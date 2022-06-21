package com.csc.spring.demo.mock;

import com.csc.spring.demo.mock.bean.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Base64Utils;

import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

/**
 * @Description:
 * @PackageName: com.csc.mock
 * @Author: 陈世超
 * @Create: 2020-10-09 17:59
 * @Version: 1.0
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({User.class})
@SpringBootTest(classes = MockApplication.class)
public class FieldAndMethodMockTest {
    PublicKey publicKey = initPublicKey();

    public FieldAndMethodMockTest() throws InvalidKeySpecException, NoSuchAlgorithmException {
    }

    public PublicKey initPublicKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCqnriBdXgN0U9910oYZ3Hbh/kePRBZitmyy85f4qm5CIojzZXi2CwAJJX2EbJ8ATWZ4hrtteQ/BoX3VIihsSMA3adrFTFaiTH6A2vKcjo/UfoltBS8l/0CZz8ug3qlZex1p2qELzu+zIg3rzTUbuwgH8mRimZNGVUzDR9c68aOlQIDAQAB";
        // 把 公钥的Base64文本 转换为已编码的 公钥bytes
        byte[] encPubKey = Base64Utils.decodeFromString(key);
        // 创建 已编码的公钥规格
        X509EncodedKeySpec encPubKeySpec = new X509EncodedKeySpec(encPubKey);
        // 获取指定算法的密钥工厂, 根据 已编码的公钥规格, 生成公钥对象
        return KeyFactory.getInstance("RSA").generatePublic(encPubKeySpec);
    }

    /**
     * 模拟修改final字段
     */
    @Test
    public void userLifeTest() {
        User user = PowerMockito.mock(User.class);
        PowerMockito.when(user.isAlive()).thenReturn(false);
        Assert.assertFalse(user.isAlive());
    }

    /**
     * 模拟修改private方法
     *
     * @throws Exception
     */
    @Test
    public void userDifTest() throws Exception {
        User user = PowerMockito.mock(User.class);
        PowerMockito.when(user.callPrivateMethod()).thenCallRealMethod();
        PowerMockito.when(user, "callUser").thenReturn(true);
        boolean result = user.callPrivateMethod();
        Assert.assertTrue(result);
    }

    /**
     * 模拟修改static方法
     */
    @Test
    public void userAddAgeTest() throws UnsupportedEncodingException {
        String phoneNumber = "18601649412";
        PowerMockito.mockStatic(User.class);
        PowerMockito.when(User.answer()).thenReturn(true);
        boolean result = User.answer();
        Assert.assertTrue(result);
    }
}
