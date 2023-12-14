package com.bruce.springboot.model.dto.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class AlipayCity implements Serializable {
    private static final long serialVersionUID = -5548147393804795683L;

    @ExcelProperty("code")
    private Integer code;

    @ExcelProperty("name")
    private String name;
}
