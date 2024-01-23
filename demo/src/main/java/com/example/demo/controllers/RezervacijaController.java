package com.example.demo.controllers;

import com.example.demo.dto.RezervacijaDTO;
import com.example.demo.models.Izdelek;
import com.example.demo.models.Rezervacija;
import com.example.demo.models.TIP_IZDELKA;
import com.example.demo.services.RezervacijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rezervacije")
public class RezervacijaController {

    private final RezervacijaService rezervacijaService;

    @Autowired
    public RezervacijaController(RezervacijaService rezervacijaService) {
        this.rezervacijaService = rezervacijaService;
    }

    @GetMapping("/vrniVseRezervacije")
    public Iterable<Rezervacija> vrniVseRezervacije() {
        return rezervacijaService.vrniVseRezervacije();
    }

    @GetMapping("/vrniRezervacijo/{id}")
    public Rezervacija vrniRezervacijoPoId(@PathVariable Long id) {
        return rezervacijaService.vrniRezervacijoPoId(id);
    }

    @PostMapping("/vstaviRezervacijo/{id_miza}")
    public Rezervacija vstaviRezervacijo(@RequestBody RezervacijaDTO rezervacijaDTO, @PathVariable Long id_miza) {
        return rezervacijaService.vstaviRezervacijo(rezervacijaDTO, id_miza);
    }

    @PutMapping("/posodobiPodatkeRezervacije/{id}")
    public Rezervacija posodobiPodatkeRezervacije(@PathVariable Long id, @RequestBody Rezervacija novaRezervacija) {
        return rezervacijaService.posodobiPodatkeRezervacije(id, novaRezervacija);
    }

    @DeleteMapping("/izbrisiRezervacijo/{id}")
    public void izbrisiRezervacijo(@PathVariable Long id) {
        rezervacijaService.izbrisiRezervacijo(id);
    }
}
