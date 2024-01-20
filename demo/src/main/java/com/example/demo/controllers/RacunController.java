package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.services.RacunService;
import com.example.demo.models.Racun;
import com.example.demo.dto.RacunDTO;

@RestController
@RequestMapping("/racuni")
public class RacunController {
    
    private final RacunService racunService;

    @Autowired
    public RacunController(RacunService racunService){
        this.racunService = racunService;
    }

    @PostMapping("/vstaviRacun")
    public Racun vstaviRacun(@RequestBody RacunDTO racunDTO){
        return racunService.vstaviRacun(racunDTO);
    }
}

