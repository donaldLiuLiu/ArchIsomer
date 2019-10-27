package com.freshjuice.fl.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.freshjuice.fl.base.entity.Temp;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author freshjuice
 * @since 2019-10-27
 */
public interface ITempService extends IService<Temp> {

    IPage<Temp> getListAll(Page<Temp> tempPage, Temp temp);
}
