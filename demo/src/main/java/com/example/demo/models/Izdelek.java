package com.example.demo.models;

import jakarta.persistence.*;

@Entity
public class Izdelek {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNaziv() {
        return naziv;
    }
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    private String naziv;
    public double getCena() {
        return cena;
    }
    public void setCena(double cena) {
        this.cena = cena;
    }
    private double cena;

    @Enumerated(EnumType.STRING)
    private TIP_IZDELKA tip_izdelka;

    public TIP_IZDELKA getTip_izdelka() {
        return tip_izdelka;
    }
    public void setTip_izdelka(TIP_IZDELKA tip_izdelka) {
        this.tip_izdelka = tip_izdelka;
    }
}
