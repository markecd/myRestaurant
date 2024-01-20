package com.example.demo.dao;

import com.example.demo.models.Izdelek;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IzdelekRepository extends CrudRepository<Izdelek, Long>{
    
    @Query("SELECT i from Izdelek i")
    List<Izdelek> vrniVseIzdelke();

}