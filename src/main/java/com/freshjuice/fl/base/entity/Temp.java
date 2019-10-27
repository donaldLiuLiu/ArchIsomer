package com.freshjuice.fl.base.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author freshjuice
 * @since 2019-10-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Temp extends BaseEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tempName;

    private Integer tempNum;

    /*@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")*/
    @JsonFormat(pattern= "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime tempDt;

    /*@DateTimeFormat(pattern = "yyyy-MM-dd")*/
    @JsonFormat(pattern= "yyyy-MM-dd",timezone = "GMT+8")
    private LocalDate tempD;

    /*@DateTimeFormat(pattern = "HH:mm:ss")*/
    @JsonFormat(pattern= "HH:mm:ss",timezone = "GMT+8")
    private LocalTime tempT;

    private String deleteFlag;


}
