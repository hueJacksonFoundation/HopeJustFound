package org.huejackson.hopejustfound.web.rest;

import org.huejackson.hopejustfound.HopeJustFoundApp;

import org.huejackson.hopejustfound.domain.DonationRequest;
import org.huejackson.hopejustfound.repository.DonationRequestRepository;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


import static org.huejackson.hopejustfound.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the DonationRequestResource REST controller.
 *
 * @see DonationRequestResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HopeJustFoundApp.class)
public class DonationRequestResourceIntTest {

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_INITIAL_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_INITIAL_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_EXPIRE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_EXPIRE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CONDITION = "AAAAAAAAAA";
    private static final String UPDATED_CONDITION = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_EXPERIENCE = "AAAAAAAAAA";
    private static final String UPDATED_EXPERIENCE = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMBER_OF_VOLUNTEERS = 1;
    private static final Integer UPDATED_NUMBER_OF_VOLUNTEERS = 2;

    @Autowired
    private DonationRequestRepository donationRequestRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDonationRequestMockMvc;

    private DonationRequest donationRequest;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DonationRequestResource donationRequestResource = new DonationRequestResource(donationRequestRepository);
        this.restDonationRequestMockMvc = MockMvcBuilders.standaloneSetup(donationRequestResource)
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
    public static DonationRequest createEntity(EntityManager em) {
        DonationRequest donationRequest = new DonationRequest()
            .type(DEFAULT_TYPE)
            .initialDate(DEFAULT_INITIAL_DATE)
            .expireDate(DEFAULT_EXPIRE_DATE)
            .condition(DEFAULT_CONDITION)
            .description(DEFAULT_DESCRIPTION)
            .experience(DEFAULT_EXPERIENCE)
            .numberOfVolunteers(DEFAULT_NUMBER_OF_VOLUNTEERS);
        return donationRequest;
    }

    @Before
    public void initTest() {
        donationRequest = createEntity(em);
    }

