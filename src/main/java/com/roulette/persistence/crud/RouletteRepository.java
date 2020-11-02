package com.roulette.persistence.crud;

import java.util.List;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import com.roulette.persistence.entity.Roulette;

@Repository
public class RouletteRepository implements RouletteCrudRepository  {
	private static final String KEY = "Roulette";
	private RedisTemplate<String, Roulette> redisTemplate;
	private HashOperations hashOperations;
	public RouletteRepository(RedisTemplate<String, Roulette> redisTemplate) {
		this.redisTemplate = redisTemplate;
		this.hashOperations = redisTemplate.opsForHash();
	}
	@Override
	public List<Roulette> findAll() {
		
		return hashOperations.values(KEY);
	}
	@Override
	public Roulette findById(String idRoulette) {
		
		return (Roulette) hashOperations.get(KEY, idRoulette);
	}
	@Override
	public Roulette save(Roulette roulette) {
		hashOperations.put(KEY, roulette.getId(), roulette);
		
		return roulette;		
	}
	@Override
	public Roulette update(Roulette roulette) {
		
		return save(roulette);
	}	
}
