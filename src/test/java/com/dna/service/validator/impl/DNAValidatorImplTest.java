package com.dna.service.validator.impl;

import com.dna.service.validator.DNAValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("DNA validator test")
public class DNAValidatorImplTest {

    private DNAValidator dnaValidator;

    @BeforeEach
    public void before(){
        this.dnaValidator = new DNAValidatorImpl();
    }

    @Test
    public void shouldThrowIllegalArgumentException(){
        String[] dna = {"AAAAA", "BBBBB"};

        assertThrows(IllegalArgumentException.class, () -> this.dnaValidator.validate(dna));
    }

    @Test
    public void shouldValidDNA(){
        String[] dna = {"AAAA", "BBBB", "CCCC", "DDDD"};

        this.dnaValidator.validate(dna);
    }
}