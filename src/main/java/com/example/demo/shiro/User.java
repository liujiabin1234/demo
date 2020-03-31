package com.example.demo.shiro;

import lombok.Data;

import java.io.Serializable;

/**
 * @Date注解 maven添加
 * <dependency>
 * <groupId>org.projectlombok</groupId>
 * <artifactId>lombok</artifactId>
 * </dependency>
 */
@Data
public class User implements Serializable {

    public User() {

    }

    public User(String username, Integer age) {
        this.username = username;
        this.age = age;
    }

    private String username;
    private String password;
    private Integer age;
}
