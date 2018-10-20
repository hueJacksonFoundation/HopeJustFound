package org.huejacksonfoundation.hopejustfound.repository;

import org.huejacksonfoundation.hopejustfound.domain.Unskilled;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Unskilled entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UnskilledRepository extends JpaRepository<Unskilled, Long> {

}
