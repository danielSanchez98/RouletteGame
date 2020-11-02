package com.roulette.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.roulette.persistence.crud.RouletteRepository;
import com.roulette.persistence.entity.Roulette;

@RestController
@RequestMapping("/api")
public class RouletteController {
	
	private RouletteRepository rouletteRepository;
	
	public RouletteController(RouletteRepository rouletteRepository) {
		this.rouletteRepository = rouletteRepository;
	}
	@PostMapping("/roulette/create")
	public String createNewRoulette() {
		try {
			Roulette roulette = new Roulette(UUID.randomUUID().toString(),"closed");
			
			return rouletteRepository.save(roulette).getId();
		} catch (Exception e) {
			return "";
		}
		
	}	
	@GetMapping("/roulette/list")
	public List<?> getAllRoulletes(){

		List roulettesAndStatusList = new ArrayList<>();
		try {
			HashMap<String, String> roulettesAndStatus = new HashMap<String, String>();
			List<Roulette> roulettesCreated = rouletteRepository.findAll();		
			for (int i = 0; i < roulettesCreated.size(); i++) {
				roulettesAndStatus.put("roulette", roulettesCreated.get(i).getId());
				roulettesAndStatus.put("status", roulettesCreated.get(i).getIsAvailable());
				roulettesAndStatusList.add(roulettesAndStatus);
				roulettesAndStatus = new HashMap<String, String>();
			}
			
			return roulettesAndStatusList;			
		} catch (Exception e) {
			
			return roulettesAndStatusList;
		}		
	}
	@PostMapping("/roulette/open/{id}")
	public Boolean openRouletteById(@PathVariable String id) {
		try {
			Roulette roulette = rouletteRepository.findById(id);
			roulette.setIsAvailable("opened");
			rouletteRepository.update(roulette);
			
			return true;			
		} catch (Exception e) {
			
			return false;
		}
	}
	
}
