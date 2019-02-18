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
@DisplayName("DNA checker horizontal test")
public class DNACheckerHorizontalTest {

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private SimianPattern simianPattern;

    private DNAChecker dnaChecker;

    @BeforeEach
    public void before() {
        this.dnaChecker = new DNACheckerHorizontal(this.simianPattern);
    }

    @Test
    public void shouldNotFindSimian(){
        String[] dna = {
                "AGCG",
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
                "AAAA",
                "BAFT",
                "BTAT",
                "BGAC"
        };
        char[][] chars = CharDataBuilder.builder()
                .dna(dna)
                .build();

        boolean simian = this.dnaChecker.isAnySimian(chars);
        assertTrue(simian);
    }
}
