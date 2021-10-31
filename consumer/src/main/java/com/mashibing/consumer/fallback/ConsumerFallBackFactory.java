package com.mashibing.consumer.fallback;

import com.mashibing.api.base.dto.PersonDTO;
import com.mashibing.consumer.service.ConsumerApi;
import feign.hystrix.FallbackFactory;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

@Component
public class ConsumerFallBackFactory implements FallbackFactory<ConsumerApi> {

    @Override
    public ConsumerApi create(Throwable cause) {
        return new ConsumerApi() {
            @Override
            public Map<Integer, String> getMap(Integer id) {
                System.out.println("熔断了：" + cause.getLocalizedMessage());
                System.out.println(cause);
                String description = "";
                if (cause instanceof RuntimeException) {
                    description = "远程服务报错";
                } else if (cause instanceof RuntimeException) {
                    description = "请求时异常：" + cause;
                } else {
                    description = "都算不上";
                }
                return Collections.singletonMap(500, description + ToStringBuilder.reflectionToString(cause));
            }

            @Override
            public Map<Integer, String> getMap2(Integer id, String name) {
                return null;
            }

            @Override
            public Map<Integer, String> getMap3(Map<String, Object> map) {
                return null;
            }

            @Override
            public Map<Integer, String> postMap(Map<String, Object> map) {
                return null;
            }

            @Override
            public String alive() {
                return null;
            }

            @Override
            public String getUserNameById(Integer id) {
                return null;
            }

            @Override
            public PersonDTO postPerson(PersonDTO person) {
                return null;
            }
        };
    }
}