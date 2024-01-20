package com.example.demo.controllers;

import com.example.demo.models.Izdelek;
import com.example.demo.services.IzdelekService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/izdelki")
public class IzdelekController {

    private final IzdelekService izdelekService;

    @Autowired
    public IzdelekController(IzdelekService izdelekService) {
        this.izdelekService = izdelekService;
    }

    @GetMapping("/vrniVseIzdelke")
    public Iterable<Izdelek> vrniVseIzdelke() {
        return izdelekService.vrniVseIzdelke();
    }

    @PostMapping("/vstaviIzdelek")
    public Izdelek vstaviIzdelek(@RequestBody Izdelek izdelek){
        return izdelekService.vstaviIzdelek(izdelek);
    }

    @PutMapping("/posodobiCenoIzdelka/{id}")
    public Izdelek posodobiCenoIzdelka(@PathVariable Long id, @RequestBody double novaCena){
        return izdelekService.posodobiCenoIzdelka(id, novaCena);
    }

    @DeleteMapping("/izbrisiIzdelek/{id}")
    public void deleteIzdelek(@PathVariable Long id){
        izdelekService.deleteIzdelek(id);
    }


}
