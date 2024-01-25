package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.NatakarRepository;
import com.example.demo.dto.NatakarDTO;
import com.example.demo.dto.prijavaDTO;
import com.example.demo.models.Natakar;
import com.example.demo.security.JwtUtil;

import java.util.Optional;
import java.util.function.Consumer;


import org.mindrot.jbcrypt.BCrypt;



@Service
public class NatakarService {
    
    private final NatakarRepository natakarRepository;

    @Autowired
    public NatakarService(NatakarRepository natakarRepository) {
        this.natakarRepository = natakarRepository;
    }

    public Natakar vstaviNatakarja(Natakar natakar) {

        String hashGeslo = BCrypt.hashpw(natakar.getGeslo(), BCrypt.gensalt());

        natakar.setGeslo(hashGeslo);

        return natakarRepository.save(natakar);
    }

    public prijavaDTO overjanjeNatakar(NatakarDTO natakarDTO){

        String username = natakarDTO.getUsername();
        String geslo = natakarDTO.getGeslo();
        
        Optional<Natakar> optionalNatakar = natakarRepository.vrniNatakarPoUsername(username);

        if(optionalNatakar.isPresent()){
            Natakar natakar = optionalNatakar.get();
            String shranjenoGeslo = natakar.getGeslo();

            
            String pozicija = natakar.getPozicija().toString();

            return new prijavaDTO(BCrypt.checkpw(geslo, shranjenoGeslo), pozicija, natakar.getId());
        }

        return new prijavaDTO(false, null, null);
    }

    public Iterable<String> vrniPozicije(){
        Iterable<String> pozicije = natakarRepository.vrniVsePozicije();

        return pozicije;
    }

}
