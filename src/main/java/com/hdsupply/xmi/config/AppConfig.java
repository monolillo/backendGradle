package com.hdsupply.xmi.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc 
@EnableCaching
@ComponentScan("com.hdsupply.xmi")
public class AppConfig implements WebMvcConfigurer {
	
	@Bean	
	public ThreadPoolTaskExecutor taskExecutorKinesis() {
		ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
		pool.setCorePoolSize(5);
		pool.setMaxPoolSize(10);
		pool.setAwaitTerminationSeconds(5);
		pool.setWaitForTasksToCompleteOnShutdown(true);
		return pool;
	}	

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("policies", "bluFis", "beacons");
    }		
    
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
      configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }
	
}
