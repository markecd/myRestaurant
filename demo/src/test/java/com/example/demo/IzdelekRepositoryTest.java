package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;

import com.example.demo.dao.IzdelekNarociloRepository;
import com.example.demo.dao.IzdelekRepository;
import com.example.demo.dao.NarociloRepository;
import com.example.demo.dao.RacunRepository;
import com.example.demo.models.Izdelek;
import com.example.demo.models.Narocilo;
import com.example.demo.models.Racun;
import com.example.demo.models.IzdelekNarocilo;
import com.example.demo.models.TIP_IZDELKA;



@SpringBootTest
public class IzdelekRepositoryTest {
	
	@Autowired
	private IzdelekRepository dao;
	
	
	@Autowired
	private IzdelekNarociloRepository daoIzdNar;
	
	@Autowired
	private NarociloRepository daoNar;
	
	@Autowired
	private RacunRepository daoRac;
	
	@BeforeEach
	void beforeAll() {
		
		daoIzdNar.deleteAll();
		dao.deleteAll();
		
	}
	
	
	@Test
	void vrniVseIzdelkeTest() {
		
		List<Izdelek> izdelki = dao.vrniVseIzdelke();
		assertEquals(0, izdelki.size());
		
		dao.save(new Izdelek());
		dao.save(new Izdelek());

		izdelki = dao.vrniVseIzdelke();
		assertEquals(2, izdelki.size());
		
	}
	

	@Test 
	void vrniIzdelkeRacunaTest() {
		
		Izdelek izdelek1 = new Izdelek();
		dao.save(izdelek1);
		Izdelek izdelek2 = new Izdelek();
		dao.save(izdelek2);
		
		Narocilo narocilo = new Narocilo();
		daoNar.save(narocilo);
		
		IzdelekNarocilo izdelekNarocilo1 = new IzdelekNarocilo();
		izdelekNarocilo1.setIzdelek(izdelek1);
		izdelekNarocilo1.setNarocilo(narocilo);
		izdelekNarocilo1.setQuantity(2);
		daoIzdNar.save(izdelekNarocilo1);
		
		IzdelekNarocilo izdelekNarocilo2 = new IzdelekNarocilo();
		izdelekNarocilo2.setIzdelek(izdelek2);
		izdelekNarocilo2.setNarocilo(narocilo);
		izdelekNarocilo2.setQuantity(1);
		daoIzdNar.save(izdelekNarocilo2);
		
		Racun racun = new Racun();
		racun.setNarocilo(narocilo);
		daoRac.save(racun);
		
		List<Object[]> izdelkiRacuna = dao.vrniIzdelkeRacuna(racun.getId());
		
		assertEquals(3, (Long) izdelkiRacuna.get(0)[1] + (Long) izdelkiRacuna.get(1)[1]);
		
	}
	
	
	@Test
	void vrniSpecificenIzdelekTest() {
		List<Izdelek> izdelki = dao.vrniSpecificenIzdelek(TIP_IZDELKA.GLAVNA_JED, 4.4);
		
		assertEquals(0, izdelki.size());
		
		Izdelek izdelek1 = new Izdelek();
		izdelek1.setTip_izdelka(TIP_IZDELKA.GLAVNA_JED);
		izdelek1.setCena(4.4);
		dao.save(izdelek1);
		
		Izdelek izdelek2 = new Izdelek();
		izdelek2.setTip_izdelka(TIP_IZDELKA.GLAVNA_JED);
		izdelek2.setCena(4.4);
		dao.save(izdelek2);
		
		izdelki = dao.vrniSpecificenIzdelek(TIP_IZDELKA.GLAVNA_JED, 4.4);
		assertEquals(2, izdelki.size());

	}
	
	@Test
	void vrniIzdelkeOrdByTipTest() {
		Iterable<Izdelek> izdelki = dao.vrniIzdelkeOrdByTip();
		assertFalse(izdelki.iterator().hasNext());
		
	    Izdelek izdelek1 = new Izdelek();
	    izdelek1.setTip_izdelka(TIP_IZDELKA.GLAVNA_JED);
	    izdelek1.setCena(5.0);
	    dao.save(izdelek1);

	    Izdelek izdelek2 = new Izdelek();
	    izdelek2.setTip_izdelka(TIP_IZDELKA.SLADICA);
	    izdelek2.setCena(10.0);
	    dao.save(izdelek2);

	    Izdelek izdelek3 = new Izdelek();
	    izdelek3.setTip_izdelka(TIP_IZDELKA.PIJACA);
	    izdelek3.setCena(3.0);
	    dao.save(izdelek3);
	    
	    izdelki = dao.vrniIzdelkeOrdByTip();
	    
	    List<Izdelek> izdelkiList = new ArrayList<>();
	    izdelki.forEach(izdelkiList::add);
	    
	    assertEquals(3, izdelkiList.size());
	    
	    assertEquals(TIP_IZDELKA.SLADICA, izdelkiList.get(0).getTip_izdelka()); 
	    assertEquals(TIP_IZDELKA.GLAVNA_JED, izdelkiList.get(1).getTip_izdelka());  
	    assertEquals(TIP_IZDELKA.PIJACA, izdelkiList.get(2).getTip_izdelka()); 
		
	}
	
	@Test
	void vrniIzdelkeNarocilaTest() {
		
		Narocilo narocilo = new Narocilo();
		daoNar.save(narocilo);
		
		Iterable<Object[]> izdelki_narocila = dao.vrniIzdelkeNarocila(narocilo.getId());
		assertFalse(izdelki_narocila.iterator().hasNext());
		
		Izdelek izdelek1 = new Izdelek();
		dao.save(izdelek1);
		Izdelek izdelek2 = new Izdelek();
		dao.save(izdelek2);
		
		IzdelekNarocilo izdelekNarocilo1 = new IzdelekNarocilo();
		izdelekNarocilo1.setIzdelek(izdelek1);
		izdelekNarocilo1.setNarocilo(narocilo);
		izdelekNarocilo1.setQuantity(5);
		daoIzdNar.save(izdelekNarocilo1);
		
		IzdelekNarocilo izdelekNarocilo2 = new IzdelekNarocilo();
		izdelekNarocilo2.setIzdelek(izdelek2);
		izdelekNarocilo2.setNarocilo(narocilo);
		izdelekNarocilo2.setQuantity(1);
		daoIzdNar.save(izdelekNarocilo2);
		
		
	    List<Object[]> izdelkiList = new ArrayList<>();
	    
		izdelki_narocila = dao.vrniIzdelkeNarocila(narocilo.getId());
	    izdelki_narocila.forEach(izdelkiList::add);
	    
		assertEquals(6, (Long) izdelkiList.get(0)[1] + (Long) izdelkiList.get(1)[1]);
		
		
	}
	
	
}
