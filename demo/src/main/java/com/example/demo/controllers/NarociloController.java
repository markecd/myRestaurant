package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.Narocilo;
import com.example.demo.models.STANJE_MIZE;
import com.example.demo.models.STANJE_NAROCILO;
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

    @PutMapping("/spremeniStanje/{stanje}/{id}")
    public Narocilo spremeniStanjeNarocila(@PathVariable(name = "stanje") String stanje_string, @PathVariable(name = "id") Long id){

        STANJE_NAROCILO stanje = STANJE_NAROCILO.valueOf(stanje_string.toUpperCase());

        return narociloService.posodobiStanjeNarocila(stanje, id);
    }

    @GetMapping("/vrniZasedeneMize")
    public Iterable<Object[]> vrniZasedeneMize(){
        return narociloService.vrniZasedeneMize();
    }
    @GetMapping("/najdiMizoZNarociloId/{narociloId}")
    public Integer NajdiMizoZNarociloId(@PathVariable Long narociloId) {
        return narociloService.najdiMizoZNarociloId(narociloId);
    }

    @GetMapping("/najdiStanjeMizePostrezenegaNarocila")
    public List<STANJE_MIZE> najdiStanjeMizeNaStanjeNarocila() {
        return narociloService.najdiStanjeMizeNaStanjeNarocila();
    }

    @GetMapping("/dobiZadnjeNarociloByMiza/{idMiza}")
    public Long dobiZadnjeNarociloByMiza(@PathVariable(name="idMiza") Long idMiza){
        return narociloService.dobiZadnjeNarociloByMiza(idMiza);
    }

    @GetMapping("/vrniNarocilaKuhar")
    public List<Narocilo> vrniNarocilaKuhar(){
        return narociloService.vrniNarocilaKuhar();
    }

}
