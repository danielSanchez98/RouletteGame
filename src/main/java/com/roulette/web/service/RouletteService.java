package com.roulette.web.service;

import java.util.List;

import com.roulette.persistence.crud.RouletteRepository;
import com.roulette.persistence.entity.Roulette;
import com.roulette.persistence.entity.RouletteBets;
import com.roulette.persistence.entity.WinnerResult;

public interface RouletteService {

	List getRoulettesAndStatus(List<Roulette> roulettesCreated);
	String checkConstraintsToBet(Roulette roulette, RouletteBets rouletteBet);
	String setNumberToBet(Roulette roulette, RouletteBets rouletteBet, RouletteRepository rouletteRepository);
	String setColorToBet(Roulette roulette ,RouletteBets rouletteBet, RouletteRepository rouletteRepository);
	WinnerResult setWinnerResultByNumberAndColor(WinnerResult winnerResult, List<RouletteBets> betsCreated, int currentBet);
}
