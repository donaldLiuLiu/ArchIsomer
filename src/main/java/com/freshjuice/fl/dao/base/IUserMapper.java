package com.freshjuice.fl.dao.base;

import com.freshjuice.fl.dto.base.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUserMapper {
    User getUserById(String id);
}
