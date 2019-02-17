package com.dna.service.component.impl;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class SimianPattern {

    private final String SIMIO_PATTERN = "[A]{4}|[C]{4}|[G]{4}|[T]{4}";

    public boolean isSimio(String dna){
        Pattern pattern = Pattern.compile(SIMIO_PATTERN);
        return pattern.matcher(dna).find();
    }
}