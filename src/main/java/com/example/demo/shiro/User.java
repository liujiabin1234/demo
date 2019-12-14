package com.example.demo.shiro;

import lombok.Data;

import java.io.Serializable;

/**@Date注解
 * maven添加
 *         <dependency>
 *             <groupId>org.projectlombok</groupId>
 *             <artifactId>lombok</artifactId>
 *         </dependency>
 */
@Data
public class User implements Serializable {
    private String username;
    private String password;
}
