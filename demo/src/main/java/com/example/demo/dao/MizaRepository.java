package com.example.demo.dao;

import com.example.demo.models.Miza;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MizaRepository extends CrudRepository<Miza, Long> {

    @Query("SELECT m from Miza m WHERE m.stanje_mize = 'NEZASEDENO' AND m.stevilo_sedezev >= ?1 ORDER BY m.stevilo_sedezev DESC")
    List<Miza> vrniProsteMizePoSteviluSedezev(int stevilo_sedezev);

}
