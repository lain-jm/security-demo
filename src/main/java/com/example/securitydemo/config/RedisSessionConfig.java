package com.example.securitydemo.config;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author
 * @version 1.0
 * @date 2020/6/8 0008 15:38
 * @description
 **/
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1900)
@Slf4j
public class RedisSessionConfig {

//    @Bean("springSessionDefaultRedisSerializer")
//    public RedisSerializer<Object> defaultRedisSerializer(){
//        log.debug("自定义Redis Session序列化加载成功");
//        return valueSerializer();
//    }
//
//    private RedisSerializer<Object> valueSerializer() {
//        ObjectMapper om = new ObjectMapper();
//        om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        om.registerModule(new JavaTimeModule());
//        om.registerModule((new SimpleModule()));
////                .addSerializer(NullSerializer.instance));
//
////        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
//        return new GenericJackson2JsonRedisSerializer(om);
//
//    }


}
