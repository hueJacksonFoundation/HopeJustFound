package org.huejacksonfoundation.hopejustfound.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.huejacksonfoundation.hopejustfound.domain.Skilled;
import org.huejacksonfoundation.hopejustfound.repository.SkilledRepository;
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
 * REST controller for managing Skilled.
 */
@RestController
@RequestMapping("/api")
public class SkilledResource {

    private final Logger log = LoggerFactory.getLogger(SkilledResource.class);

    private static final String ENTITY_NAME = "skilled";

    private SkilledRepository skilledRepository;

    public SkilledResource(SkilledRepository skilledRepository) {
        this.skilledRepository = skilledRepository;
    }

    /**
     * POST  /skilleds : Create a new skilled.
     *
     * @param skilled the skilled to create
     * @return the ResponseEntity with status 201 (Created) and with body the new skilled, or with status 400 (Bad Request) if the skilled has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/skilleds")
    @Timed
    public ResponseEntity<Skilled> createSkilled(@RequestBody Skilled skilled) throws URISyntaxException {
        log.debug("REST request to save Skilled : {}", skilled);
        if (skilled.getId() != null) {
            throw new BadRequestAlertException("A new skilled cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Skilled result = skilledRepository.save(skilled);
        return ResponseEntity.created(new URI("/api/skilleds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /skilleds : Updates an existing skilled.
     *
     * @param skilled the skilled to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated skilled,
     * or with status 400 (Bad Request) if the skilled is not valid,
     * or with status 500 (Internal Server Error) if the skilled couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/skilleds")
    @Timed
    public ResponseEntity<Skilled> updateSkilled(@RequestBody Skilled skilled) throws URISyntaxException {
        log.debug("REST request to update Skilled : {}", skilled);
        if (skilled.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Skilled result = skilledRepository.save(skilled);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, skilled.getId().toString()))
            .body(result);
    }

    /**
     * GET  /skilleds : get all the skilleds.
     *
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of skilleds in body
     */
    @GetMapping("/skilleds")
    @Timed
    public List<Skilled> getAllSkilleds(@RequestParam(required = false) String filter) {
        if ("donation-is-null".equals(filter)) {
            log.debug("REST request to get all Skilleds where donation is null");
            return StreamSupport
                .stream(skilledRepository.findAll().spliterator(), false)
                .filter(skilled -> skilled.getDonation() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all Skilleds");
        return skilledRepository.findAll();
    }

    /**
     * GET  /skilleds/:id : get the "id" skilled.
     *
     * @param id the id of the skilled to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the skilled, or with status 404 (Not Found)
     */
    @GetMapping("/skilleds/{id}")
    @Timed
    public ResponseEntity<Skilled> getSkilled(@PathVariable Long id) {
        log.debug("REST request to get Skilled : {}", id);
        Optional<Skilled> skilled = skilledRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(skilled);
    }

    /**
     * DELETE  /skilleds/:id : delete the "id" skilled.
     *
     * @param id the id of the skilled to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/skilleds/{id}")
    @Timed
    public ResponseEntity<Void> deleteSkilled(@PathVariable Long id) {
        log.debug("REST request to delete Skilled : {}", id);

        skilledRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
