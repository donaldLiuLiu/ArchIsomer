package com.freshjuice.fl.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.freshjuice.fl.base.entity.Temp;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TempMapper extends BaseMapper<Temp> {

    void insertTemp(Temp temp);

    void deleteTempById(Long id);

    void updateTemp(Temp temp);

    Temp selectTempById(Long id);
}
