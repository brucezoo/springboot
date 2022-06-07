package com.bruce.springboot.model.es;

import com.alibaba.fastjson.annotation.JSONField;
import com.chinacareer.ymtd.es.model.ESId;
import com.chinacareer.ymtd.es.model.ESMetaData;
import lombok.Data;

import java.io.Serializable;

@Data
@ESMetaData(indexName = "gb_jd")
public class JD implements Serializable {
    private static final long serialVersionUID = -4095419419805686546L;

    @ESId
    Integer id;

    String token;

    Short type;

    String name;

    Integer property;

    String advantage;

    @JSONField(name = "function_type")
    String functionType;
}
