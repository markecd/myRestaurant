package com.example.demo.dao;

import com.example.demo.models.Natakar;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface NatakarRepository extends CrudRepository<Natakar, Long>{
    
    @Query("SELECT n FROM Natakar n WHERE n.username = ?1")
    public Optional<Natakar> vrniNatakarPoUsername(String username);

}