package com.hanqf;

import com.hanqf.config.AppConfig;
import com.hanqf.config.OtherConfig;
import com.hanqf.model.User1;
import com.hanqf.model.User2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * Hello world!
 */
public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);


    public static void main(String[] args) {

        //获取注解对象，多个配置类可以逗号分隔
        ApplicationContext ctx
                = new AnnotationConfigApplicationContext(AppConfig.class, OtherConfig.class);
        User1 user1 = ctx.getBean(User1.class);
        logger.info(user1.toString());

        User2 user2 = (User2) ctx.getBean("user2");
        logger.info(user2.toString());
    }
}
