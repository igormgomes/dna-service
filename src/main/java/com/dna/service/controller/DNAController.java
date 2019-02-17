package com.dna.service.controller;

import com.dna.service.document.DNAAgregate;
import com.dna.service.document.DNADocument;
import com.dna.service.service.DNAService;
import com.swagger.dna.service.api.DnaApi;
import com.swagger.dna.service.model.DNARequest;
import com.swagger.dna.service.model.StatsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class DNAController implements DnaApi {

    private final DNAService dnaService;

    @Autowired
    public DNAController(DNAService dnaService) {
        this.dnaService = dnaService;
    }

    @Override
    public ResponseEntity<Void> save(@Valid @RequestBody DNARequest dnaRequest) {
        DNADocument dnaDocument = DNADocument.builder()
                .dna(dnaRequest.getDna())
                .build();

        this.dnaService.save(dnaDocument);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<StatsResponse> find() {
        DNAAgregate dnaAgregate = this.dnaService.find();
        StatsResponse statsResponse = new StatsResponse()
                .countHumanDna(dnaAgregate.getCountHuman())
                .countMutantDna(dnaAgregate.getCountSimian())
                .ratio(dnaAgregate.getRatio());
        return ResponseEntity.ok(statsResponse);
    }
}