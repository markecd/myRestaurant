package com.example.demo.dao;

import com.example.demo.models.Narocilo;
import com.example.demo.models.STANJE_MIZE;
import com.example.demo.models.STANJE_NAROCILO;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface NarociloRepository extends CrudRepository<Narocilo, Long>{
    
    @Query("SELECT SUM(i.cena) FROM IzdelekNarocilo n JOIN n.izdelek i WHERE n.id = ?1")
    double VrniSkupnoCenoNarocila(Long narociloId);

    @Query("SELECT m.stevilka_mize FROM Narocilo n JOIN n.miza m WHERE n.id = :narociloId")
    Integer najdiMizoZNarociloId(@Param("narociloId") Long narociloId);

    @Query("SELECT m.stanje_mize, m.stevilka_mize FROM Narocilo n JOIN n.miza m WHERE n.stanje_narocila = POSTREZENO")
    List<STANJE_MIZE> najdiStanjeMizeNaStanjeNarocila();

}
