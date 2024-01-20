package com.example.demo.controllers;

import com.example.demo.models.Miza;
import com.example.demo.dao.MizaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mize")
public class MizaController {

    @Autowired
    private MizaRepository mizaDao;

    @GetMapping("/stevilo-sedezev/{stevilo_sedezev}")
    public Iterable<Miza> vrniMizePoSteviluSedezev(@PathVariable(name = "stevilo_sedezev") int stevilo_sedezev){
        return mizaDao.vrniMizePoSteviluSedezev(stevilo_sedezev);
    }
    
}
