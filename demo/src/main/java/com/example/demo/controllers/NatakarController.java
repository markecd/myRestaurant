package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Natakar;
import com.example.demo.services.NatakarService;

@RestController
@RequestMapping("/natakarji")
public class NatakarController {

    private final NatakarService natakarService;

    @Autowired
    public NatakarController(NatakarService natakarService) {
        this.natakarService = natakarService;
    }

    @PostMapping("/vstaviNatakarja")
    public Natakar vstaviMizo(@RequestBody Natakar natakar){
        return natakarService.vstaviNatakarja(natakar);
    }
}
