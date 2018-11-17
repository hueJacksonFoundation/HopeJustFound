package org.huejackson.hopejustfound.repository;

import org.huejackson.hopejustfound.domain.DonationRequest;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the DonationRequest entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DonationRequestRepository extends JpaRepository<DonationRequest, Long> {

    @Query("select donation_request from DonationRequest donation_request where donation_request.user.login = ?#{principal.username}")
    List<DonationRequest> findByUserIsCurrentUser();

}
