package com.github.Cubolink.finalreality.model.weapon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BowTest {
    private Bow bowTest;

    @BeforeEach
    void setUp(){
        bowTest = new Bow("Arco", 10, 5);
    }

    @Test
    void testEquals() {
        assertEquals(bowTest, bowTest);

        var notBow = new GenericWeapon("piedra", 3, 1);
        var ballesta = new Bow("Bayesta", 15, 10);
        var bow_altered = new Bow(bowTest.getName(), bowTest.getPhysicalDamage()+10, bowTest.getWeight());
        var samebow = new Bow(bowTest.getName(), bowTest.getPhysicalDamage(), bowTest.getWeight());

        assertNotEquals(notBow, bowTest);
        assertNotEquals(ballesta, bowTest);
        assertNotEquals(bow_altered, bowTest);
        assertEquals(samebow, bowTest);


    }

    @Test
    void testHashCode() {
        assertEquals(bowTest.hashCode(), bowTest.hashCode());

        var notBow = new GenericWeapon(bowTest.getName(), bowTest.getPhysicalDamage(), bowTest.getWeight());
        var ballesta = new Bow("Bayesta", 15, 10);
        var bow_altered = new Bow(bowTest.getName(), bowTest.getPhysicalDamage()+10, bowTest.getWeight());
        var samebow = new Bow(bowTest.getName(), bowTest.getPhysicalDamage(), bowTest.getWeight());

        assertNotEquals(notBow.hashCode(), bowTest.hashCode());
        assertNotEquals(ballesta.hashCode(), bowTest.hashCode());
        assertNotEquals(bow_altered.hashCode(), bowTest.hashCode());
        assertEquals(samebow.hashCode(), bowTest.hashCode());

    }
}