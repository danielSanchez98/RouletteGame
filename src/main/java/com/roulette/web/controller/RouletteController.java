package com.roulette.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.roulette.persistence.crud.RouletteRepository;
import com.roulette.persistence.entity.Roulette;
import com.roulette.persistence.entity.RouletteBets;

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
			
			return roulettesCreated;			
		} catch (Exception e) {
			
			return roulettesAndStatusList;
		}		
	}
	@PostMapping("/roulette/open/{idRoulette}")
	public Boolean openRouletteById(@PathVariable String idRoulette) {
		try {
			Roulette roulette = rouletteRepository.findById(idRoulette);
			if(roulette != null) {
				roulette.setIsAvailable("opened");
				rouletteRepository.update(roulette);
				return true;	
			}
			
			return false;
					
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	@PostMapping("/roulette/bet/{idRoulette}")
	public String betAmmountByColorOrNumber(@PathVariable String idRoulette, @RequestHeader("idUser") String idUser, @RequestBody RouletteBets rouletteBetBody) {
		String message = "";
		try {
			RouletteBets rouletteBet = rouletteBetBody;
			rouletteBet.setIdUsuario(idUser);
			rouletteBet.getTypeOfBet();
			Roulette roulette = rouletteRepository.findById(idRoulette);			
			if(roulette == null) {
				message = "This roulette does no exist.";
				
				return message;
			}
			if(roulette.getIsAvailable().equals("closed")) {
				message = "This roulette is not opened.";
				
				return message;
			}						
			if(rouletteBet.getAmmount() > 10000) {
				message = "the value exceed the maximun bet";
				
				return message;
			}
			switch (rouletteBet.getTypeOfBet()) {
			case "numero":
				int numberBetted = rouletteBet.getNumberBetted();
				if(!(numberBetted>=0 && numberBetted <= 36)) {
					message = "The number bet is not in the range.";
					
					return message;
				}
				roulette.getRouletteBets().add(rouletteBet);
				rouletteRepository.update(roulette);
				message = "Bet created for number.";				
				break;
			case "color":
				String colorBet = rouletteBet.getColorBetted();
				if((!colorBet.equals("negro") && !colorBet.equals("rojo"))) {
					message = "This color is not an option.";
					
					return message;					
				}
				roulette.getRouletteBets().add(rouletteBet);
				rouletteRepository.update(roulette);
				message = "Bet created for color.";
				break;

			default:
				break;
			}
			
			return message;			
		} catch (Exception e) {
			return "error:"+e.toString();
		}
	}
}
