package com.dna.service.repository;

import com.dna.service.document.DNADocument;
import com.dna.service.document.DNAType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DNARepository extends MongoRepository<DNADocument, String> {

    Optional<DNADocument> findByDna(String[] dna);

    int countByDnaType(DNAType dnaType);

}