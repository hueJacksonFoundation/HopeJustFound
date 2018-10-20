package org.huejacksonfoundation.hopejustfound.web.rest;

import org.huejacksonfoundation.hopejustfound.HopeJustFoundApp;

import org.huejacksonfoundation.hopejustfound.domain.UserContact;
import org.huejacksonfoundation.hopejustfound.repository.UserContactRepository;
import org.huejacksonfoundation.hopejustfound.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static org.huejacksonfoundation.hopejustfound.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the UserContactResource REST controller.
 *
 * @see UserContactResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HopeJustFoundApp.class)
public class UserContactResourceIntTest {

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_STATE = "BBBBBBBBBB";

    private static final Integer DEFAULT_ZIP_CODE = 1;
    private static final Integer UPDATED_ZIP_CODE = 2;

    private static final String DEFAULT_CONTACT_DAYS = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_DAYS = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_TIMES = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_TIMES = "BBBBBBBBBB";

    @Autowired
    private UserContactRepository userContactRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUserContactMockMvc;

    private UserContact userContact;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UserContactResource userContactResource = new UserContactResource(userContactRepository);
        this.restUserContactMockMvc = MockMvcBuilders.standaloneSetup(userContactResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserContact createEntity(EntityManager em) {
        UserContact userContact = new UserContact()
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .address(DEFAULT_ADDRESS)
            .city(DEFAULT_CITY)
            .state(DEFAULT_STATE)
            .zipCode(DEFAULT_ZIP_CODE)
            .contactDays(DEFAULT_CONTACT_DAYS)
            .contactTimes(DEFAULT_CONTACT_TIMES);
        return userContact;
    }

    @Before
    public void initTest() {
        userContact = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserContact() throws Exception {
        int databaseSizeBeforeCreate = userContactRepository.findAll().size();

        // Create the UserContact
        restUserContactMockMvc.perform(post("/api/user-contacts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userContact)))
            .andExpect(status().isCreated());

        // Validate the UserContact in the database
        List<UserContact> userContactList = userContactRepository.findAll();
        assertThat(userContactList).hasSize(databaseSizeBeforeCreate + 1);
        UserContact testUserContact = userContactList.get(userContactList.size() - 1);
        assertThat(testUserContact.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testUserContact.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testUserContact.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testUserContact.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testUserContact.getZipCode()).isEqualTo(DEFAULT_ZIP_CODE);
        assertThat(testUserContact.getContactDays()).isEqualTo(DEFAULT_CONTACT_DAYS);
        assertThat(testUserContact.getContactTimes()).isEqualTo(DEFAULT_CONTACT_TIMES);
    }

    @Test
    @Transactional
    public void createUserContactWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userContactRepository.findAll().size();

        // Create the UserContact with an existing ID
        userContact.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserContactMockMvc.perform(post("/api/user-contacts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userContact)))
            .andExpect(status().isBadRequest());

        // Validate the UserContact in the database
        List<UserContact> userContactList = userContactRepository.findAll();
        assertThat(userContactList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllUserContacts() throws Exception {
        // Initialize the database
        userContactRepository.saveAndFlush(userContact);

        // Get all the userContactList
        restUserContactMockMvc.perform(get("/api/user-contacts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userContact.getId().intValue())))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY.toString())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())))
            .andExpect(jsonPath("$.[*].zipCode").value(hasItem(DEFAULT_ZIP_CODE)))
            .andExpect(jsonPath("$.[*].contactDays").value(hasItem(DEFAULT_CONTACT_DAYS.toString())))
            .andExpect(jsonPath("$.[*].contactTimes").value(hasItem(DEFAULT_CONTACT_TIMES.toString())));
    }
    
    @Test
    @Transactional
    public void getUserContact() throws Exception {
        // Initialize the database
        userContactRepository.saveAndFlush(userContact);

        // Get the userContact
        restUserContactMockMvc.perform(get("/api/user-contacts/{id}", userContact.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(userContact.getId().intValue()))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY.toString()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()))
            .andExpect(jsonPath("$.zipCode").value(DEFAULT_ZIP_CODE))
            .andExpect(jsonPath("$.contactDays").value(DEFAULT_CONTACT_DAYS.toString()))
            .andExpect(jsonPath("$.contactTimes").value(DEFAULT_CONTACT_TIMES.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingUserContact() throws Exception {
        // Get the userContact
        restUserContactMockMvc.perform(get("/api/user-contacts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserContact() throws Exception {
        // Initialize the database
        userContactRepository.saveAndFlush(userContact);

        int databaseSizeBeforeUpdate = userContactRepository.findAll().size();

        // Update the userContact
        UserContact updatedUserContact = userContactRepository.findById(userContact.getId()).get();
        // Disconnect from session so that the updates on updatedUserContact are not directly saved in db
        em.detach(updatedUserContact);
        updatedUserContact
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .address(UPDATED_ADDRESS)
            .city(UPDATED_CITY)
            .state(UPDATED_STATE)
            .zipCode(UPDATED_ZIP_CODE)
            .contactDays(UPDATED_CONTACT_DAYS)
            .contactTimes(UPDATED_CONTACT_TIMES);

        restUserContactMockMvc.perform(put("/api/user-contacts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedUserContact)))
            .andExpect(status().isOk());

        // Validate the UserContact in the database
        List<UserContact> userContactList = userContactRepository.findAll();
        assertThat(userContactList).hasSize(databaseSizeBeforeUpdate);
        UserContact testUserContact = userContactList.get(userContactList.size() - 1);
        assertThat(testUserContact.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testUserContact.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testUserContact.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testUserContact.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testUserContact.getZipCode()).isEqualTo(UPDATED_ZIP_CODE);
        assertThat(testUserContact.getContactDays()).isEqualTo(UPDATED_CONTACT_DAYS);
        assertThat(testUserContact.getContactTimes()).isEqualTo(UPDATED_CONTACT_TIMES);
    }

    @Test
    @Transactional
    public void updateNonExistingUserContact() throws Exception {
        int databaseSizeBeforeUpdate = userContactRepository.findAll().size();

        // Create the UserContact

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserContactMockMvc.perform(put("/api/user-contacts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userContact)))
            .andExpect(status().isBadRequest());

        // Validate the UserContact in the database
        List<UserContact> userContactList = userContactRepository.findAll();
        assertThat(userContactList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUserContact() throws Exception {
        // Initialize the database
        userContactRepository.saveAndFlush(userContact);

        int databaseSizeBeforeDelete = userContactRepository.findAll().size();

        // Get the userContact
        restUserContactMockMvc.perform(delete("/api/user-contacts/{id}", userContact.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UserContact> userContactList = userContactRepository.findAll();
        assertThat(userContactList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserContact.class);
        UserContact userContact1 = new UserContact();
        userContact1.setId(1L);
        UserContact userContact2 = new UserContact();
        userContact2.setId(userContact1.getId());
        assertThat(userContact1).isEqualTo(userContact2);
        userContact2.setId(2L);
        assertThat(userContact1).isNotEqualTo(userContact2);
        userContact1.setId(null);
        assertThat(userContact1).isNotEqualTo(userContact2);
    }
}
