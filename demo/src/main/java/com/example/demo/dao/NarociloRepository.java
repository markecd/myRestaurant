package com.example.demo.dao;

import com.example.demo.models.Narocilo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface NarociloRepository extends CrudRepository<Narocilo, Long>{
    
    @Query("SELECT SUM(i.cena) FROM IzdelekNarocilo n JOIN n.izdelek i WHERE n.narocilo.id = ?1")
    double VrniSkupnoCenoNarocila(Long narociloId);
}
