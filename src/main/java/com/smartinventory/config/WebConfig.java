package com.smartinventory.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@Configuration
@EnableSpringDataWebSupport(
        pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO
)
public class WebConfig {
    // Reserved for future:
    // - Argument resolvers
    // - Formatters
    // - Interceptors
}
