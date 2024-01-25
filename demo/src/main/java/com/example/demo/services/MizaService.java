package com.example.demo.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MizaRepository;
import com.example.demo.models.Miza;
import com.example.demo.models.STANJE_MIZE;

import java.util.Optional;

@Service
public class MizaService {

    private final MizaRepository mizaRepository;

    @Autowired
    public MizaService(MizaRepository mizaRepository) {
        this.mizaRepository = mizaRepository;
    }

    public Iterable<Miza> vrniProsteMizePoSteviluSedezev(int stevilo_sedezev) {
        return mizaRepository.vrniProsteMizePoSteviluSedezev(stevilo_sedezev);
    }

    public Miza vstaviMizo(Miza miza) {
        return mizaRepository.save(miza);
    }

    public void izbrisiMizo(Long id){
        if(mizaRepository.existsById(id)){
            mizaRepository.deleteById(id);
            throw new IllegalArgumentException("Izdelek z ID " + id + " ne obstaja.");
        }
    }

    public Iterable<Miza> vrniVseMize() {
        return mizaRepository.findAll();
    }


    public Iterable<Miza> vrniSpecificnoMizo(STANJE_MIZE stanjeMize, int stMize, int stSedezev) {
        return mizaRepository.vrniSpecificnoMizo(stanjeMize, stMize, stSedezev);
    }

    public Miza posodobiMizo(Long id, Miza novaMiza) {
        Optional<Miza> mizaOptional = mizaRepository.findById(id);

        if (mizaOptional.isPresent()) {
            Miza mizaZaUpdate = mizaOptional.get();
            mizaZaUpdate.setStevilo_sedezev(novaMiza.getStevilo_sedezev());
            return mizaRepository.save(mizaZaUpdate);
        } else {
            throw new IllegalArgumentException("Miza z ID " + id + " ne obstaja.");
        }
    }
}
