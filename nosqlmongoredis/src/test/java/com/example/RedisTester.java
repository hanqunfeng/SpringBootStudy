package com.example;

import com.example.redis.dao.StudentDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * ${DESCRIPTION}
 * Created by hanqunfeng on 2016/12/26 18:53.
 */


public class RedisTester  extends  NosqlMongoRedisApplicationTests{

    @Autowired
    private StudentDao studentDao;

    @Test
    public void saveStudentTest() throws Exception {
        String uri = "/saveStudent?id=2&name=张三&age=10";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println("saveStudentTest content == " + content);
    }
}
