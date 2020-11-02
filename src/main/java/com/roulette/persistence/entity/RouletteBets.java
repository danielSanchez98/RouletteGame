package com.roulette.persistence.entity;

import java.io.Serializable;

public class RouletteBets implements Serializable {
	private String idUsuario;
	private Integer ammount;
	private Integer numberBetted;
	private String colorBetted;
	private String typeOfBet;
	public String getIdUsuario() {
		
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {		
		this.idUsuario = idUsuario;
	}
	public Integer getAmmount() {
		
		return ammount;
	}
	public void setAmmount(Integer ammount) {		
		this.ammount = ammount;
	}
	public Integer getNumberBetted() {
		
		return numberBetted;
	}
	public void setNumberBetted(Integer numberBetted) {
		this.numberBetted = numberBetted;
	}
	public String getColorBetted() {
		
		return colorBetted;
	}
	public void setColorBetted(String colorBetted) {
		this.colorBetted = colorBetted;
	}
	public String getTypeOfBet() {
		
		return typeOfBet;
	}
	public void setTypeOfBet(String typeOfBet) {
		this.typeOfBet = typeOfBet;
	}
	

}
