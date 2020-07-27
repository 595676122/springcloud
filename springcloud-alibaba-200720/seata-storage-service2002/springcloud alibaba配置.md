# springcloud alibaba配置 

### nacos

###### 下载

https://github.com/alibaba/nacos/releases/tag/1.3.1

![image-20200724145334374](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200724145334374.png)

###### 安装

- 解压nacos-server-1.3.1.zip

###### 配置

- 数据库中新建数据库nacos

- 数据库中执行nacos-mysql.sql

- 修改application.properties

  spring.datasource.platform=mysql

  *### Count of DB:*

  db.num=1

  *### Connect URL of DB:*

  db.url.0=jdbc:mysql://127.0.0.1:3306/nacos?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useUnicode=true&useSSL=false&serverTimezone=UTC

  db.user=root

  db.password=111111

###### 启动nacos

​	进入bin目录,执行startup.cmd

### seata

###### 下载

https://github.com/seata/seata/releases/tag/v1.3.0

![image-20200727091649545](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200727091649545.png)

###### 安装

解压seata-server-1.3.0.zip

###### 配置

1. file.conf

   直接重命名file.conf.example为file.conf,编辑file.conf

   store {

    *## store mode: file、db*

    **mode = "db"**

    *## file store property*

    file {

     *## store location dir*

     dir = "sessionStore"

     *# branch session size , if exceeded first try compress lockkey, still exceeded throws exceptions*

     maxBranchSessionSize = 16384

     *# globe session size , if exceeded throws exceptions*

     maxGlobalSessionSize = 512

     *# file buffer size , if exceeded allocate new buffer*

     fileWriteBufferCacheSize = 16384

     *# when recover batch read size*

     sessionReloadReadSize = 100

     *# async, sync*

     flushDiskMode = async

    }

   

    *## database store property*

    db {

     *## the implement of javax.sql.DataSource, such as DruidDataSource(druid)/BasicDataSource(dbcp) etc.*

     datasource = "druid"

     *## mysql/oracle/postgresql/h2/oceanbase etc.*

     **dbType = "mysql"**

     **driverClassName = "com.mysql.jdbc.Driver"**

     **url = "jdbc:mysql://127.0.0.1:3306/seata_test"**

     **user = "root"**

     **password = "111111"**

     minConn = 5

     maxConn = 30

     globalTable = "global_table"

     branchTable = "branch_table"

     lockTable = "lock_table"

     queryLimit = 100

    }

   }

2. 修改registry.conf

   registry {

    *# file 、nacos 、eureka、redis、zk、consul、etcd3、sofa*

    **type = "nacos"**

   

    nacos {

     application = "seata-server"

     **serverAddr = "127.0.0.1:8848"**

     group = "SEATA_GROUP"

     namespace = ""

     cluster = "default"

     username = ""

     password = ""

    }

    eureka {

     serviceUrl = "http://localhost:8761/eureka"

     application = "default"

     weight = "1"

    }

    redis {

     serverAddr = "localhost:6379"

     db = 0

     password = ""

     cluster = "default"

     timeout = 0

    }

    zk {

     cluster = "default"

     serverAddr = "127.0.0.1:2181"

     sessionTimeout = 6000

     connectTimeout = 2000

     username = ""

     password = ""

    }

    consul {

     cluster = "default"

     serverAddr = "127.0.0.1:8500"

    }

    etcd3 {

     cluster = "default"

     serverAddr = "http://localhost:2379"

    }

    sofa {

     serverAddr = "127.0.0.1:9603"

     application = "default"

     region = "DEFAULT_ZONE"

     datacenter = "DefaultDataCenter"

     cluster = "default"

     group = "SEATA_GROUP"

     addressWaitTime = "3000"

    }

    file {

     name = "file.conf"

    }

   }

   

   config {

    *# file、nacos 、apollo、zk、consul、etcd3*

    **type = "nacos"**

   

    nacos {

     **serverAddr = "127.0.0.1:8848"**

     namespace = ""

     group = "SEATA_GROUP"

     username = ""

     password = ""

    }

    consul {

     serverAddr = "127.0.0.1:8500"

    }

    apollo {

     appId = "seata-server"

     apolloMeta = "http://192.168.1.204:8801"

     namespace = "application"

    }

    zk {

     serverAddr = "127.0.0.1:2181"

     sessionTimeout = 6000

     connectTimeout = 2000

     username = ""

     password = ""

    }

    etcd3 {

     serverAddr = "http://localhost:2379"

    }

    file {

     name = "file.conf"

    }

   }

