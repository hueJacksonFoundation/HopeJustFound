package org.huejacksonfoundation.hopejustfound.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.huejacksonfoundation.hopejustfound.domain.UserContact;
import org.huejacksonfoundation.hopejustfound.repository.UserContactRepository;
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

/**
 * REST controller for managing UserContact.
 */
@RestController
@RequestMapping("/api")
public class UserContactResource {

    private final Logger log = LoggerFactory.getLogger(UserContactResource.class);

    private static final String ENTITY_NAME = "userContact";

    private UserContactRepository userContactRepository;

    public UserContactResource(UserContactRepository userContactRepository) {
        this.userContactRepository = userContactRepository;
    }

    /**
     * POST  /user-contacts : Create a new userContact.
     *
     * @param userContact the userContact to create
     * @return the ResponseEntity with status 201 (Created) and with body the new userContact, or with status 400 (Bad Request) if the userContact has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/user-contacts")
    @Timed
    public ResponseEntity<UserContact> createUserContact(@RequestBody UserContact userContact) throws URISyntaxException {
        log.debug("REST request to save UserContact : {}", userContact);
        if (userContact.getId() != null) {
            throw new BadRequestAlertException("A new userContact cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserContact result = userContactRepository.save(userContact);
        return ResponseEntity.created(new URI("/api/user-contacts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /user-contacts : Updates an existing userContact.
     *
     * @param userContact the userContact to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated userContact,
     * or with status 400 (Bad Request) if the userContact is not valid,
     * or with status 500 (Internal Server Error) if the userContact couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/user-contacts")
    @Timed
    public ResponseEntity<UserContact> updateUserContact(@RequestBody UserContact userContact) throws URISyntaxException {
        log.debug("REST request to update UserContact : {}", userContact);
        if (userContact.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserContact result = userContactRepository.save(userContact);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, userContact.getId().toString()))
            .body(result);
    }

    /**
     * GET  /user-contacts : get all the userContacts.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of userContacts in body
     */
    @GetMapping("/user-contacts")
    @Timed
    public List<UserContact> getAllUserContacts() {
        log.debug("REST request to get all UserContacts");
        return userContactRepository.findAll();
    }

    /**
     * GET  /user-contacts/:id : get the "id" userContact.
     *
     * @param id the id of the userContact to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the userContact, or with status 404 (Not Found)
     */
    @GetMapping("/user-contacts/{id}")
    @Timed
    public ResponseEntity<UserContact> getUserContact(@PathVariable Long id) {
        log.debug("REST request to get UserContact : {}", id);
        Optional<UserContact> userContact = userContactRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(userContact);
    }

    /**
     * DELETE  /user-contacts/:id : delete the "id" userContact.
     *
     * @param id the id of the userContact to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/user-contacts/{id}")
    @Timed
    public ResponseEntity<Void> deleteUserContact(@PathVariable Long id) {
        log.debug("REST request to delete UserContact : {}", id);

        userContactRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
