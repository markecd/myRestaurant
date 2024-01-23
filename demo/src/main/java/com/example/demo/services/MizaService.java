package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MizaRepository;
import com.example.demo.models.Miza;
import com.example.demo.models.STANJE_MIZE;

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

    public Iterable<Miza> vrniSpecificnoMizo(STANJE_MIZE stanjeMize, int stMize, int stSedezev) {
        return mizaRepository.vrniSpecificnoMizo(stanjeMize, stMize, stSedezev);
    }
}
