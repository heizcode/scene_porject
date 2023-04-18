### Excel案例模块

​	

​	Java 中常用的 Excel 相关操作案例：导入、导出、报表



#### 1. Easy Poi(★★★★☆)

##### 1.0 公共

- 案例项目位置： [**scene_porject**——>**hz-scene-common**——>**hz-scene-common-file**——>**common-file-excel**] 模块下的 com.heizai.easypoi 包
- 相关API地址：[Excel----Easy Poi相关接口](https://console-docs.apipost.cn/preview/99ee1a31fca293bd/492a6d20f53c127e)

- 关于EasyPoi的几个jar包具体作用的话官网有解释，可以自行去了解：[[jar包具体作用](http://doc.wupaas.com/docs/easypoi/easypoi-1c0u8jachdq52)]
- pom 依赖

```
	   <!--   easypoi   -->
        <dependency>
            <groupId>cn.afterturn</groupId>
            <artifactId>easypoi-base</artifactId>
            <version>4.1.0</version>
        </dependency>
        <dependency>
            <groupId>cn.afterturn</groupId>
            <artifactId>easypoi-web</artifactId>
            <version>4.1.0</version>
        </dependency>
        <dependency>
            <groupId>cn.afterturn</groupId>
            <artifactId>easypoi-annotation</artifactId>
            <version>4.1.0</version>
        </dependency>

        <!-- mybatis-plus -->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.4.1</version>
        </dependency>

```

##### 1.1 导出

###### 1.1.1 注解方式导出





