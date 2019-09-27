package com.freshjuice.fl.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.freshjuice.fl.base.mapper.TempMapper;
import com.freshjuice.fl.base.entity.Temp;
import com.freshjuice.fl.base.service.ITempService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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



    /**
     * 数据库中日期类型datetime,date,time,timestamp;entity中使用string对应
     *
     */
    @Test
    public void test_insert_one() {
        //表的insert 测试
        Temp temp = new Temp();
        temp.setTempName("hello 世界");
        temp.setTempNum(123);
        temp.setTempDt("2019-09-08 12:09:09");
        temp.setTempD("2019-09-08 12:09:09");
        temp.setTempT("2019-09-08 12:09:09");
        int insert_ret = tempMapper.insert(temp); //参数temp对象中getId获取自动生成的主键

        //null字段insert时，不插入;TableFiled可配置规则
        Temp temp_null = new Temp();
        temp_null.setTempName("字段设置为Null");
        tempService.save(temp_null);

    }


    @Test
    public void test_update_one() {

        Temp temp = new Temp();
        temp.setId(1177636649177169921L);
        temp.setTempName("d_name中");
        temp.setTempDt("2019-09-08 12:09:09");
        temp.setTempD("2019-09-08 12:09:09");
        temp.setTempNum(123);
        temp.setTempT("2019-09-08 12:09:09");
        tempMapper.updateById(temp);

        Temp temp1 = new Temp();
        temp1.setTempName("num=123修改");
        //使用UpdateWrapper指定条件,根据entity或者回环方法调用设置条件,用于构造条件
        tempService.update(temp1, new UpdateWrapper<Temp>().eq("temp_num", 123));

        //UpdateWrapper.lambda,数据库字段字段类型R为Function接口
        tempService.update(temp1, new UpdateWrapper<Temp>().lambda().eq(Temp::getTempNum, 123));

    }

    //delete 硬删除
    @Test
    public void test_del_one() {

        tempService.removeById(1177636649177169921L);

        //使用QueryWrapper指定条件
        tempService.remove(new QueryWrapper<Temp>().eq("temp_num", 1));

        //QueryWrapper的lambda格式
        tempService.remove(new QueryWrapper<Temp>().lambda().eq(Temp::getTempNum, 1));

    }


    //单表查询 测试
    @Test
    public void test_get() {

        Temp temp = tempMapper.selectById(1177636649177169921L);
        tempService.getBaseMapper().selectById(1177636649177169921L);
        tempService.getById(1177636649177169921L);

        //使用QueryWrapper构造where条件：字段关系运算(=、<、>等),字段between(not between),字段like(leftLike,rightLike),in(not in)
        //insql(in 接sql，关联查询),
        List<Temp> tempList_a = tempService.list(new QueryWrapper<Temp>().eq("temp_num", 123));
        List<Temp> tempList_b = tempService.list(new QueryWrapper<Temp>().lambda().eq(Temp::getTempNum, 123));

        //条件与条件之间默认使用and，可使用or
        List<Temp> tempList_cc = tempService.list(new QueryWrapper<Temp>().eq("id", 1177636650158637057L).eq("id", 1177636649177169921L));
        List<Temp> tempList_c = tempService.list(new QueryWrapper<Temp>().eq("temp_num", 123).or().eq("temp_name", "字段设置为Null"));
        List<Temp> tempList_d = tempService.list(new QueryWrapper<Temp>().lambda().eq(Temp::getTempNum, 123).or().eq(Temp::getTempName, "字段设置为Null"));

        //where a=1 and (b=2 or c=3)这种形式条件构造
        List<Temp> tempList_e = tempService.list(new QueryWrapper<Temp>().eq("temp_num", 123).and(
                i -> i.eq("id", 1177636650158637057L).or().eq("id", 1177636649177169921L)));
        List<Temp> tempList_f = tempService.list(new QueryWrapper<Temp>().lambda().eq(Temp::getTempNum, 123).and(
                i -> i.eq(Temp::getId, 1177636650158637057L).or().eq(Temp::getId, 1177636649177169921L)));

        //默认是select *，只查询指定字段
        List<Temp> tempList_g = tempService.list(new QueryWrapper<Temp>().select("id", "temp_name", "temp_num").eq("id", 1177636649177169921L));
        List<Temp> tempList_h = tempService.list(new QueryWrapper<Temp>().lambda().select(Temp::getId, Temp::getTempName, Temp::getTempNum).eq(Temp::getId, 1177636649177169921L));

        //查询条件使用函数，使用Query的apply拼接sql
        List<Temp> tempList_j = tempService.list(new QueryWrapper<Temp>().eq("temp_dt", "2019-09-08 12:09:09")); //select * from temp_temp where dt='2019-09-08 12:09:09'
        List<Temp> tempList_jj = tempService.list(new QueryWrapper<Temp>().apply("DATE_FORMAT(temp_dt, '%Y-%m-%d %H:%i:%S')={0}", "2019-09-08 12:09:09"));


        //使用Wrapper指定group by,having,order by
        //使用group by,select后面字段中使用count （having中使用聚合函数类似）
        List<Temp> tempList_k = tempService.list(new QueryWrapper<Temp>().groupBy("temp_dt"));
        List<Temp> tempList_kk = tempService.list(new QueryWrapper<Temp>().select("temp_dt", "count(temp_dt) as num").groupBy("temp_dt"));


        //分页
        IPage<Temp> tempList_page = tempService.page(new Page(1, 5), new QueryWrapper<Temp>().eq("temp_num", 123));
        List<Temp> tempList_page_list = tempList_page.getRecords();

        //xml 测试jdbcType
        Temp temp_o = tempService.getTempById(1177636649177169921L);
        Temp temp_p = tempService.getTempById(null);
        Temp temp_pp = tempService.getById(null);




    }

}
