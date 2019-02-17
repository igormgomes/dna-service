package com.dna.service.controller;

import com.swagger.dna.service.api.DnaApi;
import com.swagger.dna.service.model.DNARequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class DNAController implements DnaApi {

    @Override
    public ResponseEntity<Void> save(@Valid @RequestBody DNARequest dnaRequest) {
        return ResponseEntity.ok().build();
    }
}
