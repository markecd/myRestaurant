package com.example.demo.dao;

import com.example.demo.models.Izdelek;
import com.example.demo.models.TIP_IZDELKA;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IzdelekRepository extends CrudRepository<Izdelek, Long>{
    
    @Query("SELECT i from Izdelek i")
    List<Izdelek> vrniVseIzdelke();

    @Query("SELECT izn.izdelek.naziv, SUM(izn.quantity), SUM(izn.quantity) * i.cena FROM Racun r JOIN r.narocilo n JOIN n.izdelekNarocilos izn JOIN izn.izdelek i WHERE r.id = ?1 GROUP BY izn.izdelek")
    List<Object[]> vrniIzdelkeRacuna(Long idRacun);
    

    @Query("SELECT i FROM Izdelek i WHERE i.tip_izdelka = :tipIzdelka AND i.cena >= :cena")
    List<Izdelek> vrniSpecificenIzdelek(
            @Param("tipIzdelka") TIP_IZDELKA tipIzdelka, 
            @Param("cena") double cena
        );

    @Query("SELECT i FROM Izdelek i ORDER BY i.tip_izdelka")
    Iterable<Izdelek> vrniIzdelkeOrdByTip();
}