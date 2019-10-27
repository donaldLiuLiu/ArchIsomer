package com.freshjuice.fl.base.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.freshjuice.fl.base.entity.JsonResult;
import com.freshjuice.fl.base.entity.Temp;
import com.freshjuice.fl.base.service.ITempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author freshjuice
 * @since 2019-10-27
 */
@RestController
@RequestMapping("/base/temp")
public class TempController {

    @Autowired
    private ITempService tempService;

    @RequestMapping(value={"/", "/index"}, method = RequestMethod.GET, produces = {"application/json", "text/json"})
    public JsonResult index() {
        return JsonResult.buildSuccessResult("hello 世界");
    }

    @RequestMapping(value={"/list"}, method = RequestMethod.GET, produces = {"application/json", "text/json"})
    public JsonResult list() {
        return JsonResult.buildSuccessResult(tempService.getListAll(new Page<>(1, 3), new Temp()).getRecords());
    }

    @PostMapping("/add")
    public JsonResult add(@RequestBody Temp temp) {
        tempService.save(temp);
        return JsonResult.buildSuccessResult("添加成功");
    }

}
