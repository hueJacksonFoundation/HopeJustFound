package org.huejackson.hopejustfound.web.rest;

import org.huejackson.hopejustfound.HopeJustFoundApp;

import org.huejackson.hopejustfound.domain.Contact;
import org.huejackson.hopejustfound.repository.ContactRepository;
import org.huejackson.hopejustfound.web.rest.errors.ExceptionTranslator;

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


import static org.huejackson.hopejustfound.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ContactResource REST controller.
 *
 * @see ContactResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HopeJustFoundApp.class)
public class ContactResourceIntTest {

    private static final String DEFAULT_COMPANY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY_EIN = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_EIN = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY_WEBSITE = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_WEBSITE = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_MAILING_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_MAILING_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_MAILING_CITY = "AAAAAAAAAA";
    private static final String UPDATED_MAILING_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_MAILING_STATE = "AAAAAAAAAA";
    private static final String UPDATED_MAILING_STATE = "BBBBBBBBBB";

    private static final Integer DEFAULT_MAILING_ZIP_CODE = 1;
    private static final Integer UPDATED_MAILING_ZIP_CODE = 2;

    private static final String DEFAULT_PHYISICAL_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_PHYISICAL_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_PHYISICAL_CITY = "AAAAAAAAAA";
    private static final String UPDATED_PHYISICAL_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_PHYISICAL_STATE = "AAAAAAAAAA";
    private static final String UPDATED_PHYISICAL_STATE = "BBBBBBBBBB";

    private static final Integer DEFAULT_PHYISICAL_ZIP_CODE = 1;
    private static final Integer UPDATED_PHYISICAL_ZIP_CODE = 2;

    private static final String DEFAULT_CONTACT_DAYS = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_DAYS = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_TIMES = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_TIMES = "BBBBBBBBBB";

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restContactMockMvc;

    private Contact contact;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ContactResource contactResource = new ContactResource(contactRepository);
        this.restContactMockMvc = MockMvcBuilders.standaloneSetup(contactResource)
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
    public static Contact createEntity(EntityManager em) {
        Contact contact = new Contact()
            .companyName(DEFAULT_COMPANY_NAME)
            .companyEIN(DEFAULT_COMPANY_EIN)
            .companyWebsite(DEFAULT_COMPANY_WEBSITE)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .mailingAddress(DEFAULT_MAILING_ADDRESS)
            .mailingCity(DEFAULT_MAILING_CITY)
            .mailingState(DEFAULT_MAILING_STATE)
            .mailingZipCode(DEFAULT_MAILING_ZIP_CODE)
            .phyisicalAddress(DEFAULT_PHYISICAL_ADDRESS)
            .phyisicalCity(DEFAULT_PHYISICAL_CITY)
            .phyisicalState(DEFAULT_PHYISICAL_STATE)
            .phyisicalZipCode(DEFAULT_PHYISICAL_ZIP_CODE)
            .contactDays(DEFAULT_CONTACT_DAYS)
            .contactTimes(DEFAULT_CONTACT_TIMES);
        return contact;
    }

    @Before
    public void initTest() {
        contact = createEntity(em);
    }

