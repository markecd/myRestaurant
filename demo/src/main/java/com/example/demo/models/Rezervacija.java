package com.example.demo.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Rezervacija {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCas_rezervacije() {
        return cas_rezervacije;
    }

    public void setCas_rezervacije(LocalDateTime cas_rezervacije) {
        this.cas_rezervacije = cas_rezervacije;
    }

    private LocalDateTime cas_rezervacije;

    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_miza")
	@JsonIgnore
	private Miza miza;

    public Miza getMiza() {
        return miza;
    }

    public void setMiza(Miza miza) {
        this.miza = miza;
    }
}
