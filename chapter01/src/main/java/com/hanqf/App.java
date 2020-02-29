package com.hanqf;

import com.hanqf.config.AppConfig;
import com.hanqf.model.User1;
import com.hanqf.model.User2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {

        ApplicationContext ctx
                = new AnnotationConfigApplicationContext(AppConfig.class);
        User1 user1 = ctx.getBean(User1.class);
        System.out.println(user1);

        User2 user2 = (User2) ctx.getBean("user2");
        System.out.println(user2);
    }
}
