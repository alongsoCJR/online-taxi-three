package com.mashibing.consumer.controller;

import com.mashibing.consumer.domain.Person;
import com.mashibing.consumer.service.HealthStatusService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RestController
public class ConsumerController {

    @Autowired
    private DiscoveryClient client;

    @Autowired
    private EurekaClient client02;

    @Autowired
    private LoadBalancerClient lb;

    @Autowired
    private HealthStatusService healthStatusService;

    @Autowired
    private RestTemplate restTemplate;

    private static AtomicInteger AUTO_COUNT = new AtomicInteger();

    @RequestMapping("/health")
    public String health(@RequestParam("status") Boolean status) {
        healthStatusService.setStatus(status);
        return healthStatusService.getStatus();
    }

    @RequestMapping("/client1")
    public String getHi() {
        List<ServiceInstance> serviceInstances = client.getInstances("api-passenger");
        System.out.println("serviceInstances.size:" + serviceInstances.size());
        return "";
    }

    @RequestMapping("/client2")
    public String getServices() {
        List<String> strings = client.getServices();
        System.out.println("strings:" + strings.toString());
        return "";
    }

    @RequestMapping("/client3")
    public String getInstance() {
        List<InstanceInfo> strings = client02.getInstancesById("192.168.0.102:api-passenger:8080");
        System.out.println("strings:" + strings.toString());

        if (strings.size() > 0) {
            InstanceInfo instanceInfo = strings.get(0);
            if (InstanceInfo.InstanceStatus.UP.equals(instanceInfo.getStatus())) {
                String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/getHi";
                RestTemplate restTemplate = new RestTemplate();
                String response = restTemplate.getForObject(url, String.class);
                System.out.println("response:" + response);

            }
        }
        return "";
    }

    @RequestMapping("/client4")
    public String getInstanceWithRobbin() {
        ServiceInstance instanceInfo = lb.choose("api-passenger");
        String url = "http://" + instanceInfo.getHost() + ":" + instanceInfo.getPort() + "/getHi";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        System.out.println("response:" + response);
        return "";
    }

    /**
     * @return java.lang.String
     * @Author Jianrong.Chen
     * @Description 引入单例restTemplate
     * @Date 2021-10-24 13:52
     * @Param []
     **/
    @RequestMapping("/client5")
    public String client5() {
        ServiceInstance instanceInfo = lb.choose("api-passenger");
        String url = "http://" + instanceInfo.getHost() + ":" + instanceInfo.getPort() + "/getHi";
        String response = restTemplate.getForObject(url, String.class);
        return response;
    }


    /**
     * @return java.lang.String
     * @Author Jianrong.Chen
     * @Description 自定义负载算法
     * @Date 2021-10-24 13:51
     * @Param []
     **/
    @RequestMapping("/client6")
    public String client6() {
        List<ServiceInstance> serviceInstances = client.getInstances("api-passenger");
        int size = serviceInstances.size();
        // 随机负载
        int index = new Random().nextInt(size);
        ServiceInstance instanceInfo = serviceInstances.get(index);
        System.out.println("随机负载:" + index);

        // 轮训负载
//        index = AUTO_COUNT.getAndIncrement() % size;
//        instanceInfo = serviceInstances.get(index);
//        System.out.println("轮训负载:" + index);


//        ServiceInstance instanceInfo = lb.choose("api-passenger");
        String url = "http://" + instanceInfo.getHost() + ":" + instanceInfo.getPort() + "/getHi";
        String response = restTemplate.getForObject(url, String.class);
        return response;
    }

    /**
     * @return java.lang.String
     * @Author Jianrong.Chen
     * @Description 通过自定义类/配置文件指定负载算法
     * @Date 2021-10-24 13:52
     * @Param []
     **/
    @RequestMapping("/client7")
    public String client7() {
        ServiceInstance instanceInfo = lb.choose("api-passenger");
        String url = "http://" + instanceInfo.getHost() + ":" + instanceInfo.getPort() + "/getHi";
        String response = restTemplate.getForObject(url, String.class);
        return response;
    }

    /**
     * @return java.lang.String
     * @Author Jianrong.Chen
     * @Description 使用资源地址调用服务
     * @Date 2021-10-24 13:52
     * @Param []
     **/
    @RequestMapping("/client8")
    public String client8() {
        String url = "http://api-passenger/getHi";
        String response = restTemplate.getForObject(url, String.class);
        return response;
    }

    /**
     * @return java.lang.String
     * @Author Jianrong.Chen
     * @Description Ribbon脱离Eureka
     * @Date 2021-10-24 13:52
     * @Param []
     **/
    @RequestMapping("/client9")
    public String client9() {
        String url = "http://api-passenger/getHi";
        String response = restTemplate.getForObject(url, String.class);
        return response;
    }

    /**
     * @return java.lang.String
     * @Author Jianrong.Chen
     * @Description getForEntity方法
     * @Date 2021-10-24 13:52
     * @Param []
     **/
    @RequestMapping("/client10")
    public ResponseEntity<String> client10() {
        String url = "http://api-passenger/getHi";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        System.out.println(response);
        return response;
    }

    /**
     * @return java.lang.String
     * @Author Jianrong.Chen
     * @Description getForEntity方法传Map
     * @Date 2021-10-24 13:52
     * @Param []
     **/
    @RequestMapping("/client11")
    public Map client11() {
        String url = "http://api-passenger/getMap";
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
        System.out.println(response);
        return response.getBody();
    }


    /**
     * @return java.lang.String
     * @Author Jianrong.Chen
     * @Description getForEntity 返回Object
     * @Date 2021-10-24 13:52
     * @Param []
     **/
    @RequestMapping("/client12")
    public Person client12() {
        String url = "http://api-passenger/getObj";
        ResponseEntity<Person> response = restTemplate.getForEntity(url, Person.class);
        System.out.println(response);
        return response.getBody();
    }

    /**
     * @return java.lang.String
     * @Author Jianrong.Chen
     * @Description getForEntity 返回Object 携带参数
     * @Date 2021-10-24 13:52
     * @Param []
     **/
    @RequestMapping("/client13")
    public Person client13() {
        String url = "http://api-passenger/getObj2?name={1}";
        ResponseEntity<Person> response = restTemplate.getForEntity(url, Person.class, "mucox");
        System.out.println(response);
        return response.getBody();
    }

    /**
     * @return java.lang.String
     * @Author Jianrong.Chen
     * @Description getForEntity 返回Object 通过Map携带参数
     * @Date 2021-10-24 13:52
     * @Param []
     **/
    @RequestMapping("/client14")
    public Person client14() {
        String url = "http://api-passenger/getObj2?name={name}";
        Map<String, String> stringStringMap = Collections.singletonMap("name", "along");
        ResponseEntity<Person> response = restTemplate.getForEntity(url, Person.class, stringStringMap);
        System.out.println(response);
        return response.getBody();
    }

    /**
     * @return java.lang.String
     * @Author Jianrong.Chen
     * @Description getForEntity 返回Object 通过Map携带参数
     * @Date 2021-10-24 13:52
     * @Param []
     **/
    @RequestMapping("/client15")
    public Person client15() {
        String url = "http://api-passenger/postParam";
        Map<String, String> stringStringMap = Collections.singletonMap("name", "along");
        ResponseEntity<Person> response = restTemplate.postForEntity(url, "along", Person.class);
        System.out.println(response);
        return response.getBody();
    }
}