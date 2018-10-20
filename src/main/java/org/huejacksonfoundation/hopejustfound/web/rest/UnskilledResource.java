package org.huejacksonfoundation.hopejustfound.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.huejacksonfoundation.hopejustfound.domain.Unskilled;
import org.huejacksonfoundation.hopejustfound.repository.UnskilledRepository;
import org.huejacksonfoundation.hopejustfound.web.rest.errors.BadRequestAlertException;
import org.huejacksonfoundation.hopejustfound.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * REST controller for managing Unskilled.
 */
@RestController
@RequestMapping("/api")
public class UnskilledResource {

    private final Logger log = LoggerFactory.getLogger(UnskilledResource.class);

    private static final String ENTITY_NAME = "unskilled";

    private UnskilledRepository unskilledRepository;

    public UnskilledResource(UnskilledRepository unskilledRepository) {
        this.unskilledRepository = unskilledRepository;
    }

    /**
     * POST  /unskilleds : Create a new unskilled.
     *
     * @param unskilled the unskilled to create
     * @return the ResponseEntity with status 201 (Created) and with body the new unskilled, or with status 400 (Bad Request) if the unskilled has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/unskilleds")
    @Timed
    public ResponseEntity<Unskilled> createUnskilled(@RequestBody Unskilled unskilled) throws URISyntaxException {
        log.debug("REST request to save Unskilled : {}", unskilled);
        if (unskilled.getId() != null) {
            throw new BadRequestAlertException("A new unskilled cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Unskilled result = unskilledRepository.save(unskilled);
        return ResponseEntity.created(new URI("/api/unskilleds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /unskilleds : Updates an existing unskilled.
     *
     * @param unskilled the unskilled to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated unskilled,
     * or with status 400 (Bad Request) if the unskilled is not valid,
     * or with status 500 (Internal Server Error) if the unskilled couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/unskilleds")
    @Timed
    public ResponseEntity<Unskilled> updateUnskilled(@RequestBody Unskilled unskilled) throws URISyntaxException {
        log.debug("REST request to update Unskilled : {}", unskilled);
        if (unskilled.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Unskilled result = unskilledRepository.save(unskilled);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, unskilled.getId().toString()))
            .body(result);
    }

    /**
     * GET  /unskilleds : get all the unskilleds.
     *
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of unskilleds in body
     */
    @GetMapping("/unskilleds")
    @Timed
    public List<Unskilled> getAllUnskilleds(@RequestParam(required = false) String filter) {
        if ("donation-is-null".equals(filter)) {
            log.debug("REST request to get all Unskilleds where donation is null");
            return StreamSupport
                .stream(unskilledRepository.findAll().spliterator(), false)
                .filter(unskilled -> unskilled.getDonation() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all Unskilleds");
        return unskilledRepository.findAll();
    }

    /**
     * GET  /unskilleds/:id : get the "id" unskilled.
     *
     * @param id the id of the unskilled to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the unskilled, or with status 404 (Not Found)
     */
    @GetMapping("/unskilleds/{id}")
    @Timed
    public ResponseEntity<Unskilled> getUnskilled(@PathVariable Long id) {
        log.debug("REST request to get Unskilled : {}", id);
        Optional<Unskilled> unskilled = unskilledRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(unskilled);
    }

    /**
     * DELETE  /unskilleds/:id : delete the "id" unskilled.
     *
     * @param id the id of the unskilled to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/unskilleds/{id}")
    @Timed
    public ResponseEntity<Void> deleteUnskilled(@PathVariable Long id) {
        log.debug("REST request to delete Unskilled : {}", id);

        unskilledRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
