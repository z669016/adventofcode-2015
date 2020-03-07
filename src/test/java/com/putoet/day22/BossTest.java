package com.putoet.day22;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class BossTest {
    public static final int HIT_POINTS = 11;
    public static final int DAMAGE = 5;
    private Boss boss;

    @BeforeEach
    void setup() {
        boss = new Boss(HIT_POINTS, DAMAGE);
    }

    @Test
    void attack() {
        final Combattant opponent = mock(Combattant.class);
        final Combat combat = mock(Combat.class);
        boss.attack(combat, opponent);

        verify(opponent).defend(DAMAGE);
    }

    @Test
    void defend() {
        boss.defend(DAMAGE);
        assertFalse(boss.defeated());
        boss.defend(DAMAGE);
        assertFalse(boss.defeated());
        boss.defend(DAMAGE);
        assertTrue(boss.defeated());
    }
}