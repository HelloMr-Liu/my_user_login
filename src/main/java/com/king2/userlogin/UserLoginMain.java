package com.king2.userlogin;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;
import javax.xml.ws.soap.Addressing;

/**
 * ================================================================
 * 说明：当前类说说明
 * <p>
 * 作者          时间                    注释
 * 刘梓江	2020/4/2  10:27            创建
 * =================================================================
 **/
@SpringBootApplication
@MapperScan({"com.king2.userlogin.mapper"})
@EnableScheduling
public class UserLoginMain {

    public static void main(String[] args) {
        SpringApplication.run(UserLoginMain.class);
    }

    @Bean
    //由于 @PropertySource 不支持yml文件的对象转换 默认支持properties
    //所以手动配置自定义 yml文件
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer configurer = new
                PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource("applicationdatabaseconfig.yml"));
        configurer.setProperties(yaml.getObject());
        return configurer;
    }
}
    
    
    