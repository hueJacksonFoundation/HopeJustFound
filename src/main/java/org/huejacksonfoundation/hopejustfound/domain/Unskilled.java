package org.huejacksonfoundation.hopejustfound.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Unskilled.
 */
@Entity
@Table(name = "unskilled")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Unskilled implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "climate")
    private String climate;

    @Column(name = "intensity")
    private String intensity;

    @Column(name = "jhi_number")
    private Integer number;

    @OneToOne(mappedBy = "unskilled")
    @JsonIgnore
    private Donation donation;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClimate() {
        return climate;
    }

    public Unskilled climate(String climate) {
        this.climate = climate;
        return this;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getIntensity() {
        return intensity;
    }

    public Unskilled intensity(String intensity) {
        this.intensity = intensity;
        return this;
    }

    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }

    public Integer getNumber() {
        return number;
    }

    public Unskilled number(Integer number) {
        this.number = number;
        return this;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Donation getDonation() {
        return donation;
    }

    public Unskilled donation(Donation donation) {
        this.donation = donation;
        return this;
    }

    public void setDonation(Donation donation) {
        this.donation = donation;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Unskilled unskilled = (Unskilled) o;
        if (unskilled.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), unskilled.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Unskilled{" +
            "id=" + getId() +
            ", climate='" + getClimate() + "'" +
            ", intensity='" + getIntensity() + "'" +
            ", number=" + getNumber() +
            "}";
    }
}
