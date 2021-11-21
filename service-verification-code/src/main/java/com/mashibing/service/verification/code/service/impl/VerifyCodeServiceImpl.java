package com.mashibing.service.verification.code.service.impl;

import com.mashibing.internal.common.dto.ResponseResult;
import com.mashibing.internal.common.dto.serviceverificationcode.VerifyCodeResponse;
import com.mashibing.service.verification.code.service.VerifyCodeService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class VerifyCodeServiceImpl implements VerifyCodeService {

    @Override
    public ResponseResult<VerifyCodeResponse> generate(int identity, String phoneNumber) {
        // 校验 三档验证。乌云 安全检测。业务方控制，不能无限制发短信。
        // redis 1分钟发了3次，限制你5分钟不能发。。1小时发了10次，限制24小时内不能发。

        String code = String.valueOf((int) ((Math.random() * 9 + 1) * Math.pow(10, 5)));

        VerifyCodeResponse data = new VerifyCodeResponse();
        data.setCode(code);
        return ResponseResult.success(data);
    }

    /**
     * 用户 传进里来的验证码 和 redis 中 验证码 一直 ，校验通过，否则不过。
     *
     * @param identity
     * @param phoneNumber
     * @param code
     * @return
     */
    public ResponseResult verify(int identity, String phoneNumber, String code) {
        //生成redis key
//        String keyPre = RedisKeyUtil.generateKeyPreByIdentity(identity);
//        String key = keyPre + phoneNumber;
//        BoundValueOperations<String, String> codeRedis = redisTemplate.boundValueOps(key);
//        String redisCode = codeRedis.get();
//
//        if(StringUtils.isNotBlank(code)
//                && StringUtils.isNotBlank(redisCode)
//                && code.trim().equals(redisCode.trim())) {
//            return ResponseResult.success("");
//        }else {
//            return ResponseResult.fail(CommonStatusEnum.VERIFY_CODE_ERROR.getCode(), CommonStatusEnum.VERIFY_CODE_ERROR.getValue());
//        }

        return null;
    }

    public static void main(String[] args) {

        int sum = 1000000;
        long start = System.currentTimeMillis();
        for (int i = 0; i < sum; i++) {
            String code = (Math.random() + "").substring(2, 8);

        }
        long end = System.currentTimeMillis();
        System.out.println("时间耗费：" + (end - start));

        long start1 = System.currentTimeMillis();
        for (int i = 0; i < sum; i++) {
            String code = String.valueOf((int) ((Math.random() * 9 + 1) * Math.pow(10, 5)));
        }
        long end1 = System.currentTimeMillis();
        System.out.println("时间耗费：" + (end1 - start1));


        long start2 = System.currentTimeMillis();
        for (int i = 0; i < sum; i++) {
            String code = String.valueOf(new Random().nextInt(899999) + 100000);
        }
        long end2 = System.currentTimeMillis();
        System.out.println("时间耗费：" + (end2 - start2));


        long start3 = System.currentTimeMillis();
        for (int i = 0; i < sum; i++) {
            String code = String.valueOf(Math.random() * 899999 + 100000);
        }
        long end3 = System.currentTimeMillis();
        System.out.println("时间耗费：" + (end3 - start3));


        /**
         * 估算线程数
         * 16核 应该开几个线程。
         *  线程数 = cpu 可用核数 / 1-阻塞系数（io密集型接近1， 计算密集型 接近0）
         *
         *  提升QPS：
         *  提高并发数
         *  减少响应时间。
         */


//        /**
//         *  不要
//         */
        for (int i = 0; i < 100; i++) {
            String code = String.valueOf(new Random().nextInt(899999) + 100000);
            if (code.length() < 6) {
                System.out.println(code);
            }
        }

        for (int i = 0; i < sum; i++) {
            String code = String.valueOf(Math.random() * 899999 + 100000);
            if (code.length() < 6) {
                System.out.println(code);
            }
        }

    }
}
