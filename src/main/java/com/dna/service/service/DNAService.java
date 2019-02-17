package com.dna.service.service;

import com.dna.service.document.DNAAgregate;
import com.dna.service.document.DNADocument;

public interface DNAService {

    void save(DNADocument dnaDocument);

    DNAAgregate find();
}
