package com.freshjuice.fl.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value={"/", "/index"}, method = RequestMethod.GET)
    public String index() {
        logger.debug("index 世界");
        return "index 世界";
    }
}
