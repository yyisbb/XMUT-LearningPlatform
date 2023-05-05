package cn.edu.xmut.learningplatform.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;


class RedisUtilsTest {
    @Test
    public void RedisTest(){
        RedisUtils.setValueTimeout("1","1", TimeUnit.MINUTES.toSeconds(5));
    }
}