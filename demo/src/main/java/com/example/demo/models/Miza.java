package com.example.demo.models;

import jakarta.persistence.*;


@Entity
public class Miza {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getStevilo_sedezev() {
		return stevilo_sedezev;
	}

	public void setStevilo_sedezev(int stevilo_sedezev) {
		this.stevilo_sedezev = stevilo_sedezev;
	}

	private int stevilo_sedezev;
	private int stevilka_mize;

	public int getStevilka_mize() {
		return stevilka_mize;
	}

	public void setStevilka_mize(int stevilka_mize) {
		this.stevilka_mize = stevilka_mize;
	}

	@Enumerated(EnumType.STRING)
	private STANJE_MIZE stanje_mize;

	public STANJE_MIZE getStanje_mize() {
		return stanje_mize;
	}

	public void setStanje_mize(STANJE_MIZE stanje_mize) {
		this.stanje_mize = stanje_mize;
	}

}
