package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Arrays;
import java.util.List;

/**
 * constoller跳jsp路径辅助类
 */
@Configuration
public class UrlMvcConfig implements WebMvcConfigurer {
    @Autowired
    AdminInterceptor adminInterceptor;
    //排除拦截的静态资源
    final List<String> staticUrl = Arrays.asList("/css/**","/images/**","/js/**","/layui/**","/localjs/**","/src/**","/addressAPI/**","/bootstrap/**");

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        /* 是否通过请求Url的扩展名来决定media type */
        configurer.favorPathExtension(true)
                /* 不检查Accept请求头 */
                .ignoreAcceptHeader(true)
                .parameterName("mediaType")
                /* 设置默认的media yype */
                .defaultContentType(MediaType.TEXT_HTML)
                /* 请求以.html结尾的会被当成MediaType.TEXT_HTML*/
                .mediaType("html", MediaType.TEXT_HTML)
                /* 请求以.json结尾的会被当成MediaType.APPLICATION_JSON*/
                .mediaType("json", MediaType.APPLICATION_JSON);
    }
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/jsp/", ".jsp");
        registry.enableContentNegotiation(new MappingJackson2JsonView());
    }


    /**
     * 拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(adminInterceptor);
        //排除拦截
        addInterceptor.excludePathPatterns("/login.html");
        addInterceptor.excludePathPatterns("/register");
        addInterceptor.excludePathPatterns("/kaptcha");
        addInterceptor.excludePathPatterns("/*.do");
        addInterceptor.excludePathPatterns(staticUrl);
        // 拦截配置
        addInterceptor.addPathPatterns("/**");
    }
}
