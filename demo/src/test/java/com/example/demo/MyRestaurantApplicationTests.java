package com.example.demo;


import com.example.demo.dao.MizaRepository;
import com.example.demo.models.Miza;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MyRestaurantApplicationTests {

    @Autowired
    private MizaRepository mizaRepository;

    @Test
    void contextLoads() {
        // This test ensures the Spring context loads properly
    }

    @Test
    void testVrniMizePoSteviluSedezev() {


        List<Miza> mize = mizaRepository.vrniMizePoSteviluSedezev(4);

        assertFalse(mize.isEmpty(), "No tables found with enough seats");
        for (Miza miza : mize) {
            assertTrue(miza.getStevilo_sedezev() >= 4, "Found a table with insufficient seats");
        }
    }
}
