package com.roulette.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.roulette.persistence.entity.Roulette;

@Configuration
public class RedisConfiguration {	
	@Bean
	public RedisConnectionFactory getConnectionFactory() {
		
		return new JedisConnectionFactory();
	}
	@Bean
	public RedisTemplate<String, Roulette> redisTemplate(){
		final RedisTemplate<String, Roulette> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(getConnectionFactory());
		
		return redisTemplate;
	}
}
