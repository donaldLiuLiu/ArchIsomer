package com.freshjuice.fl.test;

import com.freshjuice.fl.dao.base.IUserMapper;
import com.freshjuice.fl.dto.base.User;
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
    private IUserMapper userMapper;

    @Test
    public void test() {
        DataSource ds = applicationContext.getBean(DataSource.class);
        System.out.println(ds);

        User user = userMapper.getUserById("u1");
        System.out.println(user);
    }

}
