package org.huejackson.hopejustfound.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Donation.
 */
@Entity
@Table(name = "donation")
@ConfigurationProperties(prefix = "file")
public class Donation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "goods_type")
    private String goodsType;

    @Column(name = "service_type")
    private String serviceType;

    @Lob
    @Column(name = "images")
    private byte[] images;

    @Column(name = "images_content_type")
    private String imagesContentType;

    @Column(name = "initial_date")
    private LocalDate initialDate;

    @Column(name = "expire_date")
    private LocalDate expireDate;

    @Column(name = "jhi_condition")
    private String condition;

    @Column(name = "description")
    private String description;

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

    public String getGoodsType() {
        return goodsType;
    }

    public Donation goodsType(String goodsType) {
        this.goodsType = goodsType;
        return this;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getServiceType() {
        return serviceType;
    }

    public Donation serviceType(String serviceType) {
        this.serviceType = serviceType;
        return this;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public byte[] getImages() {
        return images;
    }

    public Donation images(byte[] images) {
        this.images = images;
        return this;
    }

    public void setImages(byte[] images) {
        this.images = images;
    }

    public String getImagesContentType() {
        return imagesContentType;
    }

    public Donation imagesContentType(String imagesContentType) {
        this.imagesContentType = imagesContentType;
        return this;
    }

    public void setImagesContentType(String imagesContentType) {
        this.imagesContentType = imagesContentType;
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

    public User getUser() {
        return user;
    }

    public Donation user(User user) {
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
            ", goodsType='" + getGoodsType() + "'" +
            ", serviceType='" + getServiceType() + "'" +
            ", images='" + getImages() + "'" +
            ", imagesContentType='" + getImagesContentType() + "'" +
            ", initialDate='" + getInitialDate() + "'" +
            ", expireDate='" + getExpireDate() + "'" +
            ", condition='" + getCondition() + "'" +
            ", description='" + getDescription() + "'" +
            ", numberOfVolunteers=" + getNumberOfVolunteers() +
            "}";
    }
}
