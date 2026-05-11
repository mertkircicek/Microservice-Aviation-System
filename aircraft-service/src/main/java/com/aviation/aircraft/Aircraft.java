package com.aviation.aircraft;

import javax.persistence.*;

@Entity
@Table(name = "aircrafts")
public class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String model;
    private String status;

    // Boş constructor (JPA arka planda tabloyu oluştururken buna ihtiyaç duyar)
    public Aircraft() {
    }

    // Getter ve Setter metotları
    public Long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}