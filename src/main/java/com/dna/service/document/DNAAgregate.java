package com.dna.service.document;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class DNAAgregate {

    private int countHuman;
    private int countSimian;

    public BigDecimal getRatio() {
        if(countHuman == 0 || countSimian == 0)
            return BigDecimal.ZERO;
        return BigDecimal.valueOf(countSimian).divide(BigDecimal.valueOf(countHuman));
    }
}