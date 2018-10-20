package org.huejacksonfoundation.hopejustfound.web.rest;

import org.huejacksonfoundation.hopejustfound.HopeJustFoundApp;

import org.huejacksonfoundation.hopejustfound.domain.Unskilled;
import org.huejacksonfoundation.hopejustfound.repository.UnskilledRepository;
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
 * Test class for the UnskilledResource REST controller.
 *
 * @see UnskilledResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HopeJustFoundApp.class)
public class UnskilledResourceIntTest {

    private static final String DEFAULT_CLIMATE = "AAAAAAAAAA";
    private static final String UPDATED_CLIMATE = "BBBBBBBBBB";

    private static final String DEFAULT_INTENSITY = "AAAAAAAAAA";
    private static final String UPDATED_INTENSITY = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMBER = 1;
    private static final Integer UPDATED_NUMBER = 2;

    @Autowired
    private UnskilledRepository unskilledRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUnskilledMockMvc;

    private Unskilled unskilled;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UnskilledResource unskilledResource = new UnskilledResource(unskilledRepository);
        this.restUnskilledMockMvc = MockMvcBuilders.standaloneSetup(unskilledResource)
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
    public static Unskilled createEntity(EntityManager em) {
        Unskilled unskilled = new Unskilled()
            .climate(DEFAULT_CLIMATE)
            .intensity(DEFAULT_INTENSITY)
            .number(DEFAULT_NUMBER);
        return unskilled;
    }

    @Before
    public void initTest() {
        unskilled = createEntity(em);
    }

    @Test
    @Transactional
    public void createUnskilled() throws Exception {
        int databaseSizeBeforeCreate = unskilledRepository.findAll().size();

        // Create the Unskilled
        restUnskilledMockMvc.perform(post("/api/unskilleds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(unskilled)))
            .andExpect(status().isCreated());

        // Validate the Unskilled in the database
        List<Unskilled> unskilledList = unskilledRepository.findAll();
        assertThat(unskilledList).hasSize(databaseSizeBeforeCreate + 1);
        Unskilled testUnskilled = unskilledList.get(unskilledList.size() - 1);
        assertThat(testUnskilled.getClimate()).isEqualTo(DEFAULT_CLIMATE);
        assertThat(testUnskilled.getIntensity()).isEqualTo(DEFAULT_INTENSITY);
        assertThat(testUnskilled.getNumber()).isEqualTo(DEFAULT_NUMBER);
    }

    @Test
    @Transactional
    public void createUnskilledWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = unskilledRepository.findAll().size();

        // Create the Unskilled with an existing ID
        unskilled.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUnskilledMockMvc.perform(post("/api/unskilleds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(unskilled)))
            .andExpect(status().isBadRequest());

        // Validate the Unskilled in the database
        List<Unskilled> unskilledList = unskilledRepository.findAll();
        assertThat(unskilledList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllUnskilleds() throws Exception {
        // Initialize the database
        unskilledRepository.saveAndFlush(unskilled);

        // Get all the unskilledList
        restUnskilledMockMvc.perform(get("/api/unskilleds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(unskilled.getId().intValue())))
            .andExpect(jsonPath("$.[*].climate").value(hasItem(DEFAULT_CLIMATE.toString())))
            .andExpect(jsonPath("$.[*].intensity").value(hasItem(DEFAULT_INTENSITY.toString())))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)));
    }
    
    @Test
    @Transactional
    public void getUnskilled() throws Exception {
        // Initialize the database
        unskilledRepository.saveAndFlush(unskilled);

        // Get the unskilled
        restUnskilledMockMvc.perform(get("/api/unskilleds/{id}", unskilled.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(unskilled.getId().intValue()))
            .andExpect(jsonPath("$.climate").value(DEFAULT_CLIMATE.toString()))
            .andExpect(jsonPath("$.intensity").value(DEFAULT_INTENSITY.toString()))
            .andExpect(jsonPath("$.number").value(DEFAULT_NUMBER));
    }

    @Test
    @Transactional
    public void getNonExistingUnskilled() throws Exception {
        // Get the unskilled
        restUnskilledMockMvc.perform(get("/api/unskilleds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUnskilled() throws Exception {
        // Initialize the database
        unskilledRepository.saveAndFlush(unskilled);

        int databaseSizeBeforeUpdate = unskilledRepository.findAll().size();

        // Update the unskilled
        Unskilled updatedUnskilled = unskilledRepository.findById(unskilled.getId()).get();
        // Disconnect from session so that the updates on updatedUnskilled are not directly saved in db
        em.detach(updatedUnskilled);
        updatedUnskilled
            .climate(UPDATED_CLIMATE)
            .intensity(UPDATED_INTENSITY)
            .number(UPDATED_NUMBER);

        restUnskilledMockMvc.perform(put("/api/unskilleds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedUnskilled)))
            .andExpect(status().isOk());

        // Validate the Unskilled in the database
        List<Unskilled> unskilledList = unskilledRepository.findAll();
        assertThat(unskilledList).hasSize(databaseSizeBeforeUpdate);
        Unskilled testUnskilled = unskilledList.get(unskilledList.size() - 1);
        assertThat(testUnskilled.getClimate()).isEqualTo(UPDATED_CLIMATE);
        assertThat(testUnskilled.getIntensity()).isEqualTo(UPDATED_INTENSITY);
        assertThat(testUnskilled.getNumber()).isEqualTo(UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void updateNonExistingUnskilled() throws Exception {
        int databaseSizeBeforeUpdate = unskilledRepository.findAll().size();

        // Create the Unskilled

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUnskilledMockMvc.perform(put("/api/unskilleds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(unskilled)))
            .andExpect(status().isBadRequest());

        // Validate the Unskilled in the database
        List<Unskilled> unskilledList = unskilledRepository.findAll();
        assertThat(unskilledList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUnskilled() throws Exception {
        // Initialize the database
        unskilledRepository.saveAndFlush(unskilled);

        int databaseSizeBeforeDelete = unskilledRepository.findAll().size();

        // Get the unskilled
        restUnskilledMockMvc.perform(delete("/api/unskilleds/{id}", unskilled.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Unskilled> unskilledList = unskilledRepository.findAll();
        assertThat(unskilledList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Unskilled.class);
        Unskilled unskilled1 = new Unskilled();
        unskilled1.setId(1L);
        Unskilled unskilled2 = new Unskilled();
        unskilled2.setId(unskilled1.getId());
        assertThat(unskilled1).isEqualTo(unskilled2);
        unskilled2.setId(2L);
        assertThat(unskilled1).isNotEqualTo(unskilled2);
        unskilled1.setId(null);
        assertThat(unskilled1).isNotEqualTo(unskilled2);
    }
}
