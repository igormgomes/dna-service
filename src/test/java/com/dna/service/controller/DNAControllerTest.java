package com.dna.service.controller;

import com.dna.service.document.DNAAgregate;
import com.dna.service.service.DNAService;
import com.swagger.dna.service.model.DNARequest;
import com.swagger.dna.service.model.StatsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("DNA Controller test")
public class DNAControllerTest {

    @Mock
    private DNAService dnaService;

    private DNAController dnaController;

    @BeforeEach
    public void before(){
        this.dnaController = new DNAController(this.dnaService);
    }

    @Test
    public void shouldTestSave(){
        doNothing()
                .when(this.dnaService).save(any());

        DNARequest dnaRequest = new DNARequest()
                .addDnaItem("AAAA");
        ResponseEntity<Void> responseEntity = this.dnaController.save(dnaRequest);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void shouldTestFind(){
        when(this.dnaService.find())
                .thenReturn(DNAAgregate.builder()
                    .countHuman(100)
                    .countSimian(40)
                    .build());

        ResponseEntity<StatsResponse> responseEntity = this.dnaController.find();

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertNotNull(responseEntity.getBody());
        assertThat(responseEntity.getBody().getCountHumanDna(), is(100));
        assertThat(responseEntity.getBody().getCountMutantDna(), is(40));
        assertThat(responseEntity.getBody().getRatio(), is(BigDecimal.valueOf(0.4)));
    }
}