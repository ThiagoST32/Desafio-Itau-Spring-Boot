package com.desafio.itau.desafioItau;

import com.desafio.itau.desafioItau.domain.Transaction;
import com.desafio.itau.desafioItau.infra.Exceptions.InvalidBodyTransactionException;
import com.desafio.itau.desafioItau.infra.Exceptions.NegativeValueTransactionException;
import com.desafio.itau.desafioItau.infra.Exceptions.TransactionInTheFutureException;
import com.desafio.itau.desafioItau.service.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;

import static org.mockito.Mockito.when;

/**
 * Unit test class for the TransactionService class.
 * This class contains test methods to validate the functionality of the TransactionService,
 * including adding transactions, retrieving statistical summaries, and handling exceptional cases.
 */
@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Test
    public void whenTheValueTransactionIsLessThanZero_thenThrowNegativeValueTransactionException() {
        Transaction transaction = new Transaction(-20, OffsetDateTime.now());
        Assertions.assertThrows(NegativeValueTransactionException.class, () -> this.transactionService.verifyTransaction(transaction));
    }

    @Test
    public void whenAddingTransactionWithZeroValue_shouldPass(){
        Transaction transaction = new Transaction(0, OffsetDateTime.now());
        this.transactionService.addTransaction(transaction);
        Assertions.assertDoesNotThrow(() -> this.transactionService.verifyTransaction(transaction));
    }

    @Test
    public void whenTransactionIsInFuture_theThrowTransactionInTheFutureException(){
        Transaction transactionFuture = new Transaction(20, OffsetDateTime.now().plusMinutes(1));
        Assertions.assertThrows(TransactionInTheFutureException.class, () -> this.transactionService.verifyTransaction(transactionFuture));
    }

    @Test
    public void whenNullTransactionIsAdded_thenThrowInvalidBodyTransactionException(){
        Assertions.assertThrows(InvalidBodyTransactionException.class, () -> this.transactionService.verifyTransaction(null));
    }

    @Test
    public void whenDeletedAllTransactions_thenAllTransactionsAreDeletedSuccessfully() {
        Transaction transaction1 = new Transaction(50, OffsetDateTime.now());
        Transaction transaction2 = new Transaction(150, OffsetDateTime.now());

        this.transactionService.addTransaction(transaction1);
        this.transactionService.addTransaction(transaction2);

        this.transactionService.clearTransaction();
        Assertions.assertEquals(0, this.transactionService.getStatistics120Seconds().getCount());
    }

    @Test
    public void whenMultipleTransactionsAreAdded_thenAllAreStoredSuccessfully() {
        Transaction transaction1 = new Transaction(50, OffsetDateTime.now().minusSeconds(60));
        Transaction transaction2 = new Transaction(150, OffsetDateTime.now().minusSeconds(60));

        this.transactionService.addTransaction(transaction1);
        this.transactionService.addTransaction(transaction2);

        Assertions.assertEquals(2, this.transactionService.getStatistics60Seconds().getCount());
    }

    @Test
    public void getStatistics60Seconds_WhenNoTransactions_ReturnDefaultValues(){
        DoubleSummaryStatistics doubleSummaryStatistics = this.transactionService.getStatistics60Seconds();
        Assertions.assertEquals(0, doubleSummaryStatistics.getCount());
        Assertions.assertEquals(0, doubleSummaryStatistics.getSum());
        Assertions.assertEquals(0, doubleSummaryStatistics.getAverage());
    }

    @Test
    public void getStatistics60Seconds_ShouldReturnOldValues(){
        Transaction transaction1 = new Transaction(50, OffsetDateTime.now().minusSeconds(60));
        Transaction transaction2 = new Transaction(150, OffsetDateTime.now().minusSeconds(60));

        this.transactionService.addTransaction(transaction1);
        this.transactionService.addTransaction(transaction2);

        Assertions.assertEquals(50, this.transactionService.getStatistics60Seconds().getMin());
        Assertions.assertEquals(150, this.transactionService.getStatistics60Seconds().getMax());
    }

    @Test
    public void getStatistics120Seconds_ShouldReturnOldValues(){
        Transaction transaction1 = new Transaction(80, OffsetDateTime.now().minusSeconds(120));
        Transaction transaction2 = new Transaction(120, OffsetDateTime.now().minusSeconds(120));

        this.transactionService.addTransaction(transaction1);
        this.transactionService.addTransaction(transaction2);

        Assertions.assertEquals(80, this.transactionService.getStatistics120Seconds().getMin());
        Assertions.assertEquals(120, this.transactionService.getStatistics120Seconds().getMax());
    }
}
