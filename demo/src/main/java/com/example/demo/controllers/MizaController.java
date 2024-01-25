package com.example.demo.controllers;

import com.example.demo.models.Miza;
import com.example.demo.models.STANJE_MIZE;
import com.example.demo.services.MizaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mize")
public class MizaController {

    private final MizaService mizaService;

    @Autowired
    public MizaController(MizaService mizaService) {
        this.mizaService = mizaService;
    }

    @DeleteMapping("/izbrisiMizo/{id}")
    public void izbrisiMizo(@PathVariable Long id) {
        mizaService.izbrisiMizo(id);
    }

    @GetMapping("/vrniVseMize")
    public Iterable<Miza> vrniVseMize() {
        return mizaService.vrniVseMize();
    }

    @PostMapping("/vstaviMizo")
    public Miza vstaviMizo(@RequestBody Miza miza){
        return mizaService.vstaviMizo(miza);
    }

    @GetMapping("/vrniProsteMizePoSteviloSedezev/{stevilo_sedezev}")
    public Iterable<Miza> vrniProsteMizePoSteviluSedezev(@PathVariable(name = "stevilo_sedezev") int stevilo_sedezev) {
        return mizaService.vrniProsteMizePoSteviluSedezev(stevilo_sedezev);
    }

    @GetMapping("/vrniSpecificnoMizo/{stanjeMize}/{stMize}/{stSedezev}")
    public Iterable<Miza> vrniSpecificnoMizo(@PathVariable STANJE_MIZE stanjeMize, @PathVariable int stMize, @PathVariable int stSedezev) {
        return mizaService.vrniSpecificnoMizo(stanjeMize, stMize, stSedezev);
    }

    @PutMapping("/posodobiStanje/{idMiza}/{stanjeMiza}")
    public Miza posodobiStanjeMize(@PathVariable(name = "idMiza") Long idMiza, @PathVariable(name = "stanjeMiza") String stanjeMiza){
        return mizaService.posodobiStanjeMize(idMiza, stanjeMiza);
    }

}
