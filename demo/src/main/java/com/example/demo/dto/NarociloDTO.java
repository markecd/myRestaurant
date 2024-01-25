package com.example.demo.dto;

import java.time.LocalDateTime;
import com.example.demo.models.STANJE_NAROCILO;
import java.util.*;

/*
 * DATA TRANSFER OBJECT prilagodim, da lahko obdelujem nestrukturirana json telesa 
 */
public class NarociloDTO {
    public LocalDateTime getCasRezervacije() {
        return casRezervacije;
    }
    public void setCasRezervacije(LocalDateTime casRezervacije) {
        this.casRezervacije = casRezervacije;
    }
    private LocalDateTime casRezervacije;
    public List<Long> getIzdelkiIds() {
        return izdelkiIds;
    }
    public void setIzdelkiIds(List<Long> izdelkiIds) {
        this.izdelkiIds = izdelkiIds;
    }
    private List<Long> izdelkiIds;
}
