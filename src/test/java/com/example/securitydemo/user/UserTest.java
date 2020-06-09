package com.example.securitydemo.user;

import com.example.securitydemo.domian.User;
import com.example.securitydemo.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;

import javax.annotation.Resource;
import javax.crypto.SecretKey;
import java.io.UnsupportedEncodingException;
import java.net.HttpCookie;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author
 * @version 1.0
 * @date 2020/6/4 0004 14:55
 * @description
 **/
@SpringBootTest
public class UserTest {

    @Resource
    private UserRepository userRepository;

    @Resource
    private BCryptPasswordEncoder encoder;

    @Test
    public void save() {
        User user = new User();
        user.setId(1L);
        user.setUsername("admin");
        String encode = encoder.encode("123");
        System.out.println(encode);
        user.setPassword(encode);

        userRepository.save(user);

    }

    @Test
    public void encoder(){
        String encode = encoder.encode("122323");
        System.out.println(encode);
        String encode1 = encoder.encode("122323");
        System.out.println(encode1);
        boolean matches = encoder.matches(encode1, encode);
        System.out.println(matches);

    }

    @Test
    public void connectionleak(){
        Object lock = new Object();
        long min = 60000L;
        long tensec = 10000;
        for (int i=0;i<12;i++){
            System.out.println("start "+(i+1)+":"+LocalDateTime.now());
            try {
                userRepository.findByUsername("admin");
                synchronized (lock) {
                    lock.wait((long)(5.5*min)+(i+1)*tensec);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("end "+(i+1)+":"+LocalDateTime.now());
        }

    }

    @Test
    public void deserialize(){
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
////        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        final PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder().allowIfBaseType(Object.class).build();
//        om.activateDefaultTyping(ptv,ObjectMapper.DefaultTyping.NON_FINAL);
//
//        String json = "[\"org.springframework.security.web.savedrequest.DefaultSavedRequest\",{\"cookies\":[\"java.util.ArrayList\",[[\"javax.servlet.http.Cookie\",{\"name\":\"Idea-f60c4641\",\"value\":\"7d08f0ba-5634-4961-9447-00c672dd8b4a\",\"version\":0,\"comment\":null,\"domain\":null,\"maxAge\":-1,\"path\":null,\"secure\":false,\"httpOnly\":false}],[\"javax.servlet.http.Cookie\",{\"name\":\"Hm_lvt_dde6ba2851f3db0ddc415ce0f895822e\",\"value\":\"1576216955\",\"version\":0,\"comment\":null,\"domain\":null,\"maxAge\":-1,\"path\":null,\"secure\":false,\"httpOnly\":false}],[\"javax.servlet.http.Cookie\",{\"name\":\"JSESSIONID\",\"value\":\"67BE78A53DF4BF46CD9323DE6AB93A7E\",\"version\":0,\"comment\":null,\"domain\":null,\"maxAge\":-1,\"path\":null,\"secure\":false,\"httpOnly\":false}],[\"javax.servlet.http.Cookie\",{\"name\":\"SESSION\",\"value\":\"ZTExMzZkZjgtZTQzYS00ODUyLTljMzctMzkxZTRiMjQ1YmU1\",\"version\":0,\"comment\":null,\"domain\":null,\"maxAge\":-1,\"path\":null,\"secure\":false,\"httpOnly\":false}]]],\"locales\":[\"java.util.ArrayList\",[\"zh_CN\",\"zh\"]],\"headers\":[\"java.util.TreeMap\",{\"accept\":[\"java.util.ArrayList\",[\"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\"]],\"accept-encoding\":[\"java.util.ArrayList\",[\"gzip, deflate, br\"]],\"accept-language\":[\"java.util.ArrayList\",[\"zh-CN,zh;q=0.9\"]],\"cache-control\":[\"java.util.ArrayList\",[\"no-cache\"]],\"connection\":[\"java.util.ArrayList\",[\"keep-alive\"]],\"cookie\":[\"java.util.ArrayList\",[\"Idea-f60c4641=7d08f0ba-5634-4961-9447-00c672dd8b4a; Hm_lvt_dde6ba2851f3db0ddc415ce0f895822e=1576216955; JSESSIONID=67BE78A53DF4BF46CD9323DE6AB93A7E; SESSION=ZTExMzZkZjgtZTQzYS00ODUyLTljMzctMzkxZTRiMjQ1YmU1\"]],\"host\":[\"java.util.ArrayList\",[\"localhost:9090\"]],\"pragma\":[\"java.util.ArrayList\",[\"no-cache\"]],\"sec-fetch-mode\":[\"java.util.ArrayList\",[\"navigate\"]],\"sec-fetch-site\":[\"java.util.ArrayList\",[\"none\"]],\"sec-fetch-user\":[\"java.util.ArrayList\",[\"?1\"]],\"upgrade-insecure-requests\":[\"java.util.ArrayList\",[\"1\"]],\"user-agent\":[\"java.util.ArrayList\",[\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36\"]]}],\"parameters\":[\"java.util.TreeMap\",{}],\"contextPath\":\"\",\"method\":\"GET\",\"pathInfo\":null,\"queryString\":null,\"requestURI\":\"/hello\",\"requestURL\":\"http://localhost:9090/hello\",\"scheme\":\"http\",\"serverName\":\"localhost\",\"servletPath\":\"/hello\",\"serverPort\":9090,\"parameterNames\":[\"java.util.TreeMap$KeySet\",[]],\"parameterMap\":[\"java.util.TreeMap\",{}],\"headerNames\":[\"java.util.TreeMap$KeySet\",[\"accept\",\"accept-encoding\",\"accept-language\",\"cache-control\",\"connection\",\"cookie\",\"host\",\"pragma\",\"sec-fetch-mode\",\"sec-fetch-site\",\"sec-fetch-user\",\"upgrade-insecure-requests\",\"user-agent\"]],\"redirectUrl\":\"http://localhost:9090/hello\"}]";
//        DefaultSavedRequest deserialize = (DefaultSavedRequest)jackson2JsonRedisSerializer.deserialize(json.getBytes());
//        System.out.println(deserialize);

        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        User user = new User();
        user.setId(1L);
        user.setUsername("rrr");
        user.setPassword("123");
        byte[] serialize = genericJackson2JsonRedisSerializer.serialize(user);
        String s = null;
        try {
            s = new String(serialize, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(s);

        Object deserialize = genericJackson2JsonRedisSerializer.deserialize(serialize);
        System.out.println(deserialize);

        System.out.println(deserialize);

        HttpCookie httpCookie = new HttpCookie("test","testValue");
        httpCookie.setVersion(1);

        byte[] serialize1 = genericJackson2JsonRedisSerializer.serialize(httpCookie);
        s = null;
        try {
            s = new String(serialize1, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(s);

        Object deserialize1 = genericJackson2JsonRedisSerializer.deserialize(serialize1);
        System.out.println(deserialize1);
    }

    @Test
    public void jwt(){
        SecretKey key = Keys.hmacShaKeyFor("RA1jvwoDtzFCi2JgVAHGNoxvPVUu/uBVnjjh215wpGQ=".getBytes());
        String token = Jwts.builder().setSubject("linlong").signWith(key).compact();
        System.out.println(token);

    }

}
