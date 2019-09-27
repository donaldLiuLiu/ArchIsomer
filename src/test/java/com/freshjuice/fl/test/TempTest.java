package com.freshjuice.fl.test;

import com.freshjuice.fl.base.mapper.TempMapper;
import com.freshjuice.fl.base.entity.Temp;
import com.freshjuice.fl.base.service.ITempService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TempTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private TempMapper tempMapper;

    @Autowired
    private ITempService tempService;

    @Test
    public void test() {
       Temp temp = tempService.getTempById(1L);

       System.out.println(temp);
    }

}
