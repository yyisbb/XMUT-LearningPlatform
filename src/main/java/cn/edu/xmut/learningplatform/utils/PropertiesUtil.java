package cn.edu.xmut.learningplatform.utils;

import lombok.Data;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;
public class PropertiesUtil {

    private static String Sm4Secret;
    private static String jwtSecret;
    private static String projectPort;

    static {
        ClassPathResource resource = new ClassPathResource("application.properties");
        try {
            Properties properties = PropertiesLoaderUtils.loadProperties(resource);
            Sm4Secret = properties.getProperty("sm4.secret");
            jwtSecret = properties.getProperty("jwt.secret");
            projectPort = properties.getProperty("server.port");
        } catch (IOException e) {
            System.out.println("application.properties属性文件读取异常" + e);
        }
    }

    public static String getSm4Secret() {
        return Sm4Secret;
    }

    public static String getJwtSecret() {
        return jwtSecret;
    }

    public static String getProjectPort() {
        return projectPort;
    }
}
