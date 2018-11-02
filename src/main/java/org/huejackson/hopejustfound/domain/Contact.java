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

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip_code")
    private Integer zipCode;

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

    public String getAddress() {
        return address;
    }

    public Contact address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public Contact city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public Contact state(String state) {
        this.state = state;
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public Contact zipCode(Integer zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
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
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", address='" + getAddress() + "'" +
            ", city='" + getCity() + "'" +
            ", state='" + getState() + "'" +
            ", zipCode=" + getZipCode() +
            ", contactDays='" + getContactDays() + "'" +
            ", contactTimes='" + getContactTimes() + "'" +
            "}";
    }
}
