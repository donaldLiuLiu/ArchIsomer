package com.freshjuice.fl.base.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.freshjuice.fl.base.entity.Temp;
import com.freshjuice.fl.base.mapper.TempMapper;
import com.freshjuice.fl.base.service.ITempService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author freshjuice
 * @since 2019-10-27
 */
@Service
public class TempServiceImpl extends ServiceImpl<TempMapper, Temp> implements ITempService {

    @Autowired
    private TempMapper tempMapper;

    @Override
    public IPage<Temp> getListAll(Page<Temp> tempPage, Temp temp) {
        tempPage.setRecords(tempMapper.selectListAll(tempPage, temp));
        return tempPage;
    }
}