    @Test
    @Transactional
    public void createContact() throws Exception {
        int databaseSizeBeforeCreate = contactRepository.findAll().size();

        // Create the Contact
        restContactMockMvc.perform(post("/api/contacts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contact)))
            .andExpect(status().isCreated());

        // Validate the Contact in the database
        List<Contact> contactList = contactRepository.findAll();
        assertThat(contactList).hasSize(databaseSizeBeforeCreate + 1);
        Contact testContact = contactList.get(contactList.size() - 1);
        assertThat(testContact.getCompanyName()).isEqualTo(DEFAULT_COMPANY_NAME);
        assertThat(testContact.getCompanyEIN()).isEqualTo(DEFAULT_COMPANY_EIN);
        assertThat(testContact.getCompanyWebsite()).isEqualTo(DEFAULT_COMPANY_WEBSITE);
        assertThat(testContact.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testContact.getMailingAddress()).isEqualTo(DEFAULT_MAILING_ADDRESS);
        assertThat(testContact.getMailingCity()).isEqualTo(DEFAULT_MAILING_CITY);
        assertThat(testContact.getMailingState()).isEqualTo(DEFAULT_MAILING_STATE);
        assertThat(testContact.getMailingZipCode()).isEqualTo(DEFAULT_MAILING_ZIP_CODE);
        assertThat(testContact.getPhyisicalAddress()).isEqualTo(DEFAULT_PHYISICAL_ADDRESS);
        assertThat(testContact.getPhyisicalCity()).isEqualTo(DEFAULT_PHYISICAL_CITY);
        assertThat(testContact.getPhyisicalState()).isEqualTo(DEFAULT_PHYISICAL_STATE);
        assertThat(testContact.getPhyisicalZipCode()).isEqualTo(DEFAULT_PHYISICAL_ZIP_CODE);
        assertThat(testContact.getContactDays()).isEqualTo(DEFAULT_CONTACT_DAYS);
        assertThat(testContact.getContactTimes()).isEqualTo(DEFAULT_CONTACT_TIMES);
    }

    @Test
    @Transactional
    public void createContactWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = contactRepository.findAll().size();

        // Create the Contact with an existing ID
        contact.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restContactMockMvc.perform(post("/api/contacts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contact)))
            .andExpect(status().isBadRequest());

        // Validate the Contact in the database
        List<Contact> contactList = contactRepository.findAll();
        assertThat(contactList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllContacts() throws Exception {
        // Initialize the database
        contactRepository.saveAndFlush(contact);

        // Get all the contactList
        restContactMockMvc.perform(get("/api/contacts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contact.getId().intValue())))
            .andExpect(jsonPath("$.[*].companyName").value(hasItem(DEFAULT_COMPANY_NAME.toString())))
            .andExpect(jsonPath("$.[*].companyEIN").value(hasItem(DEFAULT_COMPANY_EIN.toString())))
            .andExpect(jsonPath("$.[*].companyWebsite").value(hasItem(DEFAULT_COMPANY_WEBSITE.toString())))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].mailingAddress").value(hasItem(DEFAULT_MAILING_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].mailingCity").value(hasItem(DEFAULT_MAILING_CITY.toString())))
            .andExpect(jsonPath("$.[*].mailingState").value(hasItem(DEFAULT_MAILING_STATE.toString())))
            .andExpect(jsonPath("$.[*].mailingZipCode").value(hasItem(DEFAULT_MAILING_ZIP_CODE)))
            .andExpect(jsonPath("$.[*].phyisicalAddress").value(hasItem(DEFAULT_PHYISICAL_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].phyisicalCity").value(hasItem(DEFAULT_PHYISICAL_CITY.toString())))
            .andExpect(jsonPath("$.[*].phyisicalState").value(hasItem(DEFAULT_PHYISICAL_STATE.toString())))
            .andExpect(jsonPath("$.[*].phyisicalZipCode").value(hasItem(DEFAULT_PHYISICAL_ZIP_CODE)))
            .andExpect(jsonPath("$.[*].contactDays").value(hasItem(DEFAULT_CONTACT_DAYS.toString())))
            .andExpect(jsonPath("$.[*].contactTimes").value(hasItem(DEFAULT_CONTACT_TIMES.toString())));
    }
    
    @Test
    @Transactional
    public void getContact() throws Exception {
        // Initialize the database
        contactRepository.saveAndFlush(contact);

        // Get the contact
        restContactMockMvc.perform(get("/api/contacts/{id}", contact.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(contact.getId().intValue()))
            .andExpect(jsonPath("$.companyName").value(DEFAULT_COMPANY_NAME.toString()))
            .andExpect(jsonPath("$.companyEIN").value(DEFAULT_COMPANY_EIN.toString()))
            .andExpect(jsonPath("$.companyWebsite").value(DEFAULT_COMPANY_WEBSITE.toString()))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER.toString()))
            .andExpect(jsonPath("$.mailingAddress").value(DEFAULT_MAILING_ADDRESS.toString()))
            .andExpect(jsonPath("$.mailingCity").value(DEFAULT_MAILING_CITY.toString()))
            .andExpect(jsonPath("$.mailingState").value(DEFAULT_MAILING_STATE.toString()))
            .andExpect(jsonPath("$.mailingZipCode").value(DEFAULT_MAILING_ZIP_CODE))
            .andExpect(jsonPath("$.phyisicalAddress").value(DEFAULT_PHYISICAL_ADDRESS.toString()))
            .andExpect(jsonPath("$.phyisicalCity").value(DEFAULT_PHYISICAL_CITY.toString()))
            .andExpect(jsonPath("$.phyisicalState").value(DEFAULT_PHYISICAL_STATE.toString()))
            .andExpect(jsonPath("$.phyisicalZipCode").value(DEFAULT_PHYISICAL_ZIP_CODE))
            .andExpect(jsonPath("$.contactDays").value(DEFAULT_CONTACT_DAYS.toString()))
            .andExpect(jsonPath("$.contactTimes").value(DEFAULT_CONTACT_TIMES.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingContact() throws Exception {
        // Get the contact
        restContactMockMvc.perform(get("/api/contacts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateContact() throws Exception {
        // Initialize the database
        contactRepository.saveAndFlush(contact);

        int databaseSizeBeforeUpdate = contactRepository.findAll().size();

        // Update the contact
        Contact updatedContact = contactRepository.findById(contact.getId()).get();
        // Disconnect from session so that the updates on updatedContact are not directly saved in db
        em.detach(updatedContact);
        updatedContact
            .companyName(UPDATED_COMPANY_NAME)
            .companyEIN(UPDATED_COMPANY_EIN)
            .companyWebsite(UPDATED_COMPANY_WEBSITE)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .mailingAddress(UPDATED_MAILING_ADDRESS)
            .mailingCity(UPDATED_MAILING_CITY)
            .mailingState(UPDATED_MAILING_STATE)
            .mailingZipCode(UPDATED_MAILING_ZIP_CODE)
            .phyisicalAddress(UPDATED_PHYISICAL_ADDRESS)
            .phyisicalCity(UPDATED_PHYISICAL_CITY)
            .phyisicalState(UPDATED_PHYISICAL_STATE)
            .phyisicalZipCode(UPDATED_PHYISICAL_ZIP_CODE)
            .contactDays(UPDATED_CONTACT_DAYS)
            .contactTimes(UPDATED_CONTACT_TIMES);

        restContactMockMvc.perform(put("/api/contacts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedContact)))
            .andExpect(status().isOk());

        // Validate the Contact in the database
        List<Contact> contactList = contactRepository.findAll();
        assertThat(contactList).hasSize(databaseSizeBeforeUpdate);
        Contact testContact = contactList.get(contactList.size() - 1);
        assertThat(testContact.getCompanyName()).isEqualTo(UPDATED_COMPANY_NAME);
        assertThat(testContact.getCompanyEIN()).isEqualTo(UPDATED_COMPANY_EIN);
        assertThat(testContact.getCompanyWebsite()).isEqualTo(UPDATED_COMPANY_WEBSITE);
        assertThat(testContact.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testContact.getMailingAddress()).isEqualTo(UPDATED_MAILING_ADDRESS);
        assertThat(testContact.getMailingCity()).isEqualTo(UPDATED_MAILING_CITY);
        assertThat(testContact.getMailingState()).isEqualTo(UPDATED_MAILING_STATE);
        assertThat(testContact.getMailingZipCode()).isEqualTo(UPDATED_MAILING_ZIP_CODE);
        assertThat(testContact.getPhyisicalAddress()).isEqualTo(UPDATED_PHYISICAL_ADDRESS);
        assertThat(testContact.getPhyisicalCity()).isEqualTo(UPDATED_PHYISICAL_CITY);
        assertThat(testContact.getPhyisicalState()).isEqualTo(UPDATED_PHYISICAL_STATE);
        assertThat(testContact.getPhyisicalZipCode()).isEqualTo(UPDATED_PHYISICAL_ZIP_CODE);
        assertThat(testContact.getContactDays()).isEqualTo(UPDATED_CONTACT_DAYS);
        assertThat(testContact.getContactTimes()).isEqualTo(UPDATED_CONTACT_TIMES);
    }

    @Test
    @Transactional
    public void updateNonExistingContact() throws Exception {
        int databaseSizeBeforeUpdate = contactRepository.findAll().size();

        // Create the Contact

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContactMockMvc.perform(put("/api/contacts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contact)))
            .andExpect(status().isBadRequest());

        // Validate the Contact in the database
        List<Contact> contactList = contactRepository.findAll();
        assertThat(contactList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteContact() throws Exception {
        // Initialize the database
        contactRepository.saveAndFlush(contact);

        int databaseSizeBeforeDelete = contactRepository.findAll().size();

        // Get the contact
        restContactMockMvc.perform(delete("/api/contacts/{id}", contact.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Contact> contactList = contactRepository.findAll();
        assertThat(contactList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Contact.class);
        Contact contact1 = new Contact();
        contact1.setId(1L);
        Contact contact2 = new Contact();
        contact2.setId(contact1.getId());
        assertThat(contact1).isEqualTo(contact2);
        contact2.setId(2L);
        assertThat(contact1).isNotEqualTo(contact2);
        contact1.setId(null);
        assertThat(contact1).isNotEqualTo(contact2);
    }
}
