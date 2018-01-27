package cn.riversky.encode;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 如在 1 个小时内密码最多重试 5 次，如果尝试次数超过 5 次就锁定 1 小时，1 小时后可再次重试，
 * 如果还是重试失败，可以锁定如 1 天，以此类推，防止密码被暴力破解,使用Ehcache 记录重试次数和超时时间。
 * @author riversky E-mail:riversky@126.com
 * @version 创建时间 ： 2018/1/25.
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher{
    private Ehcache passwordRetryCache;

    public RetryLimitHashedCredentialsMatcher() {
        CacheManager cacheManager=CacheManager.newInstance(CacheManager.class.getClassLoader().getResource("ehcache.xml"));
        passwordRetryCache=cacheManager.getCache("passwordRetryCache");
    }
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username=(String) token.getPrincipal();
        Element element=passwordRetryCache.get(username);
        if(element==null){
            element=new Element(username,new AtomicInteger(0));
            passwordRetryCache.put(element);
        }
        AtomicInteger retryCount= (AtomicInteger) element.getObjectValue();
        if(retryCount.incrementAndGet()>5){
            throw new ExcessiveAttemptsException();
        }
        boolean matches=super.doCredentialsMatch(token,info);
        if(matches){
            passwordRetryCache.remove(username);
        }

        return matches;
    }
}
