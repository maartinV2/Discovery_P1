package za.ac.nwu.ac.logic.flow.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.logic.flow.CreateAccountTypeFlow;
import za.ac.nwu.ac.translator.AccountTypeTranslator;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreateAccountTypeFlowImplTest {

    @Mock
    private   AccountTypeTranslator accountTypeTranslator;

    @InjectMocks
    private CreateAccountTypeFlowImpl accountTypeFlowImpl;

    @Before
    public void setUp() throws Exception {
     }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void create() {
        when(accountTypeTranslator.create(any(AccountTypeDto.class))).then(returnsFirstArg());
     AccountTypeDto result=    accountTypeFlowImpl.create(new AccountTypeDto());
        assertNotNull(result);
        assertEquals(LocalDate.now(),result.getCreationDate());
        verify(accountTypeTranslator).create((any(AccountTypeDto.class)));
    }
}