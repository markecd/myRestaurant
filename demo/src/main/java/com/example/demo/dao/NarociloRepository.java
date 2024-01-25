package com.example.demo.dao;

import com.example.demo.models.Narocilo;

import java.util.List;

import com.example.demo.models.STANJE_MIZE;
import com.example.demo.models.STANJE_NAROCILO;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface NarociloRepository extends CrudRepository<Narocilo, Long> {

    @Query("SELECT SUM(i.cena) FROM IzdelekNarocilo n JOIN n.izdelek i WHERE n.narocilo.id = ?1")
    double VrniSkupnoCenoNarocila(Long narociloId);

    @Query(value = "SELECT m.*, n.stanje_narocila " +
            "FROM Miza m " +
            "LEFT JOIN Narocilo n ON m.id = n.id_miza " +
            "WHERE n.cas_rezervacije IN ( " +
            "    SELECT MAX(n2.cas_rezervacije) " +
            "    FROM Narocilo n2 " +
            "    WHERE n2.id_miza = m.id " +
            ") " +
            "OR n.id IS NULL " +
            "ORDER BY m.stevilka_mize", nativeQuery = true)
    List<Object[]> getZasedeneMize();

    @Query("SELECT m.stevilka_mize FROM Narocilo n JOIN n.miza m WHERE n.id = :narociloId")
    Integer najdiMizoZNarociloId(@Param("narociloId") Long narociloId);

    @Query("SELECT m.stanje_mize, m.stevilka_mize FROM Narocilo n JOIN n.miza m WHERE n.stanje_narocila = POSTREZENO")
    List<STANJE_MIZE> najdiStanjeMizeNaStanjeNarocila();

    @Query(value = "SELECT n.id " +
            "FROM narocilo n " +
            "WHERE n.id_miza = :mizaId " +
            "ORDER BY n.cas_rezervacije DESC " +
            "LIMIT 1", nativeQuery = true)
    Long dobiZadnjeNarociloByMiza(@Param("mizaId") Long mizaId);

}
