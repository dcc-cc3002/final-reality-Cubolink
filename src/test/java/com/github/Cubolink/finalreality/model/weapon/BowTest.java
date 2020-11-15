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

        var sword = new Sword("Espada de madera", 3, 5);
        var ballesta = new Bow("Bayesta", 15, 10);
        var bow_altered = new Bow(bowTest.getName(), bowTest.getPhysicalDamage()+10, bowTest.getWeight());
        var samebow = new Bow(bowTest.getName(), bowTest.getPhysicalDamage(), bowTest.getWeight());

        assertNotEquals(bowTest, sword);
        assertNotEquals(bowTest, ballesta);
        assertNotEquals(bowTest, bow_altered);
        assertEquals(bowTest, samebow);


    }

    @Test
    void testHashCode() {
        assertEquals(bowTest.hashCode(), bowTest.hashCode());

        var sword = new Sword("Espada de madera", 3, 5);
        var ballesta = new Bow("Bayesta", 15, 10);
        var bow_altered = new Bow(bowTest.getName(), bowTest.getPhysicalDamage()+10, bowTest.getWeight());
        var samebow = new Bow(bowTest.getName(), bowTest.getPhysicalDamage(), bowTest.getWeight());

        assertNotEquals(bowTest.hashCode(), sword.hashCode());
        assertNotEquals(bowTest.hashCode(), ballesta.hashCode());
        assertNotEquals(bowTest.hashCode(), bow_altered.hashCode());
        assertEquals(bowTest.hashCode(), samebow.hashCode());

    }
}