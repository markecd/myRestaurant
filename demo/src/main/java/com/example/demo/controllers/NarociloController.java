package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Narocilo;
import com.example.demo.dto.NarociloDTO;
import com.example.demo.services.NarociloService;

@RestController
@RequestMapping("/narocila")
public class NarociloController {

    private final NarociloService narociloService;

    @Autowired
    public NarociloController(NarociloService narociloService) {
        this.narociloService = narociloService;
    }

    @PostMapping("/vstaviNarocilo/{idMiza}")
    public Narocilo vstaviNarocilo(@RequestBody NarociloDTO narociloDTO, @PathVariable Long idMiza){
        return narociloService.vstaviNarocilo(narociloDTO, idMiza);
    }

}
