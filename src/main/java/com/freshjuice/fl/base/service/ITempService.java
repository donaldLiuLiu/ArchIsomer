package com.freshjuice.fl.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.freshjuice.fl.base.entity.Temp;

public interface ITempService extends IService<Temp> {

    public void saveTemp(Temp temp);
    public void deleteTempById(Long id);
    public void updateTemp(Temp temp);
    public Temp getTempById(Long id);

}
