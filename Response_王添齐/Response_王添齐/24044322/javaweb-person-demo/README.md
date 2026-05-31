# JavaWeb Person 示例项目

## 项目介绍
基于 Servlet + JSP + JDBC 的基础 JavaWeb 项目，实现 Person 人员信息的新增与查询功能。

## 技术栈
- Java
- Servlet 4.0
- JSP
- JDBC
- MySQL

## 运行环境
- JDK 8+
- Tomcat 9+
- MySQL 5.7+

## 数据库准备
```sql
CREATE DATABASE IF NOT EXISTS javaweb_db;
USE javaweb_db;

CREATE TABLE person (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL,
    age INT,
    gender VARCHAR(10),
    phone VARCHAR(20)
);
```

## 部署步骤
1. 修改 `DBUtil.java` 中的数据库账号密码
2. 将项目部署到 Tomcat
3. 访问 `http://localhost:8080/javaweb-person-demo/index.jsp`
