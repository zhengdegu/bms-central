# bms-central  后台管理系统后端代码

<p align="center">
  <img src='https://img.shields.io/badge/license-Apache%202-4EB1BA.svg' alt='License'/>
  <img src="https://img.shields.io/badge/Spring%20Boot-2.2.8.RELEASE-blue" alt="Downloads"/>
  <img src="https://img.shields.io/badge/Spring%20Cloud-Hoxton.SR6-blue" alt="Downloads"/>
  <img src="https://img.shields.io/badge/Spring%20Cloud%20Alibaba-2.2.1.RELEASE-blue" alt="Downloads"/>
  <img src="https://img.shields.io/badge/Elasticsearch-7.x-brightgreen" alt="Downloads"/>
</p>

## 如果您觉得有帮助，请点右上角 "Star" 支持一下谢谢
&nbsp;
## 1. 总体架构图

&nbsp;
## 2. 功能介绍

&nbsp;
## 3. 项目介绍

&nbsp;
## 4. 模块说明
```lua
```lua
bms-central-- 父项目，公共依赖
│  ├─business-central -- 业务模块一级工程
│  │  ├─gateway-business -- 网关中心[7000]
│  │  ├─iot-business -- 物联网连接中心[5000]
│  │  ├─log-business -- 日志中心[7300]
│  │  ├─search-business -- 搜索中心[]
│  │─inner-commons -- 通用工具一级工程
│  │  ├─security-spring-boot-starter -- 封装spring security通用操作逻辑
│  │  ├─common-spring-boot-starter -- 封装通用操作逻辑
│  │  ├─log-spring-boot-starter -- 封装log通用操作逻辑
│  │  ├─redis-spring-boot-starter -- 封装Redis通用操作逻辑
│  │  ├─fegin-spring-boot-starter -- 封装Feign通用操作逻辑
│  │  ├─network-spring-boot-starter -- 封装Netty通用操作逻辑
│  │  ├─elasticsearch-spring-boot-starter -- 封装elasticsearch通用操作逻辑

```
