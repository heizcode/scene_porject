package com.heizai.log.ascept;

import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.heizai.log.annotation.SysLog;
import com.heizai.log.constant.Global;
import com.heizai.log.dto.SysLogDto;
import com.heizai.log.enums.BusinessStatusEnum;
import com.heizai.log.event.SysLogEvent;
import com.heizai.log.utils.SpringContextHolder;
import com.heizai.log.utils.StringUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author zhulx
 * @version 1.0
 * @ClassName LogAspect
 * @Description TODO 日志切面类
 * @Date 2023/4/10 16:12
 * @since JDK1.8
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    //当前时间（用于记录操作时间）
    ThreadLocal<Long> currentTime = new ThreadLocal<>();

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(com.heizai.log.annotation.SysLog)")
    public void logPointCut() {

    }

    @Before(value = "logPointCut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        currentTime.set(System.currentTimeMillis());
    }

    /**
     * 处理完请求后执行
     * @param joinPoint
     * @param jsonResult
     */
    @AfterReturning(pointcut = "logPointCut()", returning = "jsonResult")
    @SneakyThrows
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult) {
        handleLog(joinPoint, null, jsonResult);
        // 执行耗时
        log.info("Time-Consuming : {} ms",(System.currentTimeMillis() - currentTime.get()) + "毫秒");
        log.info("=========================================== End ===========================================");
        // 每个请求之间空一行
        log.info("");
        currentTime.remove();
    }

    /**
     * 环绕
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    /*@Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result = proceedingJoinPoint.proceed();
        // 打印出参
        log.info("Response Args  : {}", JSONUtil.toJsonStr(result));
        // 执行耗时
        log.info("Time-Consuming : {} ms",(System.currentTimeMillis() - currentTime.get()) + "毫秒");
        currentTime.remove();
        return result;
    }*/

    /**
     * 请求之前执行
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        handleLog(joinPoint, e, null);
        currentTime.remove();
    }


    /**
     *
     * @param joinPoint
     * @param e
     * @param jsonResult
     */
    protected void handleLog(final JoinPoint joinPoint, final Exception e, Object jsonResult) {
        try {
            // 获得注解
            SysLog sysLog = getAnnotationLog(joinPoint);
            if (sysLog == null) {
                return;
            }
            HttpServletRequest request = ((ServletRequestAttributes) Objects
                    .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

            SysLogDto wiseLog = new SysLogDto();
            wiseLog.setOperIp(ServletUtil.getClientIP(request));
            //wiseLog.setJsonResult(JSONUtil.toJsonStr(jsonResult));
            wiseLog.setRequestUrl(URLUtil.getPath(request.getRequestURI()));
            // 设置方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            wiseLog.setMethodName(className + "." + methodName + "()");
            // 设置请求方式
            wiseLog.setRequestMethod(request.getMethod());
            // 设置业务类型动作
            wiseLog.setBusinessType(sysLog.businessType().getCode());
            wiseLog.setBusinessTypeName(sysLog.businessType().getRemark());
            // 设置应用模块
            wiseLog.setModuleId(Global.moduleId);
            wiseLog.setModuleName(Global.moduleName);
            // 日志备注
            wiseLog.setRemark(sysLog.remark());
            // 设置操作人类别
            wiseLog.setOperatorType(sysLog.operatorType().getCode());
            wiseLog.setOperatorTypeName(sysLog.operatorType().getRemark());

            // 获取当前的用户
            /*try{
                (SecurityUtils.getCurrentUsername());
            }catch (Exception e1){

            }*/

            wiseLog.setOperLocation(StringUtils.getLocalCityInfo(wiseLog.getOperIp()));
            if (sysLog.isSaveRequestData()) {
                wiseLog.setParams(JSONUtil.toJsonStr(joinPoint.getArgs()));
            }
            wiseLog.setUserAgent(StringUtils.getBrowser(request));
            if (e != null) {
                wiseLog.setBusinessStatus(BusinessStatusEnum.FAIL.ordinal());
                wiseLog.setErrorMsg(e.getMessage());
            } else {
                wiseLog.setBusinessStatus(BusinessStatusEnum.SUCCESS.ordinal());
            }
            wiseLog.setDuration(System.currentTimeMillis() - currentTime.get());
            // log.info("日志:" + JsonMapper.toJson(wiseLog));
            SpringContextHolder.publishEvent(new SysLogEvent(wiseLog));
            // 打印请求相关参数
            log.info("========================================== Start ==========================================");
            // 打印请求 url
            log.info("URL            : {}", request.getRequestURL().toString());
            // 打印 Http method
            log.info("HTTP Method    : {}", request.getMethod());
            // 打印调用 controller 的全路径以及执行方法
            log.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            // 打印请求的 IP
            log.info("IP             : {}", request.getRemoteAddr());
            // 打印请求入参
            log.info("Request Args   : {}", JSONUtil.toJsonStr(joinPoint.getArgs()));
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    /**
     * 是否存在注解，如果存在就获取
     * @param joinPoint
     * @return
     * @throws Exception
     */
    private SysLog getAnnotationLog(JoinPoint joinPoint) throws Exception {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method != null) {
            return method.getAnnotation(SysLog.class);
        }
        return null;
    }
}
