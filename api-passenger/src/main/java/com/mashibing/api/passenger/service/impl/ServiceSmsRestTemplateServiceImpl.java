package com.mashibing.api.passenger.service.impl;

import com.mashibing.api.passenger.service.ServiceSmsRestTemplateService;
import com.mashibing.internal.common.dto.ResponseResult;
import com.mashibing.internal.common.dto.servicesms.SmsTemplateDto;
import com.mashibing.internal.common.dto.servicesms.request.SmsSendRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ServiceSmsRestTemplateServiceImpl implements ServiceSmsRestTemplateService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResponseResult sendSms(String phoneNumber , String code) {

        String http = "http://";
        String serviceName = "SERVICE-SMS";
        String uri = "/send/sms-template";

        String url = http + serviceName + uri;
        SmsSendRequest smsSendRequest = new SmsSendRequest();
        String[] phoneNumbers = new String[] {phoneNumber};
        smsSendRequest.setReceivers(phoneNumbers);

        List<SmsTemplateDto> data = new ArrayList<SmsTemplateDto>();
        SmsTemplateDto dto = new SmsTemplateDto();
        dto.setId("SMS_144145499");
        int templateSize = 1;
        HashMap<String, Object> templateMap = new HashMap<String, Object>(templateSize);
        templateMap.put("code", code);
        dto.setTemplateMap(templateMap);
        data.add(dto);

        smsSendRequest.setData(data);

        return restTemplate.postForEntity(url, smsSendRequest, ResponseResult.class).getBody();
    }
}
