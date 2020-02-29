package com.hanqf.config;/**
 * Created by hanqf on 2020/3/1 00:37.
 */


import com.hanqf.model.User2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author hanqf
 * @date 2020/3/1 00:37
 */
@Configuration
@ComponentScan("com.hanqf")
public class AppConfig {

    @Bean(name = "user2")
    public User2 initUser2() {
        User2 user2 = new User2();
        user2.setId(2L);
        user2.setUserName("user_name_2");
        user2.setNote("note_2");
        return user2;
    }
}
