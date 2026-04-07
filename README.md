# SnapHive - 悦木图片分享平台

<p align="center">
  <img src="https://img.shields.io/badge/Spring%20Boot-2.6.13-green" alt="Spring Boot">
  <img src="https://img.shields.io/badge/Vue-3.4-blue" alt="Vue 3">
  <img src="https://img.shields.io/badge/Java-1.8-orange" alt="Java 1.8">
  <img src="https://img.shields.io/badge/TypeScript-5.6-blue" alt="TypeScript">
  <img src="https://img.shields.io/badge/License-MIT-yellow" alt="License">
</p>

> 🎨 一个面向学习和生活分享的图片社区平台，致力于为用户提供安全、友好的图片分享环境。
>
> 本项目由程序员鱼皮的[云图库项目](https://github.com/liyupi/yu-picture)改造而来，在原有基础上进行了深度重构和功能扩展。
>
> 🔗 在线体验：https://lumenglover.com
>
> ⚠️ **仅供学习交流使用，禁止商业用途**

---

## 📋 目录

- [项目简介](#项目简介)
- [功能特性](#功能特性)
- [技术架构](#技术架构)
- [项目结构](#项目结构)
- [快速开始](#快速开始)
- [API 文档](#api-文档)
- [开发规范](#开发规范)
- [已知问题](#已知问题)
- [更新日志](#更新日志)
- [致谢](#致谢)

---

## 项目简介

SnapHive（悦木）是一个功能丰富的图片分享社区平台，支持公共图库浏览、私人空间管理、社交互动、实时聊天等功能。项目采用前后端分离架构，后端使用 Spring Boot 构建 RESTful API，前端使用 Vue 3 构建现代化用户界面。

### 核心功能模块

| 模块 | 功能描述 |
|------|---------|
| 🖼️ **图片管理** | 上传、编辑、分类、AI 扩图、智能审核 |
| 👥 **用户系统** | 注册/登录、邮箱验证、关注系统、签到系统 |
| 💬 **社交功能** | 实时私信、帖子发布、评论互动、点赞分享 |
| 🏠 **空间系统** | 空间创建、成员管理、权限控制、空间分析 |
| 🎮 **娱乐功能** | 小游戏中心（贪吃蛇、2048、俄罗斯方块等） |
| 🛠️ **实用工具** | 计算器、计时器、随机数、进制转换等 |
| 🤖 **AI 功能** | DeepSeek AI 对话、智能推荐 |

---

## 功能特性

### 🖼️ 图片管理
- 支持本地上传和 URL 上传
- 多级分类和标签管理
- 基于腾讯云 COS 的对象存储
- AI 扩图和图像搜索
- 瀑布流和列表双模式展示
- 图片智能审核

### 👥 用户系统
- 邮箱注册/登录
- 图形验证码防刷
- 用户签到（基于 Redis BitMap）
- 用户关注和粉丝系统
- 用户行为分析

### 💬 社交互动
- WebSocket 实时私信聊天
- 帖子发布与评论
- 点赞、收藏、分享
- 消息通知中心
- 互动历史记录

### 🏠 空间协作
- 私人/团队空间创建
- 空间成员权限管理
- 空间数据分析
- 团队聊天室

### 🎮 娱乐功能
- 贪吃蛇、2048、俄罗斯方块
- 扫雷、记忆翻牌、打砖块
- 恐龙快跑、滑动拼图、连连看
- 弹幕互动墙

### 🛠️ 实用工具
- 计算器、计时器、番茄钟
- 随机数生成器、进制转换器
- 颜色选择器、便签墙
- 今天吃什么（随机选择器）

---

## 技术架构

### 系统架构图

```
┌─────────────────────────────────────────────────────────┐
│                    前端层 (Frontend)                     │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────────┐ │
│  │   Vue 3     │  │   Vite      │  │  Ant Design Vue │ │
│  │ TypeScript  │  │   Pinia     │  │     Axios       │ │
│  └─────────────┘  └─────────────┘  └─────────────────┘ │
└─────────────────────────────────────────────────────────┘
                          │
                          ▼ HTTP/WebSocket
┌─────────────────────────────────────────────────────────┐
│                    网关层 (Gateway)                      │
│              Nginx / 负载均衡 / HTTPS                    │
└─────────────────────────────────────────────────────────┘
                          │
                          ▼
┌─────────────────────────────────────────────────────────┐
│                    后端层 (Backend)                      │
│  ┌─────────────────────────────────────────────────┐   │
│  │           Spring Boot 2.6.13                     │   │
│  │  ┌──────────┐ ┌──────────┐ ┌──────────┐         │   │
│  │  │Controller│ │  Service │ │Repository│         │   │
│  │  └──────────┘ └──────────┘ └──────────┘         │   │
│  │  ┌──────────┐ ┌──────────┐ ┌──────────┐         │   │
│  │  │ Sa-Token │ │ MyBatis  │ │  Redis   │         │   │
│  │  │  AOP     │ │   Plus   │ │   ES     │         │   │
│  │  └──────────┘ └──────────┘ └──────────┘         │   │
│  └─────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────┘
                          │
          ┌───────────────┼───────────────┐
          ▼               ▼               ▼
┌─────────────────┐ ┌─────────────┐ ┌─────────────────┐
│     MySQL       │ │    Redis    │ │ Elasticsearch   │
│   (主数据库)     │ │  (缓存/会话) │ │   (全文检索)     │
└─────────────────┘ └─────────────┘ └─────────────────┘
```

### 后端技术栈

| 技术 | 版本 | 用途 |
|------|------|------|
| Spring Boot | 2.6.13 | 核心框架 |
| Java | 1.8 | 开发语言 |
| MyBatis-Plus | 3.5.9 | ORM 框架 |
| MySQL | 8.0+ | 关系型数据库 |
| Redis | 6.x | 缓存/计数器/限流 |
| Elasticsearch | 7.17.x | 全文检索 |
| Sa-Token | 1.39.0 | 权限认证 |
| WebSocket | - | 实时通信 |
| Redisson | 3.21.0 | 分布式锁/BitMap |
| Caffeine | 2.9.3 | 本地缓存 |
| Disruptor | 3.4.2 | 高性能队列 |
| Hutool | - | 工具库 |
| ShardingSphere | 5.2.0 | 分库分表 |

### 前端技术栈

| 技术 | 版本 | 用途 |
|------|------|------|
| Vue | 3.4 | 核心框架 |
| TypeScript | 5.6 | 开发语言 |
| Vite | 6.0 | 构建工具 |
| Pinia | 2.2 | 状态管理 |
| Vue Router | 4.4 | 路由管理 |
| Ant Design Vue | 4.2 | PC 端 UI 组件 |
| Vant | 4.9 | 移动端 UI 组件 |
| Axios | 1.7 | HTTP 请求 |
| ECharts | 5.6 | 数据可视化 |

---

## 项目结构

```
SnapHive/
├── SnapHive-backed/              # 后端项目
│   ├── src/main/java/com/yjc/snaphive/
│   │   ├── controller/           # 控制器层 (API 入口)
│   │   ├── service/              # 业务逻辑层
│   │   │   └── impl/             # 业务实现
│   │   ├── mapper/               # 数据访问层 (MyBatis)
│   │   ├── model/                # 数据模型
│   │   │   ├── entity/           # 实体类
│   │   │   ├── dto/              # 数据传输对象
│   │   │   └── vo/               # 视图对象
│   │   ├── common/               # 通用工具
│   │   ├── constant/             # 常量定义
│   │   ├── exception/            # 异常处理
│   │   ├── manager/              # 业务管理器
│   │   ├── annotation/           # 自定义注解
│   │   ├── aspect/               # AOP 切面
│   │   ├── config/               # 配置类
│   │   ├── skill/                # Skill 模块
│   │   └── job/                  # 定时任务
│   └── src/main/resources/
│       └── application.yml       # 主配置
│
├── SnapHive-fornted/             # 前端项目
│   ├── src/
│   │   ├── api/                  # API 接口定义
│   │   ├── components/           # 公共组件
│   │   ├── pages/                # 页面组件
│   │   ├── views/                # 视图组件
│   │   ├── stores/               # Pinia 状态管理
│   │   ├── router/               # 路由配置
│   │   ├── utils/                # 工具函数
│   │   ├── constants/            # 常量定义
│   │   ├── types/                # TypeScript 类型
│   │   ├── skill/                # Skill 模块
│   │   ├── access.ts             # 权限校验
│   │   └── request.ts            # Axios 配置
│   └── package.json
│
├── deploy/                       # 部署配置
├── sql/                          # 数据库脚本
└── README.md
```

---

## 快速开始

### 环境要求

#### 后端环境
- JDK 1.8+
- MySQL 8.0+
- Redis 6.x+
- Elasticsearch 7.17.x
- Maven 3.6+

#### 前端环境
- Node.js 16+
- npm 8+

### 后端启动

1. **克隆项目**
   ```bash
   git clone https://github.com/DevYangJC/SnapHive.git
   cd SnapHive/SnapHive-backed
   ```

2. **初始化数据库**
   - 创建 MySQL 数据库
   - 执行 `sql/` 目录下的初始化脚本

3. **配置修改**
   - 修改 `src/main/resources/application.yml` 中的数据库、Redis、ES 配置
   - 配置腾讯云 COS 密钥

4. **编译运行**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

### 前端启动

1. **安装依赖**
   ```bash
   cd SnapHive-fornted
   npm install
   ```

2. **开发环境**
   ```bash
   npm run dev
   ```

3. **生产构建**
   ```bash
   npm run build
   ```

---

## API 文档

### 接口规范

- **基础路径**: `/api`
- **响应格式**:
  ```json
  {
    "code": 0,
    "data": {},
    "message": "success"
  }
  ```

### 主要模块

| 模块 | 路径前缀 | 说明 |
|------|---------|------|
| 用户模块 | `/user/**` | 注册、登录、用户信息 |
| 图片模块 | `/picture/**` | 上传、查询、管理 |
| 空间模块 | `/space/**` | 空间创建、成员管理 |
| 帖子模块 | `/post/**` | 发布、评论、点赞 |
| 聊天模块 | `/chat/**` | 私聊、群聊 |
| 搜索模块 | `/search/**` | 图片搜索、帖子搜索 |

---

## 开发规范

### 代码规范

- 后端遵循阿里巴巴 Java 开发手册
- 前端遵循 Vue 官方风格指南
- 使用 ESLint + Prettier 进行代码格式化

### 分支管理

```
main           # 主分支，稳定版本
develop        # 开发分支
feature/*      # 功能分支
bugfix/*       # 修复分支
```

### 提交规范

```
feat: 新增功能
fix: 修复问题
docs: 文档更新
style: 代码格式调整
refactor: 重构代码
test: 测试相关
chore: 构建/工具相关
```

---


---

## 更新日志

### v1.2.0 (2025-06-08)

- ✨ 新增小游戏功能（贪吃蛇、2048、俄罗斯方块等）
- ✨ 新增实用工具功能（计算器、计时器、随机数等）
- ✨ 新增 DeepSeek AI 对话功能
- ✨ 新增恋爱画板功能
- ✨ 新增推荐算法
- ✨ 新增树洞功能
- ⚡ 优化系统性能
- 🐛 修复已知问题

### 历史版本

详见 [CHANGELOG.md](./CHANGELOG.md)

---

## 致谢

- 特别感谢 [程序员鱼皮](https://github.com/liyupi) 的[云图库项目](https://github.com/liyupi/yu-picture)，为本项目提供了重要的基础架构参考
- 感谢所有开源项目和工具的支持

---

## 开发者

- **作者**: SnapHive
- **邮箱**: 109484028@qq.com
- **GitHub**: [@DevYangJC](https://github.com/DevYangJC)

---

## 许可证

本项目使用 [MIT License](LICENSE) 开源协议

> ⚠️ **免责声明**: 本项目仅供学习交流使用，禁止用于任何商业用途。

---

<p align="center">
  如果这个项目对你有帮助，请给个 ⭐ Star 支持一下！
</p>
