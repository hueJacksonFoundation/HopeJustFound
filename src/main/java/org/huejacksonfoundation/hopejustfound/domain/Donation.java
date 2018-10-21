package org.huejacksonfoundation.hopejustfound.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Donation.
 */
@Entity
@Table(name = "donation")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Donation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "jhi_type")
    private String type;

    @Column(name = "initial_date")
    private LocalDate initialDate;

    @Column(name = "expire_date")
    private LocalDate expireDate;

    @Column(name = "jhi_condition")
    private String condition;

    @Column(name = "description")
    private String description;

    @Column(name = "experience")
    private String experience;

    @Column(name = "climate")
    private String climate;

    @Column(name = "intensity")
    private String intensity;

    @Column(name = "number_of_volunteers")
    private Integer numberOfVolunteers;

    @ManyToOne
    @JsonIgnoreProperties("")
    private User userid;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public Donation type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getInitialDate() {
        return initialDate;
    }

    public Donation initialDate(LocalDate initialDate) {
        this.initialDate = initialDate;
        return this;
    }

    public void setInitialDate(LocalDate initialDate) {
        this.initialDate = initialDate;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public Donation expireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
        return this;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public String getCondition() {
        return condition;
    }

    public Donation condition(String condition) {
        this.condition = condition;
        return this;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDescription() {
        return description;
    }

    public Donation description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExperience() {
        return experience;
    }

    public Donation experience(String experience) {
        this.experience = experience;
        return this;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getClimate() {
        return climate;
    }

    public Donation climate(String climate) {
        this.climate = climate;
        return this;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getIntensity() {
        return intensity;
    }

    public Donation intensity(String intensity) {
        this.intensity = intensity;
        return this;
    }

    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }

    public Integer getNumberOfVolunteers() {
        return numberOfVolunteers;
    }

    public Donation numberOfVolunteers(Integer numberOfVolunteers) {
        this.numberOfVolunteers = numberOfVolunteers;
        return this;
    }

    public void setNumberOfVolunteers(Integer numberOfVolunteers) {
        this.numberOfVolunteers = numberOfVolunteers;
    }

    public User getUserid() {
        return userid;
    }

    public Donation userid(User user) {
        this.userid = user;
        return this;
    }

    public void setUserid(User user) {
        this.userid = user;
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
        Donation donation = (Donation) o;
        if (donation.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), donation.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Donation{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", initialDate='" + getInitialDate() + "'" +
            ", expireDate='" + getExpireDate() + "'" +
            ", condition='" + getCondition() + "'" +
            ", description='" + getDescription() + "'" +
            ", experience='" + getExperience() + "'" +
            ", climate='" + getClimate() + "'" +
            ", intensity='" + getIntensity() + "'" +
            ", numberOfVolunteers=" + getNumberOfVolunteers() +
            "}";
    }
}
