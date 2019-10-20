package com.cgi.dentistapp.service;

import com.cgi.dentistapp.config.DentistConfig;
import com.cgi.dentistapp.entity.DentistEntity;
import com.cgi.dentistapp.repository.DentistRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DentistServiceTests {
    @TestConfiguration
    static class DentistServiceTestContextConfiguration {

        @Bean
        public DentistService dentistService() {
            return new DentistService();
        }
    }

    @Autowired
    private DentistService dentistService;

    @MockBean
    private DentistRepository dentistRepository;
    @MockBean
    private DentistConfig dentistConfig;

    @Before
    public void setUp() {
        DentistEntity dentist = new DentistEntity("Heli Adamson");

        Mockito.when(dentistRepository.findOne((long) 1)).thenReturn(dentist);
    }

    @Test
    public void whenValidName_thenEmployeeShouldBeFound() {
        String name = "Heli Adamson";
        DentistEntity found = dentistService.getDentistById((long) 1);

        Assert.assertEquals(found.getName(), name);
    }
}
