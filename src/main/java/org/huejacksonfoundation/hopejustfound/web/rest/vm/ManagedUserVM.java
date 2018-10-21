package org.huejacksonfoundation.hopejustfound.web.rest.vm;

import org.huejacksonfoundation.hopejustfound.service.dto.UserDTO;
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
    private LocalDate approved;
    private LocalDate submitted;

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

    public String getApprovedBy()
        {
            return approvedBy;
        }

    public void setApprovedBy(String approvedBy)
        {
            this.approvedBy = approvedBy;
        }

    private String approvedBy;


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