3. 导入配置信息进入nacos

   - https://github.com/seata/seata/tree/v1.3.0下载script目录 

   - 进入seata\script\config-center目录编辑config.txt文件

     transport.type=TCP
     transport.server=NIO
     transport.heartbeat=true
     transport.enableClientBatchSendRequest=false
     transport.threadFactory.bossThreadPrefix=NettyBoss
     transport.threadFactory.workerThreadPrefix=NettyServerNIOWorker
     transport.threadFactory.serverExecutorThreadPrefix=NettyServerBizHandler
     transport.threadFactory.shareBossWorker=false
     transport.threadFactory.clientSelectorThreadPrefix=NettyClientSelector
     transport.threadFactory.clientSelectorThreadSize=1
     transport.threadFactory.clientWorkerThreadPrefix=NettyClientWorkerThread
     transport.threadFactory.bossThreadSize=1
     transport.threadFactory.workerThreadSize=default
     transport.shutdown.wait=3
     **service.vgroupMapping.order_service_group=default**
     **service.default.grouplist=127.0.0.1:8091**
     service.enableDegrade=false
     service.disableGlobalTransaction=false
     client.rm.asyncCommitBufferLimit=10000
     client.rm.lock.retryInterval=10
     client.rm.lock.retryTimes=30
     client.rm.lock.retryPolicyBranchRollbackOnConflict=true
     client.rm.reportRetryCount=5
     client.rm.tableMetaCheckEnable=false
     client.rm.sqlParserType=druid
     client.rm.reportSuccessEnable=false
     client.rm.sagaBranchRegisterEnable=false
     client.tm.commitRetryCount=5
     client.tm.rollbackRetryCount=5
     client.tm.degradeCheck=false
     client.tm.degradeCheckAllowTimes=10
     client.tm.degradeCheckPeriod=2000
     store.mode=nacos
     store.file.dir=file_store/data
     store.file.maxBranchSessionSize=16384
     store.file.maxGlobalSessionSize=512
     store.file.fileWriteBufferCacheSize=16384
     store.file.flushDiskMode=async
     store.file.sessionReloadReadSize=100
     store.db.datasource=druid
     **store.db.dbType=mysql**
     **store.db.driverClassName=com.mysql.jdbc.Driver**
     **store.db.url=jdbc:mysql://127.0.0.1:3306/seata_test?useUnicode=true**
     **store.db.user=root**
     **store.db.password=111111**
     store.db.minConn=5
     store.db.maxConn=30
     store.db.globalTable=global_table
     store.db.branchTable=branch_table
     store.db.queryLimit=100
     store.db.lockTable=lock_table
     store.db.maxWait=5000
     store.redis.host=127.0.0.1
     store.redis.port=6379
     store.redis.maxConn=10
     store.redis.minConn=1
     store.redis.database=0
     store.redis.password=null
     store.redis.queryLimit=100
     server.recovery.committingRetryPeriod=1000
     server.recovery.asynCommittingRetryPeriod=1000
     server.recovery.rollbackingRetryPeriod=1000
     server.recovery.timeoutRetryPeriod=1000
     server.maxCommitRetryTimeout=-1
     server.maxRollbackRetryTimeout=-1
     server.rollbackRetryTimeoutUnlockEnable=false
     client.undo.dataValidation=true
     client.undo.logSerialization=jackson
     client.undo.onlyCareUpdateColumns=true
     server.undo.logSaveDays=7
     server.undo.logDeletePeriod=86400000
     client.undo.logTable=undo_log
     client.log.exceptionRate=100
     transport.serialization=seata
     transport.compressor=none
     metrics.enabled=false
     metrics.registryType=compact
     metrics.exporterList=prometheus
     metrics.exporterPrometheusPort=9898

   - 进入seata\script\config-center\nacos目录,git bash here,执行 **sh nacos-config.sh -h localhost -p 8848 -g SEATA_GROUP**

   - 新建数据seata_test

   - 在数据库中执行seata\script\server\db\mysql.sql

### 项目搭建

###### 父pom.xml

