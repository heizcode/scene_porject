package com.heizai.common.utils;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zhulx
 * @version 1.0
 * @ClassName BeanUtils
 * @Description TODO Object、List、Set、Page 复制
 * @Date 2023/3/16 11:14
 * @since JDK1.8
 */
public class BeanUtils {

    /**
     * 对象复制
     *
     * @param source source
     * @param clazz  class
     * @return T
     * @author myyan
     * @date 2022/12/22 15:15
     */
    public static <T> T copyProperties(Object source, Class<T> clazz) {
        if (source == null) {
            return null;
        }
        if (clazz == null) {
            return null;
        }
        T result = null;
        try {
            result = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        BeanUtil.copyProperties(source, result);
        return result;
    }

    /**
     * List集合复制
     *
     * @param sourcesList sourcesList
     * @param target      target
     * @return java.util.List<T>
     * @author
     * @date 2022/12/22 15:18
     */
    public static <T> List<T> copyProperties(List<?> sourcesList, Class<T> target) {
        if (sourcesList == null) {
            return null;
        }
        return sourcesList.stream().map(item -> copyProperties(item, target)).collect(Collectors.toList());
    }

    /**
     * Set集合复制
     *
     * @param sourceSet sourceSet
     * @param clazz     clazz
     * @return java.util.Set<T>
     * @author myyan
     * @date 2022/12/22 15:19
     */
    public static <T> Set<T> copyProperties(Set<?> sourceSet, Class<T> clazz) {
        if (sourceSet == null) {
            return null;
        }
        return sourceSet.stream().map(item -> copyProperties(item, clazz)).collect(Collectors.toSet());
    }

    /**
     * MyBatis-Plus page属性复制
     *
     * @param sourcePage sourcePage
     * @param clazz      clazz
     * @return com.baomidou.mybatisplus.extension.plugins.pagination.Page<T>
     * @author myyan
     * @date 2022/12/22 15:19
     */
    public static <T> Page<T> copyProperties(Page<?> sourcePage, Class<T> clazz) {
        if (sourcePage == null) {
            return null;
        }

        Page<T> result = new Page<T>();
        result.setRecords(copyProperties(sourcePage.getRecords(), clazz));
        result.setTotal(sourcePage.getTotal());
        result.setSize(sourcePage.getSize());
        result.setCurrent(sourcePage.getCurrent());
        result.setOrders(sourcePage.getOrders());
        result.setSearchCount(sourcePage.isSearchCount());
        result.setOptimizeCountSql(sourcePage.optimizeCountSql());
        result.setHitCount(sourcePage.isHitCount());
        return result;
    }
}
