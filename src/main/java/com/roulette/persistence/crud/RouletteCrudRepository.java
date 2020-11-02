package com.roulette.persistence.crud;

import java.util.List;

import com.roulette.persistence.entity.Roulette;

public interface RouletteCrudRepository {
	List<Roulette> findAll();
	Roulette findById(String idRoulette);
	Roulette save(Roulette roulette);
	
}
