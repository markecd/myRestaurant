package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MizaRepository;
import com.example.demo.models.Miza;
import com.example.demo.models.Rezervacija;
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


    public Miza posodobiStanjeMize(Long idMiza, String stanjeMiza) {
        Optional<Miza> mizaOpt = mizaRepository.findById(idMiza);

        if (mizaOpt.isPresent()) {
            Miza miza = mizaOpt.get();
            miza.setStanje_mize(STANJE_MIZE.valueOf(stanjeMiza));

            return mizaRepository.save(miza);
        } else {
            throw new IllegalArgumentException("Miza z ID " + idMiza + " ne obstaja.");
        }
    }
}
