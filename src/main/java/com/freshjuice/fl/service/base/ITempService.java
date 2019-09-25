package com.freshjuice.fl.service.base;

import com.freshjuice.fl.dto.base.Temp;

public interface ITempService {

    public void saveTemp(Temp temp);
    public void deleteTempById(Long id);
    public void updateTemp(Temp temp);
    public Temp getTempById(Long id);

}
