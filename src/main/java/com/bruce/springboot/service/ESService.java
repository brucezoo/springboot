package com.bruce.springboot.service;

import com.bruce.springboot.model.es.JD;
import com.chinacareer.ymtd.common.exception.BizException;
import com.chinacareer.ymtd.common.response.PageResponse;
import com.chinacareer.ymtd.es.client.IEsService;
import com.chinacareer.ymtd.es.model.ESPage;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@Service("springbootEsService")
@Slf4j
public class ESService {
    @Resource
    private IEsService iEsService;

    /**
     * 限流策略 ：1秒钟2个请求
     */
    private final RateLimiter limiter = RateLimiter.create(2.0);

    public PageResponse<JD> getJDsFromES() throws Exception {
        limitRequest();

        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.filter(QueryBuilders.wildcardQuery("name", "*代表*"));

        SearchSourceBuilder searchBuilder = new SearchSourceBuilder();
        searchBuilder.query(boolQueryBuilder);

        return iEsService.searchPage(
                searchBuilder,
                JD.class,
                new ESPage()
        );
    }

    private void limitRequest() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 500毫秒内，没拿到令牌，就直接进入服务降级
        boolean tryAcquire = limiter.tryAcquire(500, TimeUnit.MILLISECONDS);

        if (!tryAcquire) {
            log.warn("进入服务降级，时间{}", LocalDateTime.now().format(dtf));
            throw new BizException("当前排队人数较多，请稍后再试！");
        }

        log.info("获取令牌成功，时间{}", LocalDateTime.now().format(dtf));
    }
}
