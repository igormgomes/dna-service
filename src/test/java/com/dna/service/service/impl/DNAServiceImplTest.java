package com.dna.service.service.impl;

import com.dna.service.component.impl.DNACheckerFacade;
import com.dna.service.document.DNAAgregate;
import com.dna.service.document.DNADocument;
import com.dna.service.exception.InvalidDNATypeException;
import com.dna.service.repository.DNARepository;
import com.dna.service.service.DNAService;
import com.dna.service.validator.DNAValidator;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("DNA Service test")
public class DNAServiceImplTest {

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private DNAValidator dnaValidator;
    @Mock
    private DNARepository dnaRepository;
    @Mock
    private DNACheckerFacade dnaCheckerFacade;

    private DNAService dnaService;

    @BeforeEach
    public void before () {
        this.dnaService = new DNAServiceImpl(this.dnaValidator, this.dnaRepository, this.dnaCheckerFacade);
    }

    @Test
    public void shouldNotSaveDNA(){
        when(this.dnaCheckerFacade.isSimian(any()))
                .thenReturn(true);
        when(this.dnaRepository.findByDna(any()))
                .thenReturn(Optional.of(DNADocument.builder().build()));

        DNADocument dnaDocument = DNADocument.builder()
                .dna(Lists.newArrayList("CCCCTA", "BBBB"))
                .build();
        this.dnaService.save(dnaDocument);

        verify(this.dnaRepository, never()).save(any());
    }

    @Test
    public void shouldNotSaveDNAAndThrowInvalidDNATypeException(){
        when(this.dnaCheckerFacade.isSimian(any()))
                .thenReturn(false);
        when(this.dnaRepository.findByDna(any()))
                .thenReturn(Optional.of(DNADocument.builder().build()));

        DNADocument dnaDocument = DNADocument.builder()
                .dna(Lists.newArrayList("CCCCTA", "BBBB"))
                .build();
        assertThrows(InvalidDNATypeException.class, () -> this.dnaService.save(dnaDocument));

        verify(this.dnaRepository, never()).save(any());
    }

    @Test
    public void shouldSaveDNA(){
        when(this.dnaCheckerFacade.isSimian(any()))
                .thenReturn(true);
        when(this.dnaRepository.findByDna(any()))
                .thenReturn(Optional.empty());

        DNADocument dnaDocument = DNADocument.builder()
                .dna(Lists.newArrayList("CCCCTA", "BBBB"))
                .build();
        this.dnaService.save(dnaDocument);

        verify(this.dnaRepository, atLeastOnce()).save(any());
    }

    @Test
    public void shouldSaveDNAAndThrowInvalidDNATypeException(){
        when(this.dnaCheckerFacade.isSimian(any()))
                .thenReturn(false);
        when(this.dnaRepository.findByDna(any()))
                .thenReturn(Optional.empty());

        DNADocument dnaDocument = DNADocument.builder()
                .dna(Lists.newArrayList("CCCCTA", "BBBB"))
                .build();
        assertThrows(InvalidDNATypeException.class, () -> this.dnaService.save(dnaDocument));

        verify(this.dnaRepository, atLeastOnce()).save(any());
    }

    @Test
    public void shouldReturnAgregateWithCountHumanZero(){
        when(this.dnaRepository.countByDnaType(any()))
                .thenReturn(0)
                .thenReturn(12);

        DNAAgregate dnaAgregate = this.dnaService.find();

        assertThat(dnaAgregate.getCountHuman(), is(0));
        assertThat(dnaAgregate.getCountSimian(), is(12));
        assertThat(dnaAgregate.getRatio(), is(BigDecimal.ZERO));
    }

    @Test
    public void shouldReturnAgregateWithCountSimianZero(){
        when(this.dnaRepository.countByDnaType(any()))
                .thenReturn(12)
                .thenReturn(0);

        DNAAgregate dnaAgregate = this.dnaService.find();

        assertThat(dnaAgregate.getCountHuman(), is(12));
        assertThat(dnaAgregate.getCountSimian(), is(0));
        assertThat(dnaAgregate.getRatio(), is(BigDecimal.ZERO));
    }

    @Test
    public void shouldReturnAgregateWithValidValues(){
        when(this.dnaRepository.countByDnaType(any()))
                .thenReturn(100)
                .thenReturn(40);

        DNAAgregate dnaAgregate = this.dnaService.find();

        assertThat(dnaAgregate.getCountHuman(), is(100));
        assertThat(dnaAgregate.getCountSimian(), is(40));
        assertThat(dnaAgregate.getRatio(), is(BigDecimal.valueOf(0.4)));
    }
}
