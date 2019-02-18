package com.dna.service.component.impl;

import com.dna.service.component.DNAChecker;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class DNACheckerHorizontal implements DNAChecker {

    private final SimianPattern simianPattern;

    @Autowired
    public DNACheckerHorizontal(SimianPattern simianPattern) {
        this.simianPattern = simianPattern;
    }

    @Override
    public boolean isAnySimian(char[][] dnas) {
        List<String> horizontal = Lists.newArrayList();

        for (char[] dna : dnas) {
            horizontal.add(String.valueOf(dna));
        }

        return horizontal.stream()
                .anyMatch(this.simianPattern::isSimio);
    }
}
