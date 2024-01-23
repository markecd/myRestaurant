package com.example.demo.services;

import com.example.demo.models.Miza;
import com.example.demo.models.Rezervacija;


import com.example.demo.dao.MizaRepository;
import com.example.demo.dao.RezervacijaRepository;
import com.example.demo.dto.RezervacijaDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RezervacijaService {


    private final RezervacijaRepository rezervacijaRepository;

    @Autowired
    public RezervacijaService(RezervacijaRepository rezervacijaRepository) {
        this.rezervacijaRepository = rezervacijaRepository;
    }

    public Iterable<Rezervacija> vrniVseRezervacije() {
        return rezervacijaRepository.findAll();
    }

    public Rezervacija vrniRezervacijoPoId(Long id) {
        return rezervacijaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Rezervacija z ID " + id + " ne obstaja."));
    }

    @Autowired
    private MizaRepository mizaRepository;
    
    public Rezervacija vstaviRezervacijo(RezervacijaDTO rezervacija, Long id_miza) {
        Rezervacija rezervacijaInsert = new Rezervacija();
        rezervacijaInsert.setCas_rezervacije(rezervacija.getCas_rezervacije());
        rezervacijaInsert.setIme_priimek(rezervacija.getIme_priimek());
        Miza miza = mizaRepository.findById(id_miza).orElseThrow();
        rezervacijaInsert.setMiza(miza);
        
        return rezervacijaRepository.save(rezervacijaInsert);
    }

    public Rezervacija posodobiPodatkeRezervacije(Long id, Rezervacija novaRezervacija) {
        Optional<Rezervacija> rezervacijaOptional = rezervacijaRepository.findById(id);

        if (rezervacijaOptional.isPresent()) {
            Rezervacija rezervacijaZaUpdate = rezervacijaOptional.get();
            rezervacijaZaUpdate.setCas_rezervacije(novaRezervacija.getCas_rezervacije());


            return rezervacijaRepository.save(rezervacijaZaUpdate);
        } else {
            throw new IllegalArgumentException("Rezervacija z ID " + id + " ne obstaja.");
        }
    }

    public void izbrisiRezervacijo(Long id) {
        if (rezervacijaRepository.existsById(id)) {
            rezervacijaRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Rezervacija z ID " + id + " ne obstaja.");
        }
    }

}
