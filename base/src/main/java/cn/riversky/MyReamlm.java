package cn.riversky;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * @author riversky E-mail:riversky@126.com
 * @version 创建时间 ： 2018/1/23.
 */
public class MyReamlm implements Realm{
    @Override
    public String getName() {
        return "myrealm1";
    }
    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof UsernamePasswordToken;
    }
    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username= (String) authenticationToken.getPrincipal();
        String password=new String((char[])authenticationToken.getCredentials());
        if(!"zhang".equals(username)){
            //用户名错误
            throw new UnknownAccountException();
        }
        if(!"123".equals(password)){
            //认证错误
            throw new IncorrectCredentialsException();
        }
        return new SimpleAuthenticationInfo(username,password,getName());
    }
}
