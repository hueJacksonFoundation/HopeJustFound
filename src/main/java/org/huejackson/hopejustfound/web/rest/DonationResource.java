package org.huejackson.hopejustfound.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.huejackson.hopejustfound.domain.Donation;
import org.huejackson.hopejustfound.repository.DonationRepository;
import org.huejackson.hopejustfound.web.rest.errors.BadRequestAlertException;
import org.huejackson.hopejustfound.web.rest.util.HeaderUtil;
import org.huejackson.hopejustfound.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Donation.
 */
@RestController
@RequestMapping("/api")
public class DonationResource {

    private final Logger log = LoggerFactory.getLogger(DonationResource.class);

    private static final String ENTITY_NAME = "donation";

    private DonationRepository donationRepository;

    public DonationResource(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    /**
     * POST  /donations : Create a new donation.
     *
     * @param donation the donation to create
     * @return the ResponseEntity with status 201 (Created) and with body the new donation, or with status 400 (Bad Request) if the donation has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/donations")
    @Timed
    public ResponseEntity<Donation> createDonation(@RequestBody Donation donation) throws URISyntaxException {
        log.debug("REST request to save Donation : {}", donation);
        if (donation.getId() != null) {
            throw new BadRequestAlertException("A new donation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Donation result = donationRepository.save(donation);
        return ResponseEntity.created(new URI("/api/donations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /donations : Updates an existing donation.
     *
     * @param donation the donation to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated donation,
     * or with status 400 (Bad Request) if the donation is not valid,
     * or with status 500 (Internal Server Error) if the donation couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/donations")
    @Timed
    public ResponseEntity<Donation> updateDonation(@RequestBody Donation donation) throws URISyntaxException {
        log.debug("REST request to update Donation : {}", donation);
        if (donation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Donation result = donationRepository.save(donation);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, donation.getId().toString()))
            .body(result);
    }

    /**
     * GET  /donations : get all the donations.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of donations in body
     */
    @GetMapping("/donations")
    @Timed
    public ResponseEntity<List<Donation>> getAllDonations(Pageable pageable) {
        log.debug("REST request to get a page of Donations");
        Page<Donation> page = donationRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/donations");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /donations/:id : get the "id" donation.
     *
     * @param id the id of the donation to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the donation, or with status 404 (Not Found)
     */
    @GetMapping("/donations/{id}")
    @Timed
    public ResponseEntity<Donation> getDonation(@PathVariable Long id) {
        log.debug("REST request to get Donation : {}", id);
        Optional<Donation> donation = donationRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(donation);
    }

    /**
     * DELETE  /donations/:id : delete the "id" donation.
     *
     * @param id the id of the donation to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/donations/{id}")
    @Timed
    public ResponseEntity<Void> deleteDonation(@PathVariable Long id) {
        log.debug("REST request to delete Donation : {}", id);

        donationRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
