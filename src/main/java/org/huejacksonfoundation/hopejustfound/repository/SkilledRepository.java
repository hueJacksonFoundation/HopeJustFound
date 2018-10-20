package org.huejacksonfoundation.hopejustfound.repository;

import org.huejacksonfoundation.hopejustfound.domain.Skilled;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Skilled entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SkilledRepository extends JpaRepository<Skilled, Long> {

}
