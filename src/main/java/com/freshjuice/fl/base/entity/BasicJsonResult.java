package com.freshjuice.fl.base.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BasicJsonResult<E> extends JsonResult implements Serializable {

    protected BasicJsonResult() { }
    private E data;
    public BasicJsonResult<E> setData(E data) {
        this.data = data;
        return this;
    }
}
