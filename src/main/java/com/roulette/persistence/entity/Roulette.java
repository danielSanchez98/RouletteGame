package com.roulette.persistence.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Roulette implements Serializable {	
	private String id;
	private String isAvailable;
	private Integer winnerNumber;
	private List<RouletteBets> rouletteBets;
	
	public Roulette(String id, String isAvailable) {
		this.id = id;
		this.isAvailable = isAvailable;
		this.winnerNumber = -1;
		this.rouletteBets = new ArrayList<>();
	}
	public String getId() {
		
		return id;
	}
	public void setId(String id) {
		
		this.id = id;
	}
	public String getIsAvailable() {
		
		return isAvailable;
	}
	public void setIsAvailable(String isAvailable) {
		
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
