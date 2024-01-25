package com.example.demo.dao;


import com.example.demo.models.Miza;
import com.example.demo.models.STANJE_MIZE;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MizaRepository extends CrudRepository<Miza, Long> {

    @Query("SELECT m from Miza m WHERE m.stanje_mize = 'NEZASEDENO' AND m.stevilo_sedezev >= ?1 ORDER BY m.stevilo_sedezev DESC")
    List<Miza> vrniProsteMizePoSteviluSedezev(int stevilo_sedezev);

    @Query("SELECT m FROM Miza m WHERE m.stanje_mize = :stanjeMize AND m.stevilka_mize = :stMize AND m.stevilo_sedezev = :stSedezev")
    List<Miza> vrniSpecificnoMizo(
            @Param("stanjeMize") STANJE_MIZE stanjeMize, 
            @Param("stMize") int stMize,
            @Param("stSedezev") int stSedezev
        );


}