```
<!-- 统一管理jar包版本 -->
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
        <junit.version>4.12</junit.version>
        <log4j.version>1.2.17</log4j.version>
        <lombok.version>1.16.18</lombok.version>
        <mysql.version>5.1.47</mysql.version>
        <druid.version>1.1.16</druid.version>
        <mybatis.spring.boot.version>1.3.0</mybatis.spring.boot.version>
    </properties>

    <!-- 子模块继承之后，提供作用：锁定版本+子modlue不用写groupId和version  -->
    <dependencyManagement>
        <dependencies>
            <!--spring boot 2.2.2-->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>2.2.2.RELEASE</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <!--spring cloud Hoxton.SR1-->
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>Hoxton.SR1</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <!--spring cloud alibaba 2.1.0.RELEASE-->
            <dependency>
                <groupId>com.alibaba.cloud</groupId>
                <artifactId>spring-cloud-alibaba-dependencies</artifactId>
                <version>2.1.0.RELEASE</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>${mysql.version}</version>
            </dependency>
            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>druid</artifactId>
                <version>${druid.version}</version>
            </dependency>
            <dependency>
                <groupId>org.mybatis.spring.boot</groupId>
                <artifactId>mybatis-spring-boot-starter</artifactId>
                <version>${mybatis.spring.boot.version}</version>
            </dependency>
            <dependency>
                <groupId>junit</groupId>
                <artifactId>junit</artifactId>
                <version>${junit.version}</version>
            </dependency>
            <dependency>
                <groupId>log4j</groupId>
                <artifactId>log4j</artifactId>
                <version>${log4j.version}</version>
            </dependency>
            <dependency>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
                <version>${lombok.version}</version>
                <optional>true</optional>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <fork>true</fork>
                    <addResources>true</addResources>
                </configuration>
            </plugin>
        </plugins>
    </build>
   
```

###### 子pom.xml

```
<dependencies>
        <!--nacos-->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>
        <!--seata-->
        <dependency>
            <groupId>io.seata</groupId>
            <artifactId>seata-all</artifactId>
            <version>1.3.0</version>
        </dependency>
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-seata</artifactId>
            <exclusions>
                <exclusion>
                    <groupId>io.seata</groupId>
                    <artifactId>seata-spring-boot-starter</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>io.seata</groupId>
                    <artifactId>seata-all</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <!--feign-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
        <!--web-actuator-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <!--mysql-druid-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.37</version>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <version>1.1.10</version>
        </dependency>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.0.0</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
    </dependencies>
```

###### seata-order-service2001 application.yml

```
server:
  port: 2001
spring:
  application:
    name: seata-order-service
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848
    alibaba:
      seata:
        tx-service-group: order_service_group

  datasource:
    druid:
      url: jdbc:mysql://localhost:3306/seata_order?allowMultiQueries=true
      driverClassName: com.mysql.jdbc.Driver
      username: root
      password: 111111

feign:
  hystrix:
    enabled: false

logging:
  level:
    io:
      seata: info

mybatis:
  mapperLocations: classpath:mapper/*.xml
```

tx-service-group的值需要和config.txt中的service.vgroupMapping.order_service_group=default对应

###### registry.conf

复制seata中的registry.conf到resources目录(**新版本好像可以不用这个文件,直接将内容复制到yml文件就行,但是我做测试的时候会报错**)

###### DataSourceProxyConfig

```java
@Configuration
public class DataSourceProxyConfig {
    @Value("${mybatis.mapperLocations}")
    private String mapperLocations;


    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
    }


    @Bean
    public DataSourceProxy dataSourceProxy(DataSource dataSource) {
        return new DataSourceProxy(dataSource);
    }


    @Bean
    public SqlSessionFactory sqlSessionFactoryBean(DataSourceProxy dataSourceProxy) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSourceProxy);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
        sqlSessionFactoryBean.setTransactionFactory(new SpringManagedTransactionFactory());
        return sqlSessionFactoryBean.getObject();
    }

}
```

###### MybatisConfig

```java
@Configuration
@MapperScan({"com.zyt.springcloud.alibaba.dao"})
public class MybatisConfig {
}
```

###### SeataOrderMainApp2001

```java
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication(exclude ={ DataSourceAutoConfiguration.class})//取消数据源自动创建的配置
public class SeataOrderMainApp2001{

    public static void main(String[] args)
    {
        SpringApplication.run(SeataOrderMainApp2001.class, args);
    }
}
```

###### CommonResult

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T>
{
    private Integer code;
    private String  message;
    private T       data;

    public CommonResult(Integer code, String message)
    {
        this(code,message,null);
    }
}
```

###### Order

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order
{
    private Long id;

    private Long userId;

    private Long productId;

    private Integer count;

    private BigDecimal money;

    private Integer status; //订单状态：0：创建中；1：已完结
}
```

###### OrderDao

```java
@Mapper
public interface OrderDao
{
    //新建订单
    void create(Order order);

    //修改订单状态，从零改为1
    void update(@Param("userId") Long userId, @Param("status") Integer status);
}
```

