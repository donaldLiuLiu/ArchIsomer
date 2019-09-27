package com.freshjuice.fl.base.controller;

import com.freshjuice.fl.base.service.ITempService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TempController {
    private Logger logger = LoggerFactory.getLogger(TempController.class);
    @Autowired
    private ITempService tempService;

    @RequestMapping(value={"/", "/index"}, method = RequestMethod.GET)
    public String index() {
        tempService.getTempById(1L);
        logger.debug("index 世界");
        return "index 世界";
    }
}
