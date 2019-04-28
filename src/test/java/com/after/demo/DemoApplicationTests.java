package com.after.demo;

import com.after.demo.service.impl.PlanServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    PlanServiceImpl planService;

    @Test
    public void contextLoads(){
        System.out.println("hello");
    }
}
