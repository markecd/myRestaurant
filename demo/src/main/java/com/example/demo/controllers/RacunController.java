package com.example.demo.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.services.RacunService;
import com.example.demo.models.Racun;
import com.example.demo.dto.RacunDTO;

@RestController
@RequestMapping("/racuni")
public class RacunController {

    private final RacunService racunService;

    @Autowired
    public RacunController(RacunService racunService) {
        this.racunService = racunService;
    }

    @PostMapping("/vstaviRacun")
    public Racun vstaviRacun(@RequestBody RacunDTO racunDTO) {
        return racunService.vstaviRacun(racunDTO);
    }

    @GetMapping("/vrniRacunePogoji/{koncen_znesek}/{id_miza}/{cas_narocila}")
    public Iterable<Racun> vrniRacunPogoji(@PathVariable(name = "koncen_znesek") double koncen_znesek, @PathVariable(name = "id_miza") int id_miza, @PathVariable(name = "cas_narocila") String cas_narocila_string) {

        LocalDateTime cas_narocila = null;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            cas_narocila = LocalDateTime.parse(cas_narocila_string, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("ne dela");
        }
        return racunService.vrniRacunePogoji(koncen_znesek, id_miza, cas_narocila);
    }

    @GetMapping("/vrniPodatkeORacunu/{idRacun}")
    public Object[] vrniPodatkeORacunu(@PathVariable Long idRacun) {
        return racunService.vrniPodatkeORacunu(idRacun);
    }

    @GetMapping("/vrniZnesekRacunaZPogoji/{imeNatakarja}")
    public double vrniZnesekRacunaZPogoji(@PathVariable String imeNatakarja) {
        return racunService.vrniZnesekRacunaZPogoji(imeNatakarja);
    }
}
