package org.huejackson.hopejustfound.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DonationRequest.
 */
@Entity
@Table(name = "donation_request")
public class DonationRequest implements Serializable {

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

    @Column(name = "number_of_volunteers")
    private Integer numberOfVolunteers;

    @ManyToOne
    @JsonIgnoreProperties("")
    private User user;

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

    public DonationRequest type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getInitialDate() {
        return initialDate;
    }

    public DonationRequest initialDate(LocalDate initialDate) {
        this.initialDate = initialDate;
        return this;
    }

    public void setInitialDate(LocalDate initialDate) {
        this.initialDate = initialDate;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public DonationRequest expireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
        return this;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public String getCondition() {
        return condition;
    }

    public DonationRequest condition(String condition) {
        this.condition = condition;
        return this;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDescription() {
        return description;
    }

    public DonationRequest description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExperience() {
        return experience;
    }

    public DonationRequest experience(String experience) {
        this.experience = experience;
        return this;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public Integer getNumberOfVolunteers() {
        return numberOfVolunteers;
    }

    public DonationRequest numberOfVolunteers(Integer numberOfVolunteers) {
        this.numberOfVolunteers = numberOfVolunteers;
        return this;
    }

    public void setNumberOfVolunteers(Integer numberOfVolunteers) {
        this.numberOfVolunteers = numberOfVolunteers;
    }

    public User getUser() {
        return user;
    }

    public DonationRequest user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
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
        DonationRequest donationRequest = (DonationRequest) o;
        if (donationRequest.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), donationRequest.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DonationRequest{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", initialDate='" + getInitialDate() + "'" +
            ", expireDate='" + getExpireDate() + "'" +
            ", condition='" + getCondition() + "'" +
            ", description='" + getDescription() + "'" +
            ", experience='" + getExperience() + "'" +
            ", numberOfVolunteers=" + getNumberOfVolunteers() +
            "}";
    }
}
