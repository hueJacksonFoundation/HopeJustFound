package org.huejackson.hopejustfound.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Contact.
 */
@Entity
@Table(name = "contact")
public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_ein")
    private String companyEIN;

    @Column(name = "company_website")
    private String companyWebsite;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "mailing_address")
    private String mailingAddress;

    @Column(name = "mailing_city")
    private String mailingCity;

    @Column(name = "mailing_state")
    private String mailingState;

    @Column(name = "mailing_zip_code")
    private Integer mailingZipCode;

    @Column(name = "phyisical_address")
    private String phyisicalAddress;

    @Column(name = "phyisical_city")
    private String phyisicalCity;

    @Column(name = "phyisical_state")
    private String phyisicalState;

    @Column(name = "phyisical_zip_code")
    private Integer phyisicalZipCode;

    @Column(name = "contact_days")
    private String contactDays;

    @Column(name = "contact_times")
    private String contactTimes;

    @OneToOne
    @MapsId
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Contact companyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyEIN() {
        return companyEIN;
    }

    public Contact companyEIN(String companyEIN) {
        this.companyEIN = companyEIN;
        return this;
    }

    public void setCompanyEIN(String companyEIN) {
        this.companyEIN = companyEIN;
    }

    public String getCompanyWebsite() {
        return companyWebsite;
    }

    public Contact companyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
        return this;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Contact phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public Contact mailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
        return this;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public String getMailingCity() {
        return mailingCity;
    }

    public Contact mailingCity(String mailingCity) {
        this.mailingCity = mailingCity;
        return this;
    }

    public void setMailingCity(String mailingCity) {
        this.mailingCity = mailingCity;
    }

    public String getMailingState() {
        return mailingState;
    }

    public Contact mailingState(String mailingState) {
        this.mailingState = mailingState;
        return this;
    }

    public void setMailingState(String mailingState) {
        this.mailingState = mailingState;
    }

    public Integer getMailingZipCode() {
        return mailingZipCode;
    }

    public Contact mailingZipCode(Integer mailingZipCode) {
        this.mailingZipCode = mailingZipCode;
        return this;
    }

    public void setMailingZipCode(Integer mailingZipCode) {
        this.mailingZipCode = mailingZipCode;
    }

    public String getPhyisicalAddress() {
        return phyisicalAddress;
    }

    public Contact phyisicalAddress(String phyisicalAddress) {
        this.phyisicalAddress = phyisicalAddress;
        return this;
    }

    public void setPhyisicalAddress(String phyisicalAddress) {
        this.phyisicalAddress = phyisicalAddress;
    }

    public String getPhyisicalCity() {
        return phyisicalCity;
    }

    public Contact phyisicalCity(String phyisicalCity) {
        this.phyisicalCity = phyisicalCity;
        return this;
    }

    public void setPhyisicalCity(String phyisicalCity) {
        this.phyisicalCity = phyisicalCity;
    }

    public String getPhyisicalState() {
        return phyisicalState;
    }

    public Contact phyisicalState(String phyisicalState) {
        this.phyisicalState = phyisicalState;
        return this;
    }

    public void setPhyisicalState(String phyisicalState) {
        this.phyisicalState = phyisicalState;
    }

    public Integer getPhyisicalZipCode() {
        return phyisicalZipCode;
    }

    public Contact phyisicalZipCode(Integer phyisicalZipCode) {
        this.phyisicalZipCode = phyisicalZipCode;
        return this;
    }

    public void setPhyisicalZipCode(Integer phyisicalZipCode) {
        this.phyisicalZipCode = phyisicalZipCode;
    }

    public String getContactDays() {
        return contactDays;
    }

    public Contact contactDays(String contactDays) {
        this.contactDays = contactDays;
        return this;
    }

    public void setContactDays(String contactDays) {
        this.contactDays = contactDays;
    }

    public String getContactTimes() {
        return contactTimes;
    }

    public Contact contactTimes(String contactTimes) {
        this.contactTimes = contactTimes;
        return this;
    }

    public void setContactTimes(String contactTimes) {
        this.contactTimes = contactTimes;
    }

    public User getUser() {
        return user;
    }

    public Contact user(User user) {
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
        Contact contact = (Contact) o;
        if (contact.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), contact.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Contact{" +
            "id=" + getId() +
            ", companyName='" + getCompanyName() + "'" +
            ", companyEIN='" + getCompanyEIN() + "'" +
            ", companyWebsite='" + getCompanyWebsite() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", mailingAddress='" + getMailingAddress() + "'" +
            ", mailingCity='" + getMailingCity() + "'" +
            ", mailingState='" + getMailingState() + "'" +
            ", mailingZipCode=" + getMailingZipCode() +
            ", phyisicalAddress='" + getPhyisicalAddress() + "'" +
            ", phyisicalCity='" + getPhyisicalCity() + "'" +
            ", phyisicalState='" + getPhyisicalState() + "'" +
            ", phyisicalZipCode=" + getPhyisicalZipCode() +
            ", contactDays='" + getContactDays() + "'" +
            ", contactTimes='" + getContactTimes() + "'" +
            "}";
    }
}
