package org.huejacksonfoundation.hopejustfound.web.rest;

import org.huejacksonfoundation.hopejustfound.HopeJustFoundApp;

import org.huejacksonfoundation.hopejustfound.domain.Skilled;
import org.huejacksonfoundation.hopejustfound.repository.SkilledRepository;
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
 * Test class for the SkilledResource REST controller.
 *
 * @see SkilledResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HopeJustFoundApp.class)
public class SkilledResourceIntTest {

    private static final String DEFAULT_EXPERIENCE = "AAAAAAAAAA";
    private static final String UPDATED_EXPERIENCE = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMBER = 1;
    private static final Integer UPDATED_NUMBER = 2;

    @Autowired
    private SkilledRepository skilledRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSkilledMockMvc;

    private Skilled skilled;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SkilledResource skilledResource = new SkilledResource(skilledRepository);
        this.restSkilledMockMvc = MockMvcBuilders.standaloneSetup(skilledResource)
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
    public static Skilled createEntity(EntityManager em) {
        Skilled skilled = new Skilled()
            .experience(DEFAULT_EXPERIENCE)
            .type(DEFAULT_TYPE)
            .number(DEFAULT_NUMBER);
        return skilled;
    }

    @Before
    public void initTest() {
        skilled = createEntity(em);
    }

    @Test
    @Transactional
    public void createSkilled() throws Exception {
        int databaseSizeBeforeCreate = skilledRepository.findAll().size();

        // Create the Skilled
        restSkilledMockMvc.perform(post("/api/skilleds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(skilled)))
            .andExpect(status().isCreated());

        // Validate the Skilled in the database
        List<Skilled> skilledList = skilledRepository.findAll();
        assertThat(skilledList).hasSize(databaseSizeBeforeCreate + 1);
        Skilled testSkilled = skilledList.get(skilledList.size() - 1);
        assertThat(testSkilled.getExperience()).isEqualTo(DEFAULT_EXPERIENCE);
        assertThat(testSkilled.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testSkilled.getNumber()).isEqualTo(DEFAULT_NUMBER);
    }

    @Test
    @Transactional
    public void createSkilledWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = skilledRepository.findAll().size();

        // Create the Skilled with an existing ID
        skilled.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSkilledMockMvc.perform(post("/api/skilleds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(skilled)))
            .andExpect(status().isBadRequest());

        // Validate the Skilled in the database
        List<Skilled> skilledList = skilledRepository.findAll();
        assertThat(skilledList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSkilleds() throws Exception {
        // Initialize the database
        skilledRepository.saveAndFlush(skilled);

        // Get all the skilledList
        restSkilledMockMvc.perform(get("/api/skilleds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(skilled.getId().intValue())))
            .andExpect(jsonPath("$.[*].experience").value(hasItem(DEFAULT_EXPERIENCE.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)));
    }
    
    @Test
    @Transactional
    public void getSkilled() throws Exception {
        // Initialize the database
        skilledRepository.saveAndFlush(skilled);

        // Get the skilled
        restSkilledMockMvc.perform(get("/api/skilleds/{id}", skilled.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(skilled.getId().intValue()))
            .andExpect(jsonPath("$.experience").value(DEFAULT_EXPERIENCE.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.number").value(DEFAULT_NUMBER));
    }

    @Test
    @Transactional
    public void getNonExistingSkilled() throws Exception {
        // Get the skilled
        restSkilledMockMvc.perform(get("/api/skilleds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSkilled() throws Exception {
        // Initialize the database
        skilledRepository.saveAndFlush(skilled);

        int databaseSizeBeforeUpdate = skilledRepository.findAll().size();

        // Update the skilled
        Skilled updatedSkilled = skilledRepository.findById(skilled.getId()).get();
        // Disconnect from session so that the updates on updatedSkilled are not directly saved in db
        em.detach(updatedSkilled);
        updatedSkilled
            .experience(UPDATED_EXPERIENCE)
            .type(UPDATED_TYPE)
            .number(UPDATED_NUMBER);

        restSkilledMockMvc.perform(put("/api/skilleds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSkilled)))
            .andExpect(status().isOk());

        // Validate the Skilled in the database
        List<Skilled> skilledList = skilledRepository.findAll();
        assertThat(skilledList).hasSize(databaseSizeBeforeUpdate);
        Skilled testSkilled = skilledList.get(skilledList.size() - 1);
        assertThat(testSkilled.getExperience()).isEqualTo(UPDATED_EXPERIENCE);
        assertThat(testSkilled.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testSkilled.getNumber()).isEqualTo(UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void updateNonExistingSkilled() throws Exception {
        int databaseSizeBeforeUpdate = skilledRepository.findAll().size();

        // Create the Skilled

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSkilledMockMvc.perform(put("/api/skilleds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(skilled)))
            .andExpect(status().isBadRequest());

        // Validate the Skilled in the database
        List<Skilled> skilledList = skilledRepository.findAll();
        assertThat(skilledList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSkilled() throws Exception {
        // Initialize the database
        skilledRepository.saveAndFlush(skilled);

        int databaseSizeBeforeDelete = skilledRepository.findAll().size();

        // Get the skilled
        restSkilledMockMvc.perform(delete("/api/skilleds/{id}", skilled.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Skilled> skilledList = skilledRepository.findAll();
        assertThat(skilledList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Skilled.class);
        Skilled skilled1 = new Skilled();
        skilled1.setId(1L);
        Skilled skilled2 = new Skilled();
        skilled2.setId(skilled1.getId());
        assertThat(skilled1).isEqualTo(skilled2);
        skilled2.setId(2L);
        assertThat(skilled1).isNotEqualTo(skilled2);
        skilled1.setId(null);
        assertThat(skilled1).isNotEqualTo(skilled2);
    }
}
