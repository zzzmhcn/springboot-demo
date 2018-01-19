package com.zmh.springbootdemo.config;

import com.sun.security.ntlm.Server;
import com.zmh.springbootdemo.domain.User;
import com.zmh.springbootdemo.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;

/**
 * 路由器函数
 */
@Configuration
public class RouterFunctionConfiguration {

    /**
     * Servlet
     *  请求接口 ServletRequest or HttpServletRequest
     *  响应接口 ServletResponse or HttpServletResponse
     * Spring 5.0 重新定义了请求接口 响应接口
     *  请求接口 ServerRequest
     *  响应接口 ServerResponse
     *  既可以支持Servlet规范 也可以支持自定义 比如Netty (Web Server)
     * 以本例
     * 定义GET请求，并返回所有的用户对象 URI：/person/find/all
     * Flux 是 0 - N 的对象集合
     * Mono 是 0 - 1 的对象集合
     * Reactive 中的Flux 或者 Mono 它是异步处理（非阻塞）
     * 集合对象基本上是同步处理（阻塞）
     * Flux 或者 Mono 都是 Publisher
     */
    @Bean
    @Autowired
    public RouterFunction<ServerResponse> personFindAll(UserRespository userRespository){



        return RouterFunctions.route(RequestPredicates.GET("/person/find/all"),
                request -> {
                    //返回所有的用户对象
                    Collection<User> users = userRespository.findAll();
                    Flux<User> userFlux = Flux.fromIterable(users);
                    return ServerResponse.ok().body(userFlux,User.class);
                });
    }
}
