package com.dna.service.service.impl;

import com.dna.service.component.impl.DNACheckerFacade;
import com.dna.service.document.DNAAgregate;
import com.dna.service.document.DNADocument;
import com.dna.service.document.DNAType;
import com.dna.service.exception.InvalidDNATypeException;
import com.dna.service.repository.DNARepository;
import com.dna.service.service.DNAService;
import com.dna.service.validator.DNAValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class DNAServiceImpl implements DNAService {

    private final DNAValidator dnaValidator;
    private final DNARepository dnaRepository;
    private final DNACheckerFacade dnaCheckerFacade;

    @Autowired
    public DNAServiceImpl(DNAValidator dnaValidator, DNARepository dnaRepository, DNACheckerFacade dnaCheckerFacade) {
        this.dnaValidator = dnaValidator;
        this.dnaRepository = dnaRepository;
        this.dnaCheckerFacade = dnaCheckerFacade;
    }

    @Override
    public void save(DNADocument dnaDocument) {
        this.dnaValidator.validate(dnaDocument.getDna());

        boolean simian = this.dnaCheckerFacade.isSimian(dnaDocument.getDna());
        dnaDocument.setDNAType(simian);

        Optional<DNADocument> optionalDNADocument = this.dnaRepository.findByDna(dnaDocument.getDna());
        if (!optionalDNADocument.isPresent()) {
            this.dnaRepository.save(dnaDocument);
        }

        if(dnaDocument.isHuman()){
            throw new InvalidDNATypeException("Invalid DNA type " + dnaDocument.getDnaType().name());
        }
    }

    @Override
    public DNAAgregate find() {
        int countHuman = this.dnaRepository.countByDnaType(DNAType.HUMAN);
        int countSimian = this.dnaRepository.countByDnaType(DNAType.SIMIAN);

        return DNAAgregate.builder()
                .countHuman(countHuman)
                .countSimian(countSimian)
                .build();
    }
}
