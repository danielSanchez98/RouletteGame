package com.roulette.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.roulette.persistence.crud.RouletteRepository;
import com.roulette.persistence.entity.Roulette;
import com.roulette.persistence.entity.RouletteBets;
import com.roulette.persistence.entity.WinnerResult;

@Service
public class RouletteServiceImpl implements RouletteService {

	@Override
	public List getRoulettesAndStatus(List<Roulette> roulettesCreated) {
		List roulettesAndStatusList = new ArrayList<>();
		HashMap<String, String> roulettesAndStatus = new HashMap<String, String>();
		for (int i = 0; i < roulettesCreated.size(); i++) {
			roulettesAndStatus.put("roulette", roulettesCreated.get(i).getId());
			roulettesAndStatus.put("status", roulettesCreated.get(i).getIsAvailable());
			roulettesAndStatusList.add(roulettesAndStatus);
			roulettesAndStatus = new HashMap<String, String>();
		}
		
		return roulettesAndStatusList;
	}

	@Override
	public String checkConstraintsToBet(Roulette roulette, RouletteBets rouletteBet) {
		String message = "";
		if(roulette == null) {
			message = "This roulette does not exist.";
		}else if(roulette.getIsAvailable().equals("closed")) {
			message = "This roulette is not opened.";
		}else if(rouletteBet.getAmmount() > 10000) {
			message = "the value exceed the maximun bet";
		}else {
			message = "OK";			
		}
		
		return message;		
	}

	@Override
	public String setNumberToBet(Roulette roulette,RouletteBets rouletteBet, RouletteRepository rouletteRepository) {
		String message = "";
		int numberBetted = rouletteBet.getNumberBetted();
		if(!(numberBetted>=0 && numberBetted <= 36)) {
			message = "The number bet is not in the range.";			
		}else {
			roulette.getRouletteBets().add(rouletteBet);
			rouletteRepository.update(roulette);
			message = "Bet created for number.";			
		}

		return message;
		
	}

	@Override
	public String setColorToBet(Roulette roulette,RouletteBets rouletteBet, RouletteRepository rouletteRepository) {
		String message = "";
		String colorBet = rouletteBet.getColorBetted();
		if((!colorBet.equals("negro") && !colorBet.equals("rojo"))) {
			message = "This color is not an option.";
		}else {
			roulette.getRouletteBets().add(rouletteBet);
			rouletteRepository.update(roulette);
			message = "Bet created for color.";
		}
		
		return message;
		
	}

	@Override
	public WinnerResult setWinnerResultByNumberAndColor(WinnerResult winnerResult, List<RouletteBets> betsCreated, int currentBet) {		
		if(betsCreated.get(currentBet).getTypeOfBet().equals("numero")) {
			winnerResult.setIdUsuario(betsCreated.get(currentBet).getIdUsuario());
			winnerResult.setBetAmmount(betsCreated.get(currentBet).getAmmount());
			winnerResult.setBetOption(betsCreated.get(currentBet).getNumberBetted().toString());
			winnerResult.setTypeOfBet("numero");
			winnerResult.setAmmountWon(betsCreated.get(currentBet).getAmmount()*5.0);
		}else {
			winnerResult.setIdUsuario(betsCreated.get(currentBet).getIdUsuario());
			winnerResult.setBetAmmount(betsCreated.get(currentBet).getAmmount());
			winnerResult.setBetOption(betsCreated.get(currentBet).getColorBetted());
			winnerResult.setTypeOfBet("color");
			winnerResult.setAmmountWon(betsCreated.get(currentBet).getAmmount()*1.8);						
		}
		return winnerResult;
	}

}
