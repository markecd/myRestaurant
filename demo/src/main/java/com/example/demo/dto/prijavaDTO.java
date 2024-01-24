package com.example.demo.dto;

public class prijavaDTO {

    public prijavaDTO(boolean uspesnaPrijava, String zeton, Long id){
        this.zeton = zeton;
        this.uspesnaPrijava = uspesnaPrijava;
        this.id = id;
    }
    private Long id;
    
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public boolean isUspesnaPrijava() {
        return uspesnaPrijava;
    }
    public void setUspesnaPrijava(boolean uspesnaPrijava) {
        this.uspesnaPrijava = uspesnaPrijava;
    }
    private boolean uspesnaPrijava;
    private String zeton;
    public String getPozicija() {
        return zeton;
    }
    public void setPozicija(String zeton) {
        this.zeton = zeton;
    }
}
