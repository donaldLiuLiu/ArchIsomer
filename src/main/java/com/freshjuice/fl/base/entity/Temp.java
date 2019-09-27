package com.freshjuice.fl.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

public class Temp implements Serializable {

    @TableId(type= IdType.ID_WORKER)
    private Long id;
    private String tempName;
    private Integer tempNum;
    private String tempDt;
    private String tempD;
    private String tempT;
    private String deleteFlag;
    private String createTime;
    private String updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTempName() {
        return tempName;
    }

    public void setTempName(String tempName) {
        this.tempName = tempName;
    }

    public Integer getTempNum() {
        return tempNum;
    }

    public void setTempNum(Integer tempNum) {
        this.tempNum = tempNum;
    }

    public String getTempDt() {
        return tempDt;
    }

    public void setTempDt(String tempDt) {
        this.tempDt = tempDt;
    }

    public String getTempD() {
        return tempD;
    }

    public void setTempD(String tempD) {
        this.tempD = tempD;
    }

    public String getTempT() {
        return tempT;
    }

    public void setTempT(String tempT) {
        this.tempT = tempT;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
