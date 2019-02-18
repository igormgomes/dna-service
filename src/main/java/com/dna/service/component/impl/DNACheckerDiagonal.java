package com.dna.service.component.impl;

import com.dna.service.component.DNAChecker;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class DNACheckerDiagonal implements DNAChecker {

    private final SimianPattern simianPattern;

    @Autowired
    public DNACheckerDiagonal(SimianPattern simianPattern) {
        this.simianPattern = simianPattern;
    }

    @Override
    public boolean isAnySimian(char[][] dnas) {
        List<String> diagonal = Lists.newArrayList();
        StringBuilder builder = new StringBuilder();
        StringBuilder builderInverse = new StringBuilder();

        int length = dnas.length;
        for (int x = 0; x < length; x++) {
            int aux = x;
            for (int y = 0; aux < length; y++) {
                builder.append(dnas[y][aux]);
                builderInverse.append(dnas[aux][y]);
                aux++;
            }
            diagonal.add(builder.toString());
            diagonal.add(builderInverse.toString());
        }
        return diagonal.stream()
                .anyMatch(this.simianPattern::isSimio);
    }
}