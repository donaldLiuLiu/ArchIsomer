package com.freshjuice.fl.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.freshjuice.fl.base.entity.Temp;
import com.freshjuice.fl.base.service.ITempService;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TempTest {

    @Autowired
    private ITempService tempService;

    //单表查询 测试
    @Test
    public void test_temp() {

        Temp temp = tempService.getById(1177636649177169921L);
        System.out.println(temp);

        System.out.println(temp.getTempDt().getYear());
        System.out.println(temp.getTempDt().getMonthValue());
        System.out.println(temp.getTempDt().getDayOfMonth());
        System.out.println(temp.getTempDt().getHour());
        System.out.println(temp.getTempDt().getMinute());
        System.out.println(temp.getTempDt().getSecond());
        System.out.println(temp.getTempDt().toLocalDate());
        System.out.println(temp.getTempDt().toLocalTime());
        System.out.println(temp.getTempDt());

        tempService.getOne(new QueryWrapper<Temp>().lambda().select(Temp::getId).eq(Temp::getId, 1177636649177169921L));
        Temp temp1 = tempService.getOne(new QueryWrapper<Temp>().lambda().eq(Temp::getId, 1177636649177169921L));
        System.out.println(temp1);

        //使用QueryWrapper构造where条件：字段关系运算(=、<、>等),字段between(not between),字段like(leftLike,rightLike),in(not in)
        //insql(in 接sql，关联查询),
        List<Temp> tempList_a = tempService.list(new QueryWrapper<Temp>().eq("temp_num", 123));
        List<Temp> tempList_b = tempService.list(new QueryWrapper<Temp>().lambda().eq(Temp::getTempNum, 123));
        tempList_a.forEach(sc -> System.out.println(sc));
        tempList_b.forEach(sc -> System.out.println(sc));

        //条件与条件之间默认使用and，可使用or
        List<Temp> tempList_cc = tempService.list(new QueryWrapper<Temp>().eq("id", 1177636650158637057L).eq("id", 1177636649177169921L));
        List<Temp> tempList_c = tempService.list(new QueryWrapper<Temp>().eq("temp_num", 123).or().eq("temp_name", "字段设置为Null"));
        List<Temp> tempList_d = tempService.list(new QueryWrapper<Temp>().lambda().eq(Temp::getTempNum, 123).or().eq(Temp::getTempName, "字段设置为Null"));
        tempList_cc.forEach(sc -> System.out.println(sc));
        tempList_c.forEach(sc -> System.out.println(sc));
        tempList_d.forEach(sc -> System.out.println(sc));

        //where a=1 and (b=2 or c=3)这种形式条件构造
        List<Temp> tempList_e = tempService.list(new QueryWrapper<Temp>().eq("temp_num", 123).and(
                i -> i.eq("id", 1177636650158637057L).or().eq("id", 1177636649177169921L)));
        List<Temp> tempList_f = tempService.list(new QueryWrapper<Temp>().lambda().eq(Temp::getTempNum, 123).and(
                i -> i.eq(Temp::getId, 1177636650158637057L).or().eq(Temp::getId, 1177636649177169921L)));
        tempList_e.forEach(sc -> System.out.println(sc));
        tempList_f.forEach(sc -> System.out.println(sc));

        //默认是select *，只查询指定字段
        List<Temp> tempList_g = tempService.list(new QueryWrapper<Temp>().select("id", "temp_name", "temp_num").eq("id", 1177636649177169921L));
        List<Temp> tempList_h = tempService.list(new QueryWrapper<Temp>().lambda().select(Temp::getId, Temp::getTempName, Temp::getTempNum).eq(Temp::getId, 1177636649177169921L));
        tempList_g.forEach(sc -> System.out.println(sc));
        tempList_g.forEach(sc -> System.out.println(sc));

        //查询条件使用函数，使用Query的apply拼接sql
        List<Temp> tempList_j = tempService.list(new QueryWrapper<Temp>().eq("temp_dt", "2019-09-08 12:09:09")); //select * from temp_temp where dt='2019-09-08 12:09:09'
        List<Temp> tempList_jj = tempService.list(new QueryWrapper<Temp>().apply("DATE_FORMAT(temp_dt, '%Y-%m-%d %H:%i:%S')={0}", "2019-09-08 12:09:09"));
        tempList_j.forEach(sc -> System.out.println(sc));
        tempList_jj.forEach(sc -> System.out.println(sc));

        //使用Wrapper指定group by,having,order by
        //使用group by,select后面字段中使用count （having中使用聚合函数类似）
        List<Temp> tempList_k = tempService.list(new QueryWrapper<Temp>().groupBy("temp_dt"));
        List<Temp> tempList_kk = tempService.list(new QueryWrapper<Temp>().select("temp_dt", "count(temp_dt) as num").groupBy("temp_dt"));
        tempList_k.forEach(sc -> System.out.println(sc));
        tempList_kk.forEach(sc -> System.out.println(sc));

        //分页
        IPage<Temp> tempList_page = tempService.page(new Page(1, 5), new QueryWrapper<Temp>().eq("temp_num", 123));
        List<Temp> tempList_page_list = tempList_page.getRecords();
        tempList_page_list.forEach(sc -> System.out.println(sc));

        //sql分页
        IPage<Temp> temps = tempService.getListAll(new Page<Temp>(1, 3), new Temp());
        temps.getRecords().forEach(sc -> System.out.println(sc));

    }

    @Test
    public void test_temp2() {
        Temp temp = new Temp();
        temp.setTempDt(LocalDateTime.now());
        tempService.save(temp);

        Temp temp_h = tempService.getById(temp.getId());
        System.out.println(temp_h);
        System.out.println(temp_h.getTempDt());
        System.out.println(temp_h.getTempDt().getYear());
        System.out.println(temp_h.getTempDt().getMonthValue());
        System.out.println(temp_h.getTempDt().getDayOfMonth());
        System.out.println(temp_h.getTempDt().getHour());
        System.out.println(temp_h.getTempDt().getMinute());
        System.out.println(temp_h.getTempDt().getSecond());
        System.out.println(temp_h.getTempDt().toLocalDate());
        System.out.println(temp_h.getTempDt().toLocalTime());

    }


}
