package com.example.demo.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
 
@Entity
public class Narocilo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private LocalDateTime cas_rezervacije;

    public LocalDateTime getCas_rezervacije() {
        return cas_rezervacije;
    }

    public void setCas_rezervacije(LocalDateTime cas_rezervacije) {
        this.cas_rezervacije = cas_rezervacije;
    }

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(
        name = "izdelek_narocilo",
        joinColumns = @JoinColumn(name = "narocilo_id"),
        inverseJoinColumns = @JoinColumn(name = "izdelek_id")
    )
    private List<Izdelek> izdelki = new ArrayList<>();

    public List<Izdelek> getIzdelki() {
        return izdelki;
    }

    public void setIzdelki(List<Izdelek> izdelki) {
        this.izdelki = izdelki;
    }

    @OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_miza")
	@JsonIgnore
	private Miza miza;

    public Miza getMiza() {
        return miza;
    }

    public void setMiza(Miza miza) {
        this.miza = miza;
    }

    public STANJE_NAROCILO getStanje_narocila() {
        return stanje_narocila;
    }

    public void setStanje_narocila(STANJE_NAROCILO stanje_narocila) {
        this.stanje_narocila = stanje_narocila;
    }

    @Enumerated(EnumType.STRING)
    private STANJE_NAROCILO stanje_narocila;

}
