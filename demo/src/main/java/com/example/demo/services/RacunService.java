package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.NarociloRepository;
import com.example.demo.dao.NatakarRepository;
import com.example.demo.dao.RacunRepository;
import com.example.demo.models.Natakar;
import com.example.demo.models.Narocilo;
import com.example.demo.models.Racun;
import com.example.demo.dto.RacunDTO;

@Service
public class RacunService {
    
    private final RacunRepository racunRepository;

    @Autowired
    public RacunService(RacunRepository racunRepository){
        this.racunRepository = racunRepository;
    }

    @Autowired
    private NarociloRepository narociloRepository;

    @Autowired
    private NatakarRepository natakarRepository;

    public Racun vstaviRacun(RacunDTO racunDTO){
        Racun racun = new Racun();

        Optional<Narocilo> narocilo = narociloRepository.findById(racunDTO.getNarociloId());
        Optional<Natakar> natakar = natakarRepository.findById(racunDTO.getNatakarId());
        
        if (!narocilo.isPresent()) {
            throw new IllegalArgumentException("Narocilo z ID " + racunDTO.getNarociloId() + " ne obstaja.");
        }
        if (!natakar.isPresent()) {
            throw new IllegalArgumentException("Natakar z ID " + racunDTO.getNatakarId() + " ne obstaja.");
        }

        Narocilo narociloZaVstaviti = narocilo.get();
        Natakar natakarZaVstaviti = natakar.get();

        racun.setNarocilo(narociloZaVstaviti);
        racun.setNatakar(natakarZaVstaviti);

        racun.setKoncen_znesek(narociloRepository.VrniSkupnoCenoNarocila(racunDTO.getNarociloId()));

        return racunRepository.save(racun);
    }
}
