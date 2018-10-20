package org.huejacksonfoundation.hopejustfound.repository;

import org.huejacksonfoundation.hopejustfound.domain.UserContact;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the UserContact entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserContactRepository extends JpaRepository<UserContact, Long> {

}
