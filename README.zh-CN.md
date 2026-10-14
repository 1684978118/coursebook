# 教材管理系统（Coursebook MIS）

基于 **Spring Boot 3 + Vue 3** 前后端分离的高校教材管理系统，覆盖教材从入库、库存、审批发放到反馈的完整业务闭环。

[日本語版はこちら](README.md)

## 功能一览

系统包含三种角色：**管理员、教务员（教师）、学生**

| 模块 | 说明 |
|------|------|
| 用户管理 | 注册、登录（JWT 鉴权）、个人信息与密码修改 |
| 教材信息管理 | 教材基础信息与教材分类的增删改查 |
| 入库管理 | 教材到货入库登记，自动更新库存 |
| 库存管理 | 各教材实时库存查询与调整 |
| 教材发放 | 教务员按班级提交发放申请 → 管理员审批（自动扣减库存）→ 标记领取 |
| 班级管理 | 班级信息维护 |
| 系统公告 | 管理员发布公告，全员可见 |
| 数据统计 | 入库 / 发放 / 库存数量统计与教材库存分布饼图 |
| 反馈模块 | 学生提交问题反馈，教务员回复处理 |

## 技术栈

- **后端**：Spring Boot 3.3、MyBatis、MySQL 8、JWT（hutool-jwt）、Lombok
- **前端**：Vue 3、Vite、Element Plus、ECharts、Axios
- **工具**：Maven、npm

## 项目结构

```
coursebook
├── springboot          # 后端 Spring Boot 项目
│   └── src/main/java/com/example
│       ├── controller  # 接口层
│       ├── service     # 业务层
│       ├── mapper      # MyBatis 数据层
│       ├── entity      # 实体类
│       └── common      # 通用配置（JWT 拦截器、全局返回、异常处理）
├── vue                 # 前端 Vue 3 项目
│   └── src/views       # 页面（manager 目录为管理端页面）
└── coursebook.sql      # 数据库初始化脚本（含演示数据）
```

## 快速开始

### 环境要求

- JDK 17+、Maven 3.8+
- Node.js 18+、MySQL 8.0

### 1. 初始化数据库

创建数据库 `coursebook`（utf8mb4），导入根目录的 `coursebook.sql`。

### 2. 启动后端

修改 `springboot/src/main/resources/application.yml` 中的数据库账号密码，然后：

```bash
cd springboot
mvn spring-boot:run
```

后端运行在 `http://localhost:9090`。

### 3. 启动前端

```bash
cd vue
npm install
npm run dev
```

打开 `http://localhost:5173` 即可访问。

### 演示账号

| 角色 | 账号 | 密码 |
|------|------|------|
| 管理员 | admin | admin |
| 教务员 | lls | 123 |
| 学生 | 20201001 | 123 |

## 部署

1. 前端：修改 `vue/.env.production` 中 `VITE_BASE_URL` 为服务器地址，`npm run build` 后将 `dist` 部署到 Nginx，并配置 `try_files $uri $uri/ /index.html;`（history 路由刷新必需）。
2. 后端：`mvn package` 得到 jar，服务器上 `java -jar` 运行（可用宝塔 Java 项目管理器），放行 9090 端口。
3. 数据库：导入 `coursebook.sql`，同步修改 `application.yml` 的数据库连接与 `fileBaseUrl`。

## Roadmap

- [ ] 低库存预警：库存低于阈值时在首页提示管理员补货
- [ ] 按学期维度管理教材与发放记录，支持学期对账导出
- [ ] 发放明细到学生个人，支持领取确认

## 说明

本项目为个人毕业设计，用于学习与求职展示，数据库为演示数据。
