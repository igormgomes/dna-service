package com.dna.service.component.impl;

import com.dna.service.component.DNAChecker;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class DNACheckerVertical implements DNAChecker {

    private final SimianPattern simianPattern;

    @Autowired
    public DNACheckerVertical(SimianPattern simianPattern) {
        this.simianPattern = simianPattern;
    }

    @Override
    public boolean isAnySimian(char[][] dnas) {
        List<String> vertical = Lists.newArrayList();

        StringBuilder builder = new StringBuilder();
        for (int x = 0; x < dnas.length; x++) {
            for (int y = 0; y < dnas.length; y++) {
                builder.append(dnas[y][x]);
            }
            vertical.add(builder.toString());
        }

        return vertical.stream()
                .anyMatch(this.simianPattern::isSimio);
    }
}
