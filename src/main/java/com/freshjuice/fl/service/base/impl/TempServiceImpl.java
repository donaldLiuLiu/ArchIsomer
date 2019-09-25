package com.freshjuice.fl.service.base.impl;

import com.freshjuice.fl.dao.base.ITempMapper;
import com.freshjuice.fl.dto.base.Temp;
import com.freshjuice.fl.service.base.ITempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TempServiceImpl implements ITempService {
    @Autowired
    private ITempMapper tempMapper;


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
