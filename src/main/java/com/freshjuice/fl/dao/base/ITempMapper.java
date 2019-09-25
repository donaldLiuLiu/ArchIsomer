package com.freshjuice.fl.dao.base;

import com.freshjuice.fl.dto.base.Temp;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ITempMapper {

    void insertTemp(Temp temp);

    void deleteTempById(Long id);

    void updateTemp(Temp temp);

    Temp selectTempById(Long id);
}