###### AccountService

```java
@FeignClient(value = "seata-account-service")
public interface AccountService{
    @PostMapping(value = "/account/decrease")
    CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
}
```

###### OrderService

```java
public interface OrderService{
    void create(Order order);
}
```

StorageService

```java
@FeignClient("seata-storage-service")
public interface StorageService{
    @PostMapping(value = "/storage/decrease")
    CommonResult decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}
```

###### OrderServiceImpl

```java
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;
    @Resource
    private StorageService storageService;
    @Resource
    private AccountService accountService;

    /**
     * 创建订单->调用库存服务扣减库存->调用账户服务扣减账户余额->修改订单状态
     */

    @Override
    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)
    public void create(Order order){
        log.info("----->开始新建订单");
        //新建订单
        orderDao.create(order);

        //扣减库存
        log.info("----->订单微服务开始调用库存，做扣减Count");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("----->订单微服务开始调用库存，做扣减end");

        //扣减账户
        log.info("----->订单微服务开始调用账户，做扣减Money");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("----->订单微服务开始调用账户，做扣减end");


        //修改订单状态，从零到1代表已经完成
        log.info("----->修改订单状态开始");
        orderDao.update(order.getUserId(),0);
        log.info("----->修改订单状态结束");

        log.info("----->下订单结束了");

    }
}
```

###### OrderController

```java
@RestController
public class OrderController{
    @Resource
    private OrderService orderService;


    @GetMapping("/order/create")
    public CommonResult create(Order order)
    {
        orderService.create(order);
        return new CommonResult(200,"订单创建成功");
    }
}
```

###### OrderMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >

<mapper namespace="com.zyt.springcloud.alibaba.dao.OrderDao">

    <resultMap id="BaseResultMap" type="com.zyt.springcloud.alibaba.domain.Order">
        <id column="id" property="id" jdbcType="BIGINT"/>
        <result column="user_id" property="userId" jdbcType="BIGINT"/>
        <result column="product_id" property="productId" jdbcType="BIGINT"/>
        <result column="count" property="count" jdbcType="INTEGER"/>
        <result column="money" property="money" jdbcType="DECIMAL"/>
        <result column="status" property="status" jdbcType="INTEGER"/>
    </resultMap>

    <insert id="create">
        insert into t_order (id,user_id,product_id,count,money,status)
        values (null,#{userId},#{productId},#{count},#{money},0);
    </insert>


    <update id="update">
        update t_order set status = 1
        where user_id=#{userId} and status = #{status};
    </update>

</mapper>
```

###### 数据库中创建日志表

```mysql
CREATE TABLE IF NOT EXISTS `undo_log`

(

  `branch_id`   BIGINT(20)  NOT NULL COMMENT 'branch transaction id',

  `xid`      VARCHAR(100) NOT NULL COMMENT 'global transaction id',

  `context`    VARCHAR(128) NOT NULL COMMENT 'undo_log context,such as serialization',

  `rollback_info` LONGBLOB   NOT NULL COMMENT 'rollback info',

  `log_status`  INT(11)   NOT NULL COMMENT '0:normal status,1:defense status',

  `log_created`  DATETIME(6) NOT NULL COMMENT 'create datetime',

  `log_modified` DATETIME(6) NOT NULL COMMENT 'modify datetime',

  UNIQUE KEY `ux_undo_log` (`xid`, `branch_id`)

) ENGINE = InnoDB

 AUTO_INCREMENT = 1

 DEFAULT CHARSET = utf8 COMMENT ='AT transaction mode undo table';
```



###### seata-storage-service2002 application.yml

```yml
server:
  port: 2002
spring:
  application:
    name: seata-storage-service
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848
    alibaba:
      seata:
        tx-service-group: storage_service_group

  datasource:
    druid:
      url: jdbc:mysql://localhost:3306/seata_storage?allowMultiQueries=true
      driverClassName: com.mysql.jdbc.Driver
      username: root
      password: 111111

feign:
  hystrix:
    enabled: false

logging:
  level:
    io:
      seata: info

mybatis:
  mapperLocations: classpath:mapper/*.xml
```

###### StorageServiceImpl

```java
@Service
@Slf4j
public class StorageServiceImpl implements StorageService {
    @Override
    public void decrease(Long productId, Integer count) {
        log.info("------->storage-service中扣减库存开始");
        storageDao.decrease(productId,count);
        log.info("------->storage-service中扣减库存结束");
    }
    @Resource
    private StorageDao storageDao;
}
```