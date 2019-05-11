package com.freshjuice.fl.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FlBasicErrorController extends BasicErrorController {

	public FlBasicErrorController() {
        super(new DefaultErrorAttributes(), new ErrorProperties());
    }
	
	//覆盖 error 方法 返回统一的 json 字符串
    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.ALL));
        HttpStatus status = getStatus(request);
        //输出自定义的Json格式
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", false);
        map.put("msg", body.get("message"));
        return new ResponseEntity<Map<String, Object>>(map, status);
    }
    
    //覆盖 errorHtml 跳转/toError
    @Override
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        HttpStatus status = getStatus(request);
        response.setStatus(getStatus(request).value());
        Map<String, Object> model = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.TEXT_HTML));
        ModelAndView modelAndView = resolveErrorView(request, response, status, model);
        //return(modelAndView == null ? new ModelAndView("error", model) : modelAndView); //跳转视图
        return(modelAndView == null ? new ModelAndView("/error/toError") : modelAndView); //跳转controller
    }
	
    @RequestMapping("/toError")
    public void toError(HttpServletRequest request, HttpServletResponse response) {
    	try {
    		HttpStatus status = getStatus(request);
            response.setStatus(getStatus(request).value());
            Map<String, Object> model = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.TEXT_HTML));
    		response.setContentType("text/html; charset=UTF-8");
			response.getWriter().write("<!doctype html><html><head><meta charset=\"UTF-8\">"
					+ "<title>title</title></head>"
					+ "<body>"+status+"<br/>"+model.get("message")+"</body></html>");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
}
