package com.example.demo.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {


    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //存放可放行的权限
        Set<String> str = new HashSet<>();
        str.add("companyUser:user:save");
        info.setStringPermissions(str);
        return info;
    }

    //验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        char[] pd = usernamePasswordToken.getPassword();
        String password = String.valueOf(pd);
        String username = usernamePasswordToken.getUsername();
        if (!"admin".equals(username)||!"123456".equals(password)) {
            throw new IncorrectCredentialsException("账号或密码不正确");
        }

        User user = new User();
        user.setPassword(password);
        user.setUsername(username);
        //第一个参数用来存放在session中的信息
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
    }

}
