package com.epam.esm.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Certificate{
    private long id;
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDate dateCreation;
    private LocalDate dateModification;
    private CertificateDuration duration;

    public Certificate() {
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

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDate getDateModification() {
        return dateModification;
    }

    public void setDateModification(LocalDate dateModification) {
            this.dateModification = dateModification;
    }

    public CertificateDuration getDuration() {
        return duration;
    }

    public void setDuration(CertificateDuration duration) {
        this.duration = duration;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Certificate that = (Certificate) o;
        return id == that.id &&
                name.equals(that.name) &&
                description.equals(that.description) &&
                price.equals(that.price) &&
                dateCreation.equals(that.dateCreation) &&
                Objects.equals(dateModification, that.dateModification) &&
                duration == that.duration;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, dateCreation, dateModification, duration);
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", dateCreation=" + dateCreation +
                ", dateModification=" + dateModification +
                ", duration=" + duration +
                '}';
    }
}
