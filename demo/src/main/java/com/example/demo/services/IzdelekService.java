package com.example.demo.services;

import com.example.demo.models.Izdelek;
import com.example.demo.dao.IzdelekRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IzdelekService {

    private final IzdelekRepository izdelekRepository;

    @Autowired
    public IzdelekService(IzdelekRepository izdelekRepository) {
        this.izdelekRepository = izdelekRepository;
    }

    public Iterable<Izdelek> vrniVseIzdelke() {
        return izdelekRepository.vrniVseIzdelke();
    }

    public Izdelek vstaviIzdelek(Izdelek izdelek){
        return izdelekRepository.save(izdelek);
    }

    public Izdelek posodobiCenoIzdelka(Long id, double novaCena){
        Optional<Izdelek> izdelek = izdelekRepository.findById(id);

        if (!izdelek.isPresent()) {
            throw new IllegalArgumentException("Izdelek z ID " + id + " ne obstaja.");
        }

        Izdelek izdelekZaUpdate = izdelek.get();
        izdelekZaUpdate.setCena(novaCena);

        return izdelekRepository.save(izdelekZaUpdate);
    }

    public void deleteIzdelek(Long id){
        if(!izdelekRepository.existsById(id)){
            throw new IllegalArgumentException("Izdelek z ID " + id + " ne obstaja.");
        }

        izdelekRepository.deleteById(id);
    }

    public Iterable<Object[]> vrniIzdelkeRacuna(Long idRacun){
        return izdelekRepository.vrniIzdelkeRacuna(idRacun);
    }

}
