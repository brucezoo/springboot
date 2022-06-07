package com.bruce.springboot.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Configuration
public class WebMvcConfiguration {
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return jacksonObjectMapperBuilder -> {
            // 为 String 类型自定义反序列化操作
            jacksonObjectMapperBuilder
                    .deserializerByType(String.class, new StdScalarDeserializer<String>(String.class) {
                        private static final long serialVersionUID = 2808681827191594573L;

                        @Override
                        public String deserialize(JsonParser jsonParser, DeserializationContext ctx)
                                throws IOException {
                            // 去除前后空格
                            return StringUtils.trimWhitespace(jsonParser.getValueAsString());
                        }
                    });
        };
    }
}
