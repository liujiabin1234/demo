package com.example.demo.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.AuthenticationStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.Collection;
import java.util.Iterator;

public class MyModularRealmAuthenticator extends ModularRealmAuthenticator {
    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken)
            throws AuthenticationException {

        // 判断getRealms()是否返回为空
        assertRealmsConfigured();
        // 强制转换回自定义的CustomizedToken
        //UserNamePasswordPhoneToken phoneToken = (UserNamePasswordPhoneToken) authenticationToken;
        // 所有Realm
        Collection<Realm> realms = getRealms();
        // 判断是单Realm还是多Realm
        if (realms.size() == 1)
            return doSingleRealmAuthentication(realms.iterator().next(), authenticationToken);
        else
            return doMultiRealmAuthentication(realms, authenticationToken);
    }

    /**
     * 重写doMultiRealmAuthentication，抛出异常，便于自定义ExceptionHandler捕获
     */
    @Override
    public AuthenticationInfo doMultiRealmAuthentication(Collection<Realm> realms, AuthenticationToken token) throws AuthenticationException {

        AuthenticationStrategy strategy = this.getAuthenticationStrategy();
        AuthenticationInfo aggregate = strategy.beforeAllAttempts(realms, token);

        Iterator var5 = realms.iterator();

        while (var5.hasNext()) {
            Realm realm = (Realm) var5.next();
            aggregate = strategy.beforeAttempt(realm, token, aggregate);
            if (realm.supports(token)) {

                AuthenticationInfo info = null;
                Throwable t = null;

                info = realm.getAuthenticationInfo(token);

                aggregate = strategy.afterAttempt(realm, token, info, aggregate, t);
            }
        }
        aggregate = strategy.afterAllAttempts(token, aggregate);
        return aggregate;
    }
}
