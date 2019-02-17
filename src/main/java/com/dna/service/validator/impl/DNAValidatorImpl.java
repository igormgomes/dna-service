package com.dna.service.validator.impl;

import com.dna.service.validator.DNAValidator;
import org.springframework.stereotype.Component;

import static org.springframework.util.StringUtils.isEmpty;

@Component
class DNAValidatorImpl implements DNAValidator {

    @Override
    public void validate(String[] dnas) {
        int lenght = dnas.length;
        for(String dna :dnas) {
            if(isEmpty(dna) || dna.length() != lenght) {
                throw new IllegalArgumentException("Invalid length DNA");
            }
        }
    }
}