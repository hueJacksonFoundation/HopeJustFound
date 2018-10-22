package org.huejacksonfoundation.hopejustfound.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Status.
 */
@Entity
@Table(name = "status")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Status implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "approved")
    private LocalDate approved;

    @Column(name = "submitted")
    private LocalDate submitted;

    @Column(name = "jhi_role")
    private String role;

    @OneToOne    @JoinColumn(unique = true)
    private User userid;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getApproved() {
        return approved;
    }

    public Status approved(LocalDate approved) {
        this.approved = approved;
        return this;
    }

    public void setApproved(LocalDate approved) {
        this.approved = approved;
    }

    public LocalDate getSubmitted() {
        return submitted;
    }

    public Status submitted(LocalDate submitted) {
        this.submitted = submitted;
        return this;
    }

    public void setSubmitted(LocalDate submitted) {
        this.submitted = submitted;
    }

    public String getRole() {
        return role;
    }

    public Status role(String role) {
        this.role = role;
        return this;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User getUserid() {
        return userid;
    }

    public Status userid(User user) {
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
        Status status = (Status) o;
        if (status.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), status.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Status{" +
            "id=" + getId() +
            ", approved='" + getApproved() + "'" +
            ", submitted='" + getSubmitted() + "'" +
            ", role='" + getRole() + "'" +
            "}";
    }
}
