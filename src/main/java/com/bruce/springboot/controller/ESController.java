package com.bruce.springboot.controller;

import com.bruce.springboot.annotation.LimitRequest;
import com.bruce.springboot.model.es.JD;
import com.bruce.springboot.response.ApiResponse;
import com.bruce.springboot.service.ESService;
import com.chinacareer.ymtd.common.response.PageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/es")
public class ESController {
    @Resource(name = "springbootEsService")
    ESService esService;

    @GetMapping("/getJDsFromES")
    @LimitRequest
    public ApiResponse<PageResponse<JD>> getJDsFromES() throws Exception {
        PageResponse<JD> response = esService.getJDsFromES();
        return ApiResponse.success(response);
    }
}
