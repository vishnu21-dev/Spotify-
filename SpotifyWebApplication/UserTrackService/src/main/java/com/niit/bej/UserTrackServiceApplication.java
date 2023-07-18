package com.niit.bej;

import com.niit.bej.filter.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class UserTrackServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserTrackServiceApplication.class, args);
    }
    @Bean
    public FilterRegistrationBean<JwtFilter> registerFilterBean() {
        FilterRegistrationBean<JwtFilter> jwtFilterBean = new FilterRegistrationBean<>();
        jwtFilterBean.setFilter(new JwtFilter());

        jwtFilterBean.addUrlPatterns("/userTrack/user/*");
        return jwtFilterBean;
    }


}
