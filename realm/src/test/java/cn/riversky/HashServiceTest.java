package cn.riversky;

import cn.riversky.encode.LoginController;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.junit.Before;
import org.junit.Test;

/**
 * @author riversky E-mail:riversky@126.com
 * @version 创建时间 ： 2018/1/25.
 */
public class HashServiceTest {
    private LoginController loginController;
    @Before
    public void init(){
        loginController=new LoginController();
    }
    @Test
    public void retry(){
        loginController.login("classpath:shiro.ini","liu","123");
//        Subject subject=SecurityUtils.getSubject();
//        System.out.println(subject.isPermitted("user:create"));
//        subject.logout();

    }
    @Test
    public void hashServic(){
        //hash服务的构建--提供公共的服务
        DefaultHashService hashService=new DefaultHashService();
        hashService.setHashAlgorithmName("SHA-512");
//        可以通过 privateSalt 设置一个私盐，其在散列时自动与用户传入的公盐混合产生一个新盐；
        hashService.setPrivateSalt(new SimpleByteSource("123"));
        //可以通过 generatePublicSalt 属性在用户没有传入公盐的情况下是否生成公盐；
        hashService.setGeneratePublicSalt(true);
        hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());
        hashService.setHashIterations(2);

        //服务使用需要操作的数据（每个实例都不同）-需要构建一个 HashRequest，传入算法、数据、公盐、迭代次数。
        HashRequest request=new HashRequest.Builder().setAlgorithmName("MD5")
                .setSource(ByteSource.Util.bytes("123")).setSalt(ByteSource.Util.bytes("123"))
                .setIterations(4).build();

        String hex=hashService.computeHash(request).toHex();
        System.out.println(hex);
    }
}
