package com.freshjuice.fl.service.base.impl;

import com.freshjuice.fl.dao.base.ITempMapper;
import com.freshjuice.fl.service.base.ITempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TempServiceImpl implements ITempService {
    @Autowired
    private ITempMapper tempMapper;


}
