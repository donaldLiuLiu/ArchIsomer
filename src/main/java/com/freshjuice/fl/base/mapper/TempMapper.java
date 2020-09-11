package com.freshjuice.fl.base.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.freshjuice.fl.base.entity.Temp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author freshjuice
 * @since 2019-10-27
 */
public interface TempMapper extends BaseMapper<Temp> {

    List<Temp> selectListAll(Page<Temp> tempPage, Temp temp);
    List<Temp> selectMany(@Param("tempName") String tempName, @Param("tempIds") List<Long> tempIds);
    void selectByIds(@Param("ids") List<Long> ids);

}
