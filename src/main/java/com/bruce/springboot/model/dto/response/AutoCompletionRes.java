package com.bruce.springboot.model.dto.response;

import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
public class AutoCompletionRes implements Serializable {
    private static final long serialVersionUID = 407629812625495627L;

    private Boolean success;

    private List<Data> data;

    @lombok.Data
    public static class Data {
        private String name;

        private String type;
    }
}
