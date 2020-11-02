package com.roulette.persistence.entity;

import java.util.List;

public class Roulette {	
	private String id;
	private Boolean isAvailable;
	private Integer winnerNumber;
	private List<RouletteBets> rouletteBets;
	public String getId() {
		
		return id;
	}
	public void setId(String id) {
		
		this.id = id;
	}
	public Boolean getIsAvailable() {
		
		return isAvailable;
	}
	public void setIsAvailable(Boolean isAvailable) {
		
		this.isAvailable = isAvailable;
	}
	public Integer getWinnerNumber() {
		
		return winnerNumber;
	}
	public void setWinnerNumber(Integer winnerNumber) {
		
		this.winnerNumber = winnerNumber;
	}
	public List<RouletteBets> getRouletteBets() {
		
		return rouletteBets;
	}
	public void setRouletteBets(List<RouletteBets> rouletteBets) {
		
		this.rouletteBets = rouletteBets;
	}
	

}
