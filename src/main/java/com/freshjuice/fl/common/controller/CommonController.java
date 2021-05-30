package com.freshjuice.fl.common.controller;

import com.freshjuice.fl.common.vo.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("common")
public class CommonController {


    @GetMapping("get")
    public JsonResult get() {
        return JsonResult.buildSuccessResult("common");
    }


}
