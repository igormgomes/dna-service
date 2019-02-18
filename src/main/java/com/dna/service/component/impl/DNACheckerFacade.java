package com.dna.service.component.impl;

import com.dna.service.component.DNAChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DNACheckerFacade {

    private final List<DNAChecker> dnaCheckers;

    @Autowired
    public DNACheckerFacade(List<DNAChecker> dnaCheckers) {
        this.dnaCheckers = dnaCheckers;
    }

    public boolean isSimian(String[] dna) {
        char[][] chars = new char[dna.length][dna.length];
        for (int x = 0; x < dna.length; x++) {
            chars[x] = dna[x].toCharArray();
        }

        return dnaCheckers.stream()
                .anyMatch(d -> d.isAnySimian(chars));
    }
}
