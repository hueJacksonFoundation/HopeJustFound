package org.huejacksonfoundation.hopejustfound.web.rest.vm;

import org.huejacksonfoundation.hopejustfound.service.dto.UserDTO;
import javax.validation.constraints.Size;
import java.io.File;
import java.time.LocalDate;

/**
 * View Model extending the UserDTO, which is meant to be used in the user management UI.
 */
public class ManagedUserVM extends UserDTO {

    public static final int PASSWORD_MIN_LENGTH = 4;

    public static final int PASSWORD_MAX_LENGTH = 100;

    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
    private String password;
    private String phoneNumber;
    private String address;
    private String city;
    private String state;
    private Integer zipCode;
    private String contactDays;
    private String contactTimes;
    private LocalDate submitted;
    private String role;

    public File getFile()
        {
            return file;
        }

    public void setFile(File file)
        {
            this.file = file;
        }

    private File file;


    public ManagedUserVM() {
        // Empty constructor needed for Jackson.
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public LocalDate getSubmitted()
        {
            return submitted;
        }

    public void setSubmitted(LocalDate submitted)
        {
            this.submitted = submitted;
        }

    public String getRole()
        {
            return role;
        }

    public void setRole(String role)
        {
            this.role = role;
        }

    @Override
    public String toString() {
        return "ManagedUserVM{" +
            "} " + super.toString();
    }
}
