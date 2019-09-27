package com.freshjuice.fl.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.freshjuice.fl.base.mapper.TempMapper;
import com.freshjuice.fl.base.entity.Temp;
import com.freshjuice.fl.base.service.ITempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TempServiceImpl extends ServiceImpl<TempMapper, Temp> implements ITempService {
    @Autowired
    private TempMapper tempMapper;


    @Override
    public void saveTemp(Temp temp) {
        tempMapper.insertTemp(temp);
    }

    @Override
    public void deleteTempById(Long id) {
        tempMapper.deleteTempById(id);
    }

    @Override
    public void updateTemp(Temp temp) {
        tempMapper.updateTemp(temp);
    }

    @Override
    public Temp getTempById(Long id) {
        return tempMapper.selectTempById(id);
    }
}
