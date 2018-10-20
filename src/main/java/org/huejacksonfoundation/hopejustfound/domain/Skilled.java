package org.huejacksonfoundation.hopejustfound.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Skilled.
 */
@Entity
@Table(name = "skilled")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Skilled implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "experience")
    private String experience;

    @Column(name = "jhi_type")
    private String type;

    @Column(name = "jhi_number")
    private Integer number;

    @OneToOne(mappedBy = "skilled")
    @JsonIgnore
    private Donation donation;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExperience() {
        return experience;
    }

    public Skilled experience(String experience) {
        this.experience = experience;
        return this;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getType() {
        return type;
    }

    public Skilled type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNumber() {
        return number;
    }

    public Skilled number(Integer number) {
        this.number = number;
        return this;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Donation getDonation() {
        return donation;
    }

    public Skilled donation(Donation donation) {
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
        Skilled skilled = (Skilled) o;
        if (skilled.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), skilled.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Skilled{" +
            "id=" + getId() +
            ", experience='" + getExperience() + "'" +
            ", type='" + getType() + "'" +
            ", number=" + getNumber() +
            "}";
    }
}
