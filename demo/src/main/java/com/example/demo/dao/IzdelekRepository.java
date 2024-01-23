package com.example.demo.dao;

import com.example.demo.models.Izdelek;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IzdelekRepository extends CrudRepository<Izdelek, Long>{
    
    @Query("SELECT i from Izdelek i")
    List<Izdelek> vrniVseIzdelke();

    @Query("SELECT izn.izdelek.naziv, SUM(izn.quantity), SUM(izn.quantity) * i.cena FROM Racun r JOIN r.narocilo n JOIN n.izdelekNarocilos izn JOIN izn.izdelek i WHERE r.id = ?1 GROUP BY izn.izdelek")
    List<Object[]> vrniIzdelkeRacuna(Long idRacun);

}