package org.huejackson.hopejustfound.web.rest.vm;

import org.huejackson.hopejustfound.service.dto.UserDTO;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * View Model extending the UserDTO, which is meant to be used in the user management UI.
 */
public class ManagedUserVM extends UserDTO {

    public static final int PASSWORD_MIN_LENGTH = 4;

    public static final int PASSWORD_MAX_LENGTH = 100;

    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
    private String password;
    private String companyName;
    private String companyWebsite;
    private String companyEIN;
    private String phoneNumber;
    private String mailingAddress;
    private String mailingCity;
    private String mailingState;
    private Integer mailingZipCode;
    private String phyisicalAddress;
    private String phyisicalCity;
    private String phyisicalState;
    private Integer phyisicalZipCode;
    private String contactDays;
    private String contactTimes;
    private LocalDate approved;
    private LocalDate submitted;
    private String authority;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyWebsite() {
        return companyWebsite;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }

    public String getCompanyEIN() {
        return companyEIN;
    }

    public void setCompanyEIN(String companyEIN) {
        this.companyEIN = companyEIN;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public String getMailingCity() {
        return mailingCity;
    }

    public void setMailingCity(String mailingCity) {
        this.mailingCity = mailingCity;
    }

    public String getMailingState() {
        return mailingState;
    }

    public void setMailingState(String mailingState) {
        this.mailingState = mailingState;
    }

    public Integer getMailingZipCode() {
        return mailingZipCode;
    }

    public void setMailingZipCode(Integer mailingZipCode) {
        this.mailingZipCode = mailingZipCode;
    }

    public String getPhyisicalAddress() {
        return phyisicalAddress;
    }

    public void setPhyisicalAddress(String phyisicalAddress) {
        this.phyisicalAddress = phyisicalAddress;
    }

    public String getPhyisicalCity() {
        return phyisicalCity;
    }

    public void setPhyisicalCity(String phyisicalCity) {
        this.phyisicalCity = phyisicalCity;
    }

    public String getPhyisicalState() {
        return phyisicalState;
    }

    public void setPhyisicalState(String phyisicalState) {
        this.phyisicalState = phyisicalState;
    }

    public Integer getPhyisicalZipCode() {
        return phyisicalZipCode;
    }

    public void setPhyisicalZipCode(Integer phyisicalZipCode) {
        this.phyisicalZipCode = phyisicalZipCode;
    }

    public String getContactDays() {
        return contactDays;
    }

    public void setContactDays(String contactDays) {
        this.contactDays = contactDays;
    }

    public String getContactTimes() {
        return contactTimes;
    }

    public void setContactTimes(String contactTimes) {
        this.contactTimes = contactTimes;
    }

    public LocalDate getApproved() {
        return approved;
    }

    public void setApproved(LocalDate approved) {
        this.approved = approved;
    }

    public LocalDate getSubmitted() {
        return submitted;
    }

    public void setSubmitted(LocalDate submitted) {
        this.submitted = submitted;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public ManagedUserVM() {
        // Empty constructor needed for Jackson.
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ManagedUserVM{" +
            "} " + super.toString();
    }
}
