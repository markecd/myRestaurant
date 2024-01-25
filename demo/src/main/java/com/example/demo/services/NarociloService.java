package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.NarociloRepository;
import com.example.demo.dao.IzdelekNarociloRepository;
import com.example.demo.dao.IzdelekRepository;
import com.example.demo.dao.MizaRepository;
import com.example.demo.models.Miza;
import com.example.demo.models.Narocilo;
import com.example.demo.models.STANJE_MIZE;
import com.example.demo.models.STANJE_NAROCILO;
import com.example.demo.models.Izdelek;
import com.example.demo.models.IzdelekNarocilo;
import com.example.demo.dto.NarociloDTO;

@Service
public class NarociloService {

    private final NarociloRepository narociloRepository;

    @Autowired
    public NarociloService(NarociloRepository narociloRepository) {
        this.narociloRepository = narociloRepository;
    }

    @Autowired
    private MizaRepository mizaRepository;

    @Autowired
    private IzdelekRepository izdelekRepository;

    @Autowired
    private IzdelekNarociloRepository izdelekNarociloRepository;

    public Narocilo vstaviNarocilo(NarociloDTO narociloDTO, Long idMiza) {

        Narocilo narocilo = new Narocilo();
        narocilo.setCas_rezervacije(narociloDTO.getCasRezervacije());
        narocilo.setStanje_narocila(STANJE_NAROCILO.PREJETO);

        Miza miza = mizaRepository.findById(idMiza).orElseThrow(/* exception */);
        narocilo.setMiza(miza);

        narocilo = narociloRepository.save(narocilo);

        for (Long izdelekId : narociloDTO.getIzdelkiIds()) {
            Izdelek izdelek = izdelekRepository.findById(izdelekId).orElseThrow(/* exception */);
            IzdelekNarocilo izdelekNarocilo = new IzdelekNarocilo();
            izdelekNarocilo.setIzdelek(izdelek);
            izdelekNarocilo.setNarocilo(narocilo);
            izdelekNarocilo.setQuantity(1);
            izdelekNarociloRepository.save(izdelekNarocilo);
        }

        

        return narocilo;
    }

    public Narocilo posodobiStanjeNarocila(STANJE_NAROCILO stanje_narocila, Long id){

        Optional<Narocilo> narocilo = narociloRepository.findById(id);

        if (!narocilo.isPresent()) {
            throw new IllegalArgumentException("Narocilo z ID " + id + " ne obstaja.");
        }

        Narocilo narociloZaUpdate = narocilo.get();
        narociloZaUpdate.setStanje_narocila(stanje_narocila);

        return narociloRepository.save(narociloZaUpdate);
    }

    public Iterable<Object[]> vrniZasedeneMize(){
        return narociloRepository.getZasedeneMize();
    }

    public Integer najdiMizoZNarociloId(Long narociloId) {
        return narociloRepository.najdiMizoZNarociloId(narociloId);
    }

    public List<STANJE_MIZE> najdiStanjeMizeNaStanjeNarocila() {
        return narociloRepository.najdiStanjeMizeNaStanjeNarocila();
    }

    public Narocilo getNarociloByMizaId(Long mizaId) {
        return narociloRepository.findByMizaId(mizaId);
    }
    public Long dobiZadnjeNarociloByMiza(Long idMiza){
        return narociloRepository.dobiZadnjeNarociloByMiza(idMiza);
    }

}
