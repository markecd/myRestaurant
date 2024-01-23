package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.NatakarRepository;
import com.example.demo.models.Natakar;

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
}
