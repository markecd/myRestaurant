package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;

import com.example.demo.models.Racun;

public interface RacunRepository extends CrudRepository<Racun, Long>{
    
    @Query("SELECT r from Racun r JOIN r.narocilo n WHERE r.koncen_znesek > ?1 AND n.miza.id = ?2 AND n.cas_rezervacije > ?3")
    List<Racun> vrniRacunePogoji(double koncen_znesek, int miza_id, LocalDateTime cas_rezervacije);
}
