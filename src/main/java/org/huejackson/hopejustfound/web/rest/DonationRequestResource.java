package org.huejackson.hopejustfound.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.huejackson.hopejustfound.domain.DonationRequest;
import org.huejackson.hopejustfound.repository.DonationRequestRepository;
import org.huejackson.hopejustfound.web.rest.errors.BadRequestAlertException;
import org.huejackson.hopejustfound.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing DonationRequest.
 */
@RestController
@RequestMapping("/api")
public class DonationRequestResource {

    private final Logger log = LoggerFactory.getLogger(DonationRequestResource.class);

    private static final String ENTITY_NAME = "donationRequest";

    private final DonationRequestRepository donationRequestRepository;

    public DonationRequestResource(DonationRequestRepository donationRequestRepository) {
        this.donationRequestRepository = donationRequestRepository;
    }

    /**
     * POST  /donation-requests : Create a new donationRequest.
     *
     * @param donationRequest the donationRequest to create
     * @return the ResponseEntity with status 201 (Created) and with body the new donationRequest, or with status 400 (Bad Request) if the donationRequest has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/donation-requests")
    @Timed
    public ResponseEntity<DonationRequest> createDonationRequest(@RequestBody DonationRequest donationRequest) throws URISyntaxException {
        log.debug("REST request to save DonationRequest : {}", donationRequest);
        if (donationRequest.getId() != null) {
            throw new BadRequestAlertException("A new donationRequest cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DonationRequest result = donationRequestRepository.save(donationRequest);
        return ResponseEntity.created(new URI("/api/donation-requests/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /donation-requests : Updates an existing donationRequest.
     *
     * @param donationRequest the donationRequest to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated donationRequest,
     * or with status 400 (Bad Request) if the donationRequest is not valid,
     * or with status 500 (Internal Server Error) if the donationRequest couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/donation-requests")
    @Timed
    public ResponseEntity<DonationRequest> updateDonationRequest(@RequestBody DonationRequest donationRequest) throws URISyntaxException {
        log.debug("REST request to update DonationRequest : {}", donationRequest);
        if (donationRequest.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DonationRequest result = donationRequestRepository.save(donationRequest);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, donationRequest.getId().toString()))
            .body(result);
    }

    /**
     * GET  /donation-requests : get all the donationRequests.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of donationRequests in body
     */
    @GetMapping("/donation-requests")
    @Timed
    public List<DonationRequest> getAllDonationRequests() {
        log.debug("REST request to get all DonationRequests");
        return donationRequestRepository.findAll();
    }

    /**
     * GET  /donation-requests/:id : get the "id" donationRequest.
     *
     * @param id the id of the donationRequest to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the donationRequest, or with status 404 (Not Found)
     */
    @GetMapping("/donation-requests/{id}")
    @Timed
    public ResponseEntity<DonationRequest> getDonationRequest(@PathVariable Long id) {
        log.debug("REST request to get DonationRequest : {}", id);
        Optional<DonationRequest> donationRequest = donationRequestRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(donationRequest);
    }

    /**
     * DELETE  /donation-requests/:id : delete the "id" donationRequest.
     *
     * @param id the id of the donationRequest to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/donation-requests/{id}")
    @Timed
    public ResponseEntity<Void> deleteDonationRequest(@PathVariable Long id) {
        log.debug("REST request to delete DonationRequest : {}", id);

        donationRequestRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
