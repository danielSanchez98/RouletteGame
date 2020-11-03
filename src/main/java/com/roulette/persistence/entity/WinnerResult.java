package com.roulette.persistence.entity;

public class WinnerResult {
	
	private String idUsuario;
	private Integer betAmmount;
	private String betOption;
	private String typeOfBet;
	private Integer winnerNumber;
	private Double ammountWon;
	
	
	
	public WinnerResult() {
	}
	public String getIdUsuario() {
		
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Integer getBetAmmount() {
		
		return betAmmount;
	}
	public void setBetAmmount(Integer betAmmount) {
		this.betAmmount = betAmmount;
	}
	public String getBetOption() {
		
		return betOption;
	}
	public void setBetOption(String betOption) {
		this.betOption = betOption;
	}
	public String getTypeOfBet() {
		
		return typeOfBet;
	}
	public void setTypeOfBet(String typeOfBet) {
		this.typeOfBet = typeOfBet;
	}
	public Double getAmmountWon() {
		
		return ammountWon;
	}
	public void setAmmountWon(Double ammountWon) {
		this.ammountWon = ammountWon;
	}
	public Integer getWinnerNumber() {
		
		return winnerNumber;
	}
	public void setWinnerNumber(Integer winnerNumber) {
		this.winnerNumber = winnerNumber;
	}
	

}
