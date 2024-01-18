# cathay-bank-simple-demo

- [點此測試 API ](http://123.241.19.224/)
- [點此確認 schedule endpoint: /actuator/scheduledtasks ](http://123.241.19.224/actuator/scheduledtasks)

- 此專案是針對與國泰銀行公司申請的線上作業：

  ## 專案講解
  ### 使用環境及工具
  - 採用 Java 17 語言的 Spring boot 3.1.0 框架，串接第三方 API，將資料存入 MongoDB Atlas，Junit 5 撰寫 Unit test & Integration test

  ### 如何運行此專案 (使用 maven 建置)
  1. 已經部署在 Linux 幾台中，網址上方提供
  2. 部署在其他機器 (如下)

  ### Clone 專案
  ```bash
  # 透過 git clone 專案到主機任意路徑下
  git clone https://github.com/wun-yu-lin/practiceForex.git
  ```

  ### 設定 application.properties
  ```preperties
  spring.task.scheduling.pool.size=1
  spring.data.mongodb.uri=mongodb+srv://<UserName>:<Password>@<Cluster>.mongodb.net/forex
  spring.data.mongodb.database=forex
  management.endpoints.web.exposure.include=scheduledtasks

  ```

  ### 建立 MongoDB Atlas
  [可參考此資料](https://www.mongodb.com/docs/atlas/getting-started/)
  1. 建立 MongoDB database cluster
  2. Database access 設定
  3. 建立 forex 名稱 database


  ### maven 建置專案
  ```bash
  
  ## 安裝 maven, 以 macOS 為例
  brew install maven

  ## cd 到專案檔案位置
  cd /Users/linwunyu/Documents/GitHub/CathayJob

  ## 編譯專案
  mvn compile
 
  ## 執行測試
  mvn test

  ## 打包專案
  mvn package


  ## 運行專案
  nohup java -jar target/CathayJob-0.0.1-SNAPSHOT.jar &


  ```

  ## 以下為專案資料夾結構
  1. Controller-service-DAO 三層式架構
  2. 使用 POJO

  ```bash
  ├── pom.xml
  ├── src
  │   ├── main
  │   │   ├── java
  │   │   │   └── com
  │   │   │       └── example
  │   │   │           └── cathayjob
  │   │   │               ├── CathayJobApplication.java
  │   │   │               ├── controller
  │   │   │               │   ├── ForexController.java
  │   │   │               │   └── HtmlController.java
  │   │   │               ├── dao
  │   │   │               │   ├── ForexDao.java
  │   │   │               │   └── ForexDaoImpl.java
  │   │   │               ├── dto
  │   │   │               │   └── ForexPostDto.java
  │   │   │               ├── exception
  │   │   │               │   ├── ControllerException.java
  │   │   │               │   ├── MongoDbSaveErrorException.java
  │   │   │               │   └── QueryParameterErrorException.java
  │   │   │               ├── model
  │   │   │               │   └── ForexModel.java
  │   │   │               ├── schedule
  │   │   │               │   ├── ForexApiDataVO.java
  │   │   │               │   └── ForexSchedule.java
  │   │   │               ├── service
  │   │   │               │   ├── ForeServiceImpl.java
  │   │   │               │   └── ForexService.java
  │   │   │               └── vo
  │   │   │                   ├── ForexDataVO.java
  │   │   │                   ├── ForexResultVO.java
  │   │   │                   └── StatusVO.java
  │   │   └── resources
  │   │       ├── application.properties
  │   │       ├── static
  │   │       │   └── js
  │   │       │       └── index.js
  │   │       └── templates
  │   │           └── index.html
  │   └── test
  │       └── java
  │           └── com
  │               └── example
  │                   └── cathayjob
  │                       ├── CathayJobApplicationTests.java
  │                       ├── controller
  │                       │   └── ForexControllerTest.java
  │                       └── schedule
  │                           └── ForexScheduleTest.java
  └── target





  ```
