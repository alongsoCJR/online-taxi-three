# online-taxi-three
### springcloud实践，网约车项目搭建
1. 如果项目交付工作没完成，怎么办？
   - 加班
   - 加人，加资源
   - 对功能点排优先级，保证总体流程能用，没上功能下次迭代上线
2. 微服务设计原则
   - 架构，模式，拆分，隔离。目标：**隔离系统的变化点**
3. AKF（并发量太高，服务器扛不住治理方案）
   - x轴，水平复制，AAA
   - y轴，A，将A进行功能拆分
   - z轴，数据分片：手机、服饰、家电    杭州、北京
4. 基础业务架构图
   - 乘客端、司机端、微信端、官网web，boss端
5. 服务
   - zipkin、sleuth链路追踪
   - config 配置中心
   -  boot-admin健康检查
   - eureka注册中心
   - hysteric熔断降级



### DAY7 乘客发送验证码-qps提升
###### 提高QPS：
1. 能用多线程就用多线程（缓存，FutureTask，编程式事务）
2. 增加各种连接数（线程池 ，tomcat、mysql、redis等）
3. 服务无状态，便于横向扩展，扩机器
4. 让服务能力对等（serviceUrl：打乱顺序）

###### 减少响应时间
1. 异步（最终一致性，不要求及时）
2. 缓存 （减少db读取，减少磁盘IO）
3. 数据库优化
4. 多的数据，分批次返回
5. 减少调用链
6. 长连接（不要轮询 ep:司机和乘客端的消息通信）


###### 线程池线程数的定义
```线程数=CPU核数/（1-阻塞系数）（IO密集型系数接近1，CPU密集型系数接近0）```

###### 学习心得
1. 生产中不能使用快照版本
2. 常用的数据，用缓存，不要每次都db（IO瓶颈：网络，磁盘IO，减少IO）
3. review代码有很多性能的优化点，都是一些小细节


### DAY8 乘客发送验证码登陆-网关
是否需要做缓存的衡量标准
```读多写少```

数据存放DB还是Redis的标准
```估算存放数据的大小=行数*每行的大小``` 

存redis需要注意的问题
1. 数据结构(string,list，set,zset,hash)
2. 所占内存大小
3. 存放的时间（什么时候删掉）
4. 数据一致性问题
5. 原子命令的使用，set&&expire——>setEx
```//        Boolean aBoolean = codeRedis.setIfAbsent(code);
   //        if (aBoolean){
   //            codeRedis.expire(2,TimeUnit.MINUTES);
   //        }
           codeRedis.set(code,2, TimeUnit.MINUTES);```

