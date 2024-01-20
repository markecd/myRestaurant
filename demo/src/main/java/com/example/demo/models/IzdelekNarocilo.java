package com.example.demo.models;

import jakarta.persistence.*;
@Entity
public class IzdelekNarocilo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "narocilo_id")
    private Narocilo narocilo;

    public Narocilo getNarocilo() {
        return narocilo;
    }

    public void setNarocilo(Narocilo narocilo) {
        this.narocilo = narocilo;
    }

    @ManyToOne
    @JoinColumn(name = "izdelek_id")
    private Izdelek izdelek;

    public Izdelek getIzdelek() {
        return izdelek;
    }

    public void setIzdelek(Izdelek izdelek) {
        this.izdelek = izdelek;
    }

    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
