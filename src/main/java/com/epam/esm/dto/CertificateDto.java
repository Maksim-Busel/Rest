package com.epam.esm.dto;

import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.CertificateDuration;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CertificateDto {
    private long id;
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDate dateCreation;
    private LocalDate dateModification;
    private CertificateDuration duration;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public LocalDate getDateModification() {
        return dateModification;
    }

    public void setDateModification(LocalDate dateModification) {
        this.dateModification = dateModification;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public CertificateDuration getDuration() {
        return duration;
    }

    public void setDuration(CertificateDuration duration) {
        this.duration = duration;
    }
}
