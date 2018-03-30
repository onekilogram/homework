# homework
# 部署在阿里云  
# http://www.hit-kg.top:8080/homework  
# 买家 buyer 密码 reyub     买家 seller  密码 relles

## 项目结构说明

---

```
src
└── main
    ├── java
    │   └── com
    │       └── netease
    │           └── koala
    │               ├── common      公共类、定义全局配置、数据返回等
    │               ├── controller  控制器
    │               ├── dao         持久化层
    │               ├── model       数据对象
    │               ├── service     服务层接口
    │               │   └── impl    服务接口的实现
    │               └── utils       工具类
    │
    ├── resources                   系统所有的配置文件
    │   ├── context                 非Web相关Spring Bean的定义
    │   ├── dbconfig                数据库配置
    │   └── mybatis-mappers         MyBatis
    │
    │                               // 以上为服务端内容
    └── webapp                      // 以下为前端内容
        ├── asset
        │   ├── css               公共前端文件，包含网站主题、顶栏、侧边栏、公共CSS、IMG
        │   ├── html              各个具体模块的前端内容
        │   ├── images            图片
        │   ├── js                js脚本
        └── doc                   项目文档
```

## 开发说明

---

项目使用 `Maven` 构建，具体依赖信息定义在 `pom.xml` 文件中，主要的构成框架为 `Spring` 和 `MyBatis`

该项目使用的是html，AJAX异步数据获取，jquery 处理前端是显示


***源码阅读说明***

- 由于系统基于Spring MVC，因此所有的后端代码的入口皆为 `controller` 包下的控制器类
- 进行源码修改时，只需从页面中找到其数据来源 `Controller` ，然后按照`Controller` > `Service` > `DAO` 的顺序即可

