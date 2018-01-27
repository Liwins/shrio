package cn.riversky;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.junit.Test;

/**
 * Shiro提供了几种支持加盐的散列算法Md5 ,sha系列的几种方案
 * @author riversky E-mail:riversky@126.com
 * @version 创建时间 ： 2018/1/25.
 */
public class EncodeTest {
    @Test
    public void eccoude(){

        //常规的md5
      System.out.println(new Md5Hash("admin"));
        //表示对admin加盐123后进行3次的md5
        System.out.println(new Md5Hash("admin","123",3));
        //使用sha256方案类似的加密方法
        System.out.println(new Sha256Hash("admin","123",3));
    }
}
