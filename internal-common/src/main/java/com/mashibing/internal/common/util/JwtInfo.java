package com.mashibing.internal.common.util;

import lombok.Data;

@Data
public class JwtInfo{
    String subject;
    Long issueDate;
}