package com.dna.service.component.impl;

import com.dna.service.builder.CharDataBuilder;
import com.dna.service.component.DNAChecker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
@DisplayName("DNA checker vertical test")
public class DNACheckerVerticalTest {

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private SimianPattern simianPattern;

    private DNAChecker dnaChecker;

    @BeforeEach
    public void before() {
        this.dnaChecker = new DNACheckerVertical(this.simianPattern);
    }

    @Test
    public void shouldNotFindSimian(){
        String[] dna = {
                "ATGC",
                "AAFT",
                "ATAT",
                "BGAC"
        };
        char[][] chars = CharDataBuilder.builder()
                .dna(dna)
                .build();

        boolean simian = this.dnaChecker.isAnySimian(chars);
        assertFalse(simian);
    }

    @Test
    public void shouldFindSimian(){
        String[] dna = {
                "ATGC",
                "AAFT",
                "ATAT",
                "AGAA"
        };
        char[][] chars = CharDataBuilder.builder()
                .dna(dna)
                .build();

        boolean simian = this.dnaChecker.isAnySimian(chars);
        assertTrue(simian);
    }
}
