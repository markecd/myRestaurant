package com.example.demo.models;

import jakarta.persistence.*;

@Entity
public class Natakar {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getIme() {
        return ime;
    }
    public void setIme(String ime) {
        this.ime = ime;
    }
    private String ime;
    public String getPriimek() {
        return priimek;
    }
    public void setPriimek(String priimek) {
        this.priimek = priimek;
    }
    private String priimek;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    private String username;

    public String getGeslo() {
        return geslo;
    }
    public void setGeslo(String geslo) {
        this.geslo = geslo;
    }
    private String geslo;

}
