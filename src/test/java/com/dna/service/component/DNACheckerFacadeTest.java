package com.dna.service.component;

import com.dna.service.component.impl.DNACheckerHorizontal;
import com.dna.service.component.impl.DNACheckerVertical;
import com.dna.service.component.impl.SimianPattern;
import com.dna.service.document.DNADocument;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
@DisplayName("DNA checker facade test")
public class DNACheckerFacadeTest {

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private SimianPattern simianPattern;
    private DNACheckerHorizontal dnaCheckerHorizontal;
    private DNACheckerVertical dnaCheckerVertical;

    private DNACheckerFacade dnaCheckerFacade;

    @BeforeEach
    public void before(){
        this.dnaCheckerHorizontal = new DNACheckerHorizontal(this.simianPattern);
        this.dnaCheckerVertical = new DNACheckerVertical(this.simianPattern);
        this.dnaCheckerFacade = new DNACheckerFacade(Lists.newArrayList(this.dnaCheckerHorizontal, this.dnaCheckerVertical));
    }

    @Test
    public void shouldNotFindSimian(){
        DNADocument dnaDocument = DNADocument.builder()
                .dna(Lists.newArrayList("CCSCTA", "BBBA"))
                .build();
        boolean simian = this.dnaCheckerFacade.isSimian(dnaDocument.getDna());

        assertFalse(simian);
    }

    @Test
    public void shouldFindSimian(){
        DNADocument dnaDocument = DNADocument.builder()
                .dna(Lists.newArrayList("CCCCTA", "BBBB"))
                .build();
        boolean simian = this.dnaCheckerFacade.isSimian(dnaDocument.getDna());

        assertTrue(simian);
    }
}