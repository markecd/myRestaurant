package com.example.demo.dto;


import java.time.LocalDateTime;

public class RezervacijaDTO {

    private LocalDateTime cas_rezervacije;
    private String ime_priimek;
    private Long id_miza;

    public LocalDateTime getCas_rezervacije() {
        return cas_rezervacije;
    }
    public void setCas_rezervacije(LocalDateTime cas_rezervacije) {
        this.cas_rezervacije = cas_rezervacije;
    }
    public String getIme_priimek() {
        return ime_priimek;
    }
    public void setIme_priimek(String ime_priimek) {
        this.ime_priimek = ime_priimek;
    }
    public Long getId_miza() {
        return id_miza;
    }
    public void setId_miza(Long id_miza) {
        this.id_miza = id_miza;
    }
}
