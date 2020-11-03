package com.roulette.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.roulette.persistence.entity.WinnerResult;
import com.roulette.web.service.RouletteServiceImpl;

@RestController
@RequestMapping("/api")
public class RouletteController {
	
	private RouletteRepository rouletteRepository;
	@Autowired
	private RouletteServiceImpl rouletteService;
	
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
			List<Roulette> roulettesCreated = rouletteRepository.findAll();		
			roulettesAndStatusList = rouletteService.getRoulettesAndStatus(roulettesCreated);
			
			return roulettesAndStatusList;			
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
			message = rouletteService.checkConstraintsToBet(roulette, rouletteBet);
			if(!message.equals("OK")) {
				
				return message;
			}			
			switch (rouletteBet.getTypeOfBet()) {
			case "numero":
				message = rouletteService.setNumberToBet(roulette, rouletteBet, rouletteRepository);				
				break;
			case "color":
				message = rouletteService.setColorToBet(roulette, rouletteBet, rouletteRepository);
				break;
			default:
				break;
			}
			
			return message;			
		} catch (Exception e) {
			
			return "error:"+e.toString();
		}
	}
	
	@PostMapping("/roulette/close/{idRoulette}")
	public List<WinnerResult> closeRouletteAndgetWinners(@PathVariable String idRoulette){
		List<WinnerResult> winnersList = new ArrayList<>();
		int winnerNumber = (int)(Math.random()*36);
		String winnerColor = (winnerNumber % 2 == 0) ?"rojo":"negro";
		try {
			Roulette roulette = rouletteRepository.findById(idRoulette);
			if(roulette == null) {
				
				return winnersList;
			}
			roulette.setIsAvailable("closed");
			rouletteRepository.update(roulette);			
			List<RouletteBets> betsCreated = roulette.getRouletteBets();			
			WinnerResult winnerResult = new WinnerResult();
			winnerResult.setWinnerNumber(winnerNumber);
			for (int i = 0; i < betsCreated.size(); i++) {
				if(betsCreated.get(i).getNumberBetted() == winnerNumber || betsCreated.get(i).getColorBetted().equals(winnerColor) ) {
					winnerResult = rouletteService.setWinnerResultByNumberAndColor(winnerResult, betsCreated, i);
					winnersList.add(winnerResult);
					winnerResult = new WinnerResult(); 					
				}
			}
			
			return winnersList;
			
		} catch (Exception e) {
			
			return winnersList;
		}
	}
}
