package com.wchallenge.forum.infrastructure.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients("com.wchallenge.forum.infrastructure.adapter.jsonplaceholder.feign")
public class FeignClientConfig {

}
