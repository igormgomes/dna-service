package com.dna.service.document;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Builder
@Getter
@Document(value = "DNA")
@TypeAlias(value = "DNA")
public class DNADocument {

    private String[] dna;
    private DNAType dnaType;

    public boolean isHuman() {
        return DNAType.HUMAN.equals(this.dnaType);
    }

    public void setDNAType (boolean simian){
        this.dnaType = simian ? DNAType.SIMIAN : DNAType.HUMAN;
    }

    public static class DNADocumentBuilder {

        public DNADocumentBuilder dna (List<String> dna){
            String[] dnaSize = new String[dna.size()];
            this.dna = dna.toArray(dnaSize);
            return this;
        }
    }
}
