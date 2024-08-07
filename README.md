# 良好选OJ
![catLogo](https://github.com/LeungHoHin/goodchoiceoj-backend/assets/114863160/7b09a252-f3aa-44f9-b4eb-f912652d349c)

线上地址：<http://goodchoiceoj.cn/>
          <http://106.53.68.162/>

代码沙箱源码地址：<https://github.com/LeungHoHin/goodchoiceoj-code-sandbox>

前端源码地址：<https://github.com/LeungHoHin/goodchoiceoj-frontend>


## 模板特点

### 技术栈

基于**Spring Boot + Docker + Vue 3 + Arco Design**的编程题目在线评测系统。
在系统前台，管理员可以创建、管理题目；用户可以自由搜索题目、阅读题目、编写并提交代码。
在系统后端，能够根据管理员设定的题目测试用例在 自主实现的代码沙箱 中对代码进行编译、运行、判断输出是否正确。
其中，代码沙箱可以作为独立服务，提供给其他开发者使用。

### 主要工作

#### 后端
1. 库表设计：根据业务流程自主设计用户表、题目表、题目提交表，并通过给题目表添加 userId 索引提升检索性能。
2. 自主设计判题机模块的架构，定义了代码沙箱的抽象调用接口和多种实现类（比如远程 / 第三方代码沙箱），并通过**静态工厂模式 + Spring 配置化**的方式实现了对多种代码沙箱的灵活调用。
3. 使用**代理模式**对代码沙箱接口进行能力增强，统一实现了对代码沙箱调用前后的日志记录，减少重复代码。
4. 由于判题逻辑复杂、且不同题目的判题算法可能不同（比如 Java 题目额外增加空间限制），选用**策略模式**代替 if else 独立封装了不同语言的判题算法，提高系统的可维护性。
5. 使用**Java Runtime**对象的 exec 方法实现了对 Java 程序的编译和执行，并通过**Process 类**的输入流获取执行结果，实现了 Java 原生代码沙箱。
6. 通过编写Java 脚本自测代码沙箱，模拟了多种程序异常情况并针对性解决，如使用守护线程 + Thread.sleep 等待机制实现了对进程的超时中断、使用 JVM -Xmx。
7. 为保证沙箱宿主机的稳定性，选用 Docker 隔离用户代码，使用 Docker Java 库创建容器隔离执行代码，并通过 tty 和 Docker 进行传参交互，从而实现了更安全的代码沙箱。
8. 使用 VMware Workstation 虚拟机软件搭建**Ubuntu Linux + Docker**环境，并通过**JetBrains Client**连接虚拟机进行实时 远程开发 ，提高了开发效率。
9. 为提高 Docker 代码沙箱的安全性，通过**HostConfig**限制了容器的内存限制和网络隔离，并通过设置容器执行超时时间解决资源未及时释放的问题。
10. 由于 Java 原生和 Docker 代码沙箱的实现流程完全一致（编译、执行、获取输出、清理），选用模板方法模式定义了一套标准的流程并允许子类自行扩展部分流程，提高代码一致性并大幅简化冗余代码。
11. 为防止用户恶意请求代码沙箱服务，给调用方分配签名密钥，并通过校验请求头中的密钥保证了 API 调用安全。
12. 使用**Knife4j Gateway**在网关层实现了对各服务Swagger接口文档的统一聚合，无需通过切换地址查看各服务的文档。
13. 为防止判题操作执行时间较长，系统选用异步的方式，在题目服务中将用户提交 id 发送给**RabbitMQ 消息队列**，并通过 Direct 交换机转发给判题队列，由判题服务进行消费，异步更新提交状态。

#### 前端
1. 基于**Vue3 + Arco Design**组件库，自主实现了在线做题、题目检索和管理、提交列表、用户登录等页面。
2. 使用**Vue-CLI**脚手架初始化项目，并自行开发了全局页面布局和通用前端项目模板，便于后续复用。
3. 使用 TypeScript + ESLint + Prettier + Husky 保证项目编码和提交规范，提高项目的质量。
4. 全局导航生成：基于 Vue Router 的路由配置文件自动生成导航菜单，并通过给路由的 meta 属性增加 hidden 字段实现集中控制页面的显隐。
5. 全局权限管理：通过给 Vue Router 路由的 meta 属性增加 access 字段来定义页面权限，然后通过 beforeEach 全局路由守卫集中校验用户进入页面的权限，并进一步将权限管理相关代码统一封装为 access.ts 模块，简化用户使用。
6. 选用 ByteMD 开源 Markdown 文本编辑器组件，引入 gfm 插件（支持表格语法）并进一步自行封装了可复用的 Editor 和 Viewer，实现了题目内容及答案的编辑功能。
7. 基于 Webpack 整合了 Monaco Editor 开源代码编辑器组件，并进一步基于 ref 自行封装了可复用的 Editor 和 Viewer，实现了用户编写代码功能，支持多种语言的高亮。
8. 使用 Arco Design 的 Table 组件实现了题目检索页面，并通过自定义插槽将后端返回的 JSON 数据解析为美观的格式。


## 使用说明
**主页**
![image](https://github.com/LeungHoHin/goodchoiceoj-backend/assets/114863160/c30c6de1-6163-4c17-a02e-c91d099782ff)
进入主页后可以看见已经添加的题目，由于刚进入主页是未登录状态，所以点击做题或者是点击登录可以跳转到登录页面
**示例管理员**
**账号：admin**
**密码：12345678**
![image](https://github.com/LeungHoHin/goodchoiceoj-backend/assets/114863160/75249775-3d8e-4e8c-8830-81e9d9e8e931)
如果想要自己注册账号也可以点击下方的按钮进行注册
登录之后可以看见上方多出几个选项卡
如果是普通用户登录，多出的选项卡就只有**我的题目提交**
如果是管理员身份登录，还会多出**管理题目**和**修改题目**

### 做题
点击做题后出现以下界面
![image](https://github.com/LeungHoHin/goodchoiceoj-backend/assets/114863160/1264aacd-f911-4549-a072-739f2489763c)
再右侧输入代码，点击提交后系统会自动将代码提交至代码沙箱进行判题

### 创建题目
**管理员**点击创建题目后出现以下界面
![image](https://github.com/LeungHoHin/goodchoiceoj-backend/assets/114863160/d8e7f29c-b2b0-4f21-bb9f-38af714ab81b)
1. 输入标题
2. 输入标签：标签输入后按下回车即可添加一个标签
3. 输入题目内容及答案，两者均可以使用MarkDown语法进行编辑
4. 输入判题配置，时间限制以及空间限制
5. 输入测试用例，可以选择新增或删除测试用例，测试用例至少有一个
6. 点击添加

### 管理题目
**管理员**可以进行题目的管理
![image](https://github.com/LeungHoHin/goodchoiceoj-backend/assets/114863160/f2ca7566-a893-4836-a2b6-938a2f52b305)
在此处可以对题目进行管理，可以删除题目或者是修改题目。

### 查看题目提交结果
在页面选项卡之中可以查看题目的提交结果
![image](https://github.com/LeungHoHin/goodchoiceoj-backend/assets/114863160/fadc29c0-10e5-4495-8e7a-aa9ea3206e8a)


