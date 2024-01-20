package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Racun {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_narocilo")
	@JsonIgnore
    private Narocilo narocilo;

    public Narocilo getNarocilo() {
        return narocilo;
    }

    public void setNarocilo(Narocilo narocilo) {
        this.narocilo = narocilo;
    }

    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_natakar")
	@JsonIgnore
    private Natakar natakar;

    public Natakar getNatakar() {
        return natakar;
    }

    public void setNatakar(Natakar natakar) {
        this.natakar = natakar;
    }

    private double koncen_znesek;

    public double getKoncen_znesek() {
        return koncen_znesek;
    }

    public void setKoncen_znesek(double koncen_znesek) {
        this.koncen_znesek = koncen_znesek;
    }
}
