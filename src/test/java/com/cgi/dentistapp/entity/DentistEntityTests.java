package com.cgi.dentistapp.entity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DentistEntityTests {
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void constructorTest() {
        String name = "Heli Adamson";
        DentistEntity dentist = new DentistEntity(name);
        Assert.assertEquals(name, dentist.getName());
    }

    @Test
    public void getterAndSetterTest() {
        String name = "Heli Adamson";
        String name2 = "Sander Tamm";
        Long id = 15L;
        DentistEntity dentist = new DentistEntity(name);
        dentist.setId(id);
        dentist.setName(name2);
        Assert.assertEquals(name2, dentist.getName());
        Assert.assertEquals(id, dentist.getId());
    }

    @Test(expected = Test.None.class /* no exception expected */)
    public void successfulInsertionTest() {
        String name = "Heli Adamson";
        DentistEntity dentist = new DentistEntity(name);

        entityManager.persist(dentist);
        entityManager.flush();
    }

    @Test(expected = ConstraintViolationException.class)
    public void emptyNameInsertionTest() {
        String name = "";
        DentistEntity dentist = new DentistEntity(name);

        entityManager.persist(dentist);
        entityManager.flush();
    }

    @Test(expected = ConstraintViolationException.class)
    public void nullNameInsertionTest() {
        String name = null;
        DentistEntity dentist = new DentistEntity(name);

        entityManager.persist(dentist);
        entityManager.flush();
    }

    @Test(expected = ConstraintViolationException.class)
    public void trimmableNameInsertionTest() {
        String name = "     \t\n";
        DentistEntity dentist = new DentistEntity(name);

        entityManager.persist(dentist);
        entityManager.flush();
    }

    @Test(expected = ConstraintViolationException.class)
    public void longNameInsertionTest() {
        String name = "Namelongerthanfiftycharacters Wowwhatalongnamethisis";
        DentistEntity dentist = new DentistEntity(name);

        entityManager.persist(dentist);
        entityManager.flush();
    }
}
