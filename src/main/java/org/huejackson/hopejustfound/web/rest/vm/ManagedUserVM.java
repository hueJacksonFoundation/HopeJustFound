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
    private String phoneNumber;
    private String address;
    private String city;
    private String state;
    private Integer zipCode;
    private String contactDays;
    private String contactTimes;
    private LocalDate approved;
    private LocalDate submitted;
    private String authority;


    public String getAuthority()
        {
            return authority;
        }

    public void setAuthority(String authority)
        {
            this.authority = authority;
        }



    public String getCompanyName()
        {
            return companyName;
        }

    public void setCompanyName(String companyName)
        {
            this.companyName = companyName;
        }

    public String getPhoneNumber()
        {
            return phoneNumber;
        }

    public void setPhoneNumber(String phoneNumber)
        {
            this.phoneNumber = phoneNumber;
        }

    public String getAddress()
        {
            return address;
        }

    public void setAddress(String address)
        {
            this.address = address;
        }

    public String getCity()
        {
            return city;
        }

    public void setCity(String city)
        {
            this.city = city;
        }

    public String getState()
        {
            return state;
        }

    public void setState(String state)
        {
            this.state = state;
        }

    public Integer getZipCode()
        {
            return zipCode;
        }

    public void setZipCode(Integer zipCode)
        {
            this.zipCode = zipCode;
        }

    public String getContactDays()
        {
            return contactDays;
        }

    public void setContactDays(String contactDays)
        {
            this.contactDays = contactDays;
        }

    public String getContactTimes()
        {
            return contactTimes;
        }

    public void setContactTimes(String contactTimes)
        {
            this.contactTimes = contactTimes;
        }

    public LocalDate getApproved()
        {
            return approved;
        }

    public void setApproved(LocalDate approved)
        {
            this.approved = approved;
        }

    public LocalDate getSubmitted()
        {
            return submitted;
        }

    public void setSubmitted(LocalDate submitted)
        {
            this.submitted = submitted;
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
