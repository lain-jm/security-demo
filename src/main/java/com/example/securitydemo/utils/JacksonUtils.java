package com.example.securitydemo.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author
 * @version 1.0
 * @date 2020/6/10 0010 17:57
 * @description
 **/
public class JacksonUtils {

    private static ObjectMapper mapper;

    private static ObjectMapper getInstance() {
        if (mapper == null) {
            synchronized(JacksonUtils.class) {
                if (mapper == null) {
                    mapper = new ObjectMapper();
                    //设置时区
                    mapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                    //所有属性都转为json
                    mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
                    //属性null不报错
                    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
                    //json属性在实体类中不存在不报错
                    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                    //不用时间戳
                    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
                    mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
                    //null转为""
                    mapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>()
                    {

                        @Override
                        public void serialize(
                                Object value,
                                JsonGenerator jg,
                                SerializerProvider sp) throws IOException, JsonProcessingException
                        {
                            jg.writeString("");
                        }
                    });
                }
            }
        }
        return mapper;
    }

    /**
     * 对象转为json字符串
     * @param obj
     * @return
     */
    public static String writeValueAsString(Object obj){
        String json = null;
        try {
            json = getInstance().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * json转为相应对象
     * @param json
     * @param var2
     * @param <T>
     * @return
     */
    public static <T> T readValue(String json, Class<T> var2){
        try {
            return getInstance().readValue(json, var2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json转为List<Bean>
     * @param json
     * @param var2
     * @param <T>
     * @return
     */
    public static <T> List<T> readValueToList(String json, Class<T> var2){
        List<T> list = null;
        JavaType javaType = getInstance().getTypeFactory().constructParametricType(List.class, var2);
        try {
            list = getInstance().readValue(json, javaType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * json转为Map<String,Bean>
     * @param json
     * @param var2
     * @param <T>
     * @return
     */
    public static <T> Map<String,T> readValueToMap(String json, Class<T> var2){
        Map<String,T> map = null;
        JavaType javaType = getInstance().getTypeFactory().constructParametricType(Map.class,String.class, var2);
        try {
            map = getInstance().readValue(json, javaType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * list空时需要返回空数组json而不是null的时候
     * @param list
     * @return
     */
    public static String writeListAsString(List list){
        if(list==null){
            return "[]";
        }
        return writeValueAsString(list);
    }

}
