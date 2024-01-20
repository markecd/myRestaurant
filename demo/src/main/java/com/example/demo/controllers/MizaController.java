package com.example.demo.controllers;

import com.example.demo.models.Miza;
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

    @PostMapping("/vstaviMizo")
    public Miza vstaviMizo(@RequestBody Miza miza){
        return mizaService.vstaviMizo(miza);
    }

    @GetMapping("/vrniProsteMizePoSteviloSedezev/{stevilo_sedezev}")
    public Iterable<Miza> vrniProsteMizePoSteviluSedezev(@PathVariable(name = "stevilo_sedezev") int stevilo_sedezev) {
        return mizaService.vrniProsteMizePoSteviluSedezev(stevilo_sedezev);
    }

}
