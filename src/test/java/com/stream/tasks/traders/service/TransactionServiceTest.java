package com.stream.tasks.traders.service;

import com.stream.tasks.traders.models.Trader;
import com.stream.tasks.traders.models.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import static org.assertj.core.api.Assertions.assertThat;


public class TransactionServiceTest {

    private final TransactionService transactionService = new TransactionService();
    private List<Transaction> transactionList;

    @BeforeEach
    public void init() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        transactionList = List.of(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    @Test
    public void transactionsBySortedValueTest() {
        List<Transaction> actualList = transactionService.transactionsBySortedValue(transactionList);

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        assertThat(actualList).containsExactly(new Transaction(brian, 2011, 300), new Transaction(raoul, 2011, 400));
    }

    @Test
    public void allUniqueCitiesTest() {
        List<String> actualList = transactionService.allUniqueCities(transactionList);

        assertThat(actualList).containsExactly("Cambridge", "Milan");
    }

    @Test
    public void allTradersFromCambridgeTest() {
        List<Trader> actualList = transactionService.allTradersFromCambridge(transactionList);

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        assertThat(actualList).contains(alan, brian, raoul);
    }

    @Test
    public void namesOfTradersSortedAlphabeticallyTest() {
        List<String> actualList = transactionService.namesOfTradersSortedAlphabetically(transactionList);

        assertThat(actualList).containsExactly("Alan", "Brian", "Mario", "Raoul");
    }

    @Test
    void namesOfTradersSortedAlphabeticallyAsOneStringTest() {
        String actualResult = transactionService.namesOfTradersSortedAlphabeticallyAsOneString(transactionList);

        assertThat(actualResult).isEqualTo("Alan Brian Mario Raoul");
    }

    @Test
    void isThereTradersFromMilanTest() {
        boolean actualResult = transactionService.areThereTradersFromMilan(transactionList);

        assertThat(actualResult).isTrue();
    }

    @Test
    void valuesOfCambridgeTransactionsTest() {
        List<Integer> actualResult = transactionService.valuesOfCambridgeTransactions(transactionList);

        assertThat(actualResult).containsExactly(300, 1000, 400, 950);
    }

    @Test
    void highestValueOfAllTransactionsTest() {
        OptionalInt actualResult = transactionService.highestValueOfAllTransactions(transactionList);

        assertThat(actualResult).isEqualTo(OptionalInt.of(1000));
    }

    @Test
    void transactionWithSmallestValueTest() {
        Optional<Transaction> actualResult = transactionService.transactionWithSmallestValue(transactionList);

        Trader brian = new Trader("Brian", "Cambridge");
        Transaction transaction = new Transaction(brian, 2011, 300);

        assertThat(actualResult).isEqualTo(Optional.of(transaction));
    }
}