package com.freshjuice.fl.test;

import com.freshjuice.fl.dao.base.ITempMapper;
import com.freshjuice.fl.dto.base.Temp;
import com.freshjuice.fl.service.base.ITempService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TempTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ITempMapper tempMapper;

    @Autowired
    private ITempService tempService;

    @Test
    public void test() {
       Temp temp = tempService.getTempById(1L);

       System.out.println(temp);
    }

}
