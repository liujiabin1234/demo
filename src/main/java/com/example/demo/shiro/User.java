package com.example.demo.shiro;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @Date注解 maven添加
 * <dependency>
 * <groupId>org.projectlombok</groupId>
 * <artifactId>lombok</artifactId>
 * </dependency>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, Integer age) {
        this.username = username;
        this.age = age;
    }

    @NotBlank(message="用户名不能为空")
    private String username;

    @NotBlank(message="密码不能为空")
    private String password;

//    @NotBlank(message="年龄不能为空")
//    @Pattern(regexp="^[0-9]{1,2}$",message="年龄不正确")
    private Integer age;

    @AssertFalse(message = "必须为false")
    private Boolean isFalse;

    /**
     * 如果是空，则不校验，如果不为空，则校验
     */
    @Pattern(regexp="^[0-9]{4}-[0-9]{2}-[0-9]{2}$",message="出生日期格式不正确")
    private String birthday;
}
