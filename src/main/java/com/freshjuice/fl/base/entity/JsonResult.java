package com.freshjuice.fl.base.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import net.sf.json.JSONObject;

@Getter
@Setter
public class JsonResult {

    private String message;
    private String code;
    private boolean success;
    // 附加值对象
    private JSONObject values;

    protected JsonResult() {
    }

    public static <E> JsonResult buildSuccessResult(E data) {
        BasicJsonResult<E> result = new BasicJsonResult<E>();
        result.setMessage("SUCCESS");
        result.setSuccess(true);
        result.setCode("1");
        if (data != null) {
            result.setData(data);
        }
        return result;
    }

    public static <E> JsonResult buildSuccessResult(String code,String message) {
        BasicJsonResult result = new BasicJsonResult();
        result.setMessage(message);
        result.setSuccess(true);
        result.setCode(code);
        return result;
    }

    protected static JsonResult buildResult(String code, String message, boolean success) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(code);
        jsonResult.setMessage(message);
        jsonResult.setSuccess(success);
        return jsonResult;
    }

    public static JsonResult buildSuccessResult(String message) {
        return buildResult("1", message, true);
    }

    public static JsonResult buildFailedResult(String message) {
        return buildResult("-1", message, false);
    }

    public static BasicJsonResult buildFailedResult(String code, String message) {
        BasicJsonResult result = new BasicJsonResult();
        result.setCode(code);
        result.setMessage(message);
        result.setSuccess(false);
        return result;
    }

    /**
     * 返回JSON格式的字符串
     */
    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        String value = null;
        try {
            value = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("序列化为json异常", e);
        }

        return value;
    }

    @JsonAnySetter
    public JsonResult setValue(String key, Object value) {
        if (values == null)
            values = new JSONObject();
        values.put(key, value);
        return this;
    }
    @JsonAnyGetter
    public JSONObject getValues() {
        return values;
    }

}
