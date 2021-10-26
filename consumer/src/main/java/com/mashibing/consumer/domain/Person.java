package com.mashibing.consumer.domain;

import lombok.Data;

@Data
public class Person {

    private String name;

    private Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }
}