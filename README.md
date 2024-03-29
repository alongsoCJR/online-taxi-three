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
           codeRedis.set(code,2, TimeUnit.MINUTES);
```

思考
验证码：先发短信还是先放缓存？

与乘客端的接口交互都在api-passenger
提供的主要两个接口：获取验证码、登陆
参考：VerificationCodeController、AuthController

#### 网关token校验
过滤器Filter
```
shouldFilter——是否执行run方法
filterOrder——过滤器执行顺序
```

如果token鉴权不通过，后面的过滤器仍会执行，但是不会路由到其他服务
有一个好的中断别的过滤器的执行，方法是：通过shouldFilter传参数来指定


### DAY9 计价时序图（订单与派单）
1. 程序开发的效率取决于需求明确
2. BigDicamal定义方式用String，小数二进制如何计算？
![小数二进制表示方式](https://github.com/alongsoCJR/online-taxi-three/blob/online-taxi-three/doc/image/Xnip2021-11-28_19-50-30.jpg)
3. 微服务设计可以参考 类的单一职责原则
```text
1. 原料、操作
2. 门、开门
```
4. 系统设计需要考虑的地方
```text
隔离系统的变化点
```
5. 电商-订单系统涉及到的两个点
```text
1. 性能
2. 数据一致性
```

### 过滤器Filter与监听器Intercepter
1. 过滤器先执行，监听器后执行
2. Filter需要在web.xml中配置，依赖于Servlet
3. Interceptor需要在SpringMVC中配置，依赖于框架

两者的区别
1. 拦截器（Interceptor）是基于Java的反射机制，而过滤器（Filter）是基于函数回调。从灵活性上说拦截器功能更强大些，Filter能做的事情，都能做，而且可以在请求前，请求后执行，比较灵活。

![参考](https://blog.csdn.net/zxd1435513775/article/details/80556034#:~:text=%E4%B8%A4%E8%80%85%E7%9A%84%E6%9C%AC%E8%B4%A8%E5%8C%BA%E5%88%AB,%E8%AF%B7%E6%B1%82%E5%90%8E%E6%89%A7%E8%A1%8C%EF%BC%8C%E6%AF%94%E8%BE%83%E7%81%B5%E6%B4%BB%E3%80%82)