    @Test
    @Transactional
    public void createDonationRequest() throws Exception {
        int databaseSizeBeforeCreate = donationRequestRepository.findAll().size();

        // Create the DonationRequest
        restDonationRequestMockMvc.perform(post("/api/donation-requests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(donationRequest)))
            .andExpect(status().isCreated());

        // Validate the DonationRequest in the database
        List<DonationRequest> donationRequestList = donationRequestRepository.findAll();
        assertThat(donationRequestList).hasSize(databaseSizeBeforeCreate + 1);
        DonationRequest testDonationRequest = donationRequestList.get(donationRequestList.size() - 1);
        assertThat(testDonationRequest.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testDonationRequest.getInitialDate()).isEqualTo(DEFAULT_INITIAL_DATE);
        assertThat(testDonationRequest.getExpireDate()).isEqualTo(DEFAULT_EXPIRE_DATE);
        assertThat(testDonationRequest.getCondition()).isEqualTo(DEFAULT_CONDITION);
        assertThat(testDonationRequest.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testDonationRequest.getExperience()).isEqualTo(DEFAULT_EXPERIENCE);
        assertThat(testDonationRequest.getNumberOfVolunteers()).isEqualTo(DEFAULT_NUMBER_OF_VOLUNTEERS);
    }

    @Test
    @Transactional
    public void createDonationRequestWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = donationRequestRepository.findAll().size();

        // Create the DonationRequest with an existing ID
        donationRequest.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDonationRequestMockMvc.perform(post("/api/donation-requests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(donationRequest)))
            .andExpect(status().isBadRequest());

        // Validate the DonationRequest in the database
        List<DonationRequest> donationRequestList = donationRequestRepository.findAll();
        assertThat(donationRequestList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDonationRequests() throws Exception {
        // Initialize the database
        donationRequestRepository.saveAndFlush(donationRequest);

        // Get all the donationRequestList
        restDonationRequestMockMvc.perform(get("/api/donation-requests?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(donationRequest.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].initialDate").value(hasItem(DEFAULT_INITIAL_DATE.toString())))
            .andExpect(jsonPath("$.[*].expireDate").value(hasItem(DEFAULT_EXPIRE_DATE.toString())))
            .andExpect(jsonPath("$.[*].condition").value(hasItem(DEFAULT_CONDITION.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].experience").value(hasItem(DEFAULT_EXPERIENCE.toString())))
            .andExpect(jsonPath("$.[*].numberOfVolunteers").value(hasItem(DEFAULT_NUMBER_OF_VOLUNTEERS)));
    }
    
    @Test
    @Transactional
    public void getDonationRequest() throws Exception {
        // Initialize the database
        donationRequestRepository.saveAndFlush(donationRequest);

        // Get the donationRequest
        restDonationRequestMockMvc.perform(get("/api/donation-requests/{id}", donationRequest.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(donationRequest.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.initialDate").value(DEFAULT_INITIAL_DATE.toString()))
            .andExpect(jsonPath("$.expireDate").value(DEFAULT_EXPIRE_DATE.toString()))
            .andExpect(jsonPath("$.condition").value(DEFAULT_CONDITION.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.experience").value(DEFAULT_EXPERIENCE.toString()))
            .andExpect(jsonPath("$.numberOfVolunteers").value(DEFAULT_NUMBER_OF_VOLUNTEERS));
    }

    @Test
    @Transactional
    public void getNonExistingDonationRequest() throws Exception {
        // Get the donationRequest
        restDonationRequestMockMvc.perform(get("/api/donation-requests/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDonationRequest() throws Exception {
        // Initialize the database
        donationRequestRepository.saveAndFlush(donationRequest);

        int databaseSizeBeforeUpdate = donationRequestRepository.findAll().size();

        // Update the donationRequest
        DonationRequest updatedDonationRequest = donationRequestRepository.findById(donationRequest.getId()).get();
        // Disconnect from session so that the updates on updatedDonationRequest are not directly saved in db
        em.detach(updatedDonationRequest);
        updatedDonationRequest
            .type(UPDATED_TYPE)
            .initialDate(UPDATED_INITIAL_DATE)
            .expireDate(UPDATED_EXPIRE_DATE)
            .condition(UPDATED_CONDITION)
            .description(UPDATED_DESCRIPTION)
            .experience(UPDATED_EXPERIENCE)
            .numberOfVolunteers(UPDATED_NUMBER_OF_VOLUNTEERS);

        restDonationRequestMockMvc.perform(put("/api/donation-requests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDonationRequest)))
            .andExpect(status().isOk());

        // Validate the DonationRequest in the database
        List<DonationRequest> donationRequestList = donationRequestRepository.findAll();
        assertThat(donationRequestList).hasSize(databaseSizeBeforeUpdate);
        DonationRequest testDonationRequest = donationRequestList.get(donationRequestList.size() - 1);
        assertThat(testDonationRequest.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testDonationRequest.getInitialDate()).isEqualTo(UPDATED_INITIAL_DATE);
        assertThat(testDonationRequest.getExpireDate()).isEqualTo(UPDATED_EXPIRE_DATE);
        assertThat(testDonationRequest.getCondition()).isEqualTo(UPDATED_CONDITION);
        assertThat(testDonationRequest.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testDonationRequest.getExperience()).isEqualTo(UPDATED_EXPERIENCE);
        assertThat(testDonationRequest.getNumberOfVolunteers()).isEqualTo(UPDATED_NUMBER_OF_VOLUNTEERS);
    }

    @Test
    @Transactional
    public void updateNonExistingDonationRequest() throws Exception {
        int databaseSizeBeforeUpdate = donationRequestRepository.findAll().size();

        // Create the DonationRequest

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDonationRequestMockMvc.perform(put("/api/donation-requests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(donationRequest)))
            .andExpect(status().isBadRequest());

        // Validate the DonationRequest in the database
        List<DonationRequest> donationRequestList = donationRequestRepository.findAll();
        assertThat(donationRequestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDonationRequest() throws Exception {
        // Initialize the database
        donationRequestRepository.saveAndFlush(donationRequest);

        int databaseSizeBeforeDelete = donationRequestRepository.findAll().size();

        // Get the donationRequest
        restDonationRequestMockMvc.perform(delete("/api/donation-requests/{id}", donationRequest.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DonationRequest> donationRequestList = donationRequestRepository.findAll();
        assertThat(donationRequestList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DonationRequest.class);
        DonationRequest donationRequest1 = new DonationRequest();
        donationRequest1.setId(1L);
        DonationRequest donationRequest2 = new DonationRequest();
        donationRequest2.setId(donationRequest1.getId());
        assertThat(donationRequest1).isEqualTo(donationRequest2);
        donationRequest2.setId(2L);
        assertThat(donationRequest1).isNotEqualTo(donationRequest2);
        donationRequest1.setId(null);
        assertThat(donationRequest1).isNotEqualTo(donationRequest2);
    }
}
