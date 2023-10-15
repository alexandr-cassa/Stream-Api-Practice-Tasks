package com.stream.tasks.traders.service;

import com.stream.tasks.traders.models.Trader;
import com.stream.tasks.traders.models.Transaction;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class TransactionService {

    //Find all transactions in the year 2011 and sort them by value, small to high. Lvl: Easy
    public List<Transaction> transactionsBySortedValue(List<Transaction> transactionList) {
        throw new UnsupportedOperationException("Resolve me :3");
    }

    //What are all the unique cities where the traders work? Lvl: Easy
    public List<String> allUniqueCities(List<Transaction> transactionList) {
        throw new UnsupportedOperationException("Resolve me :3");
    }

    //Find all traders from Cambridge and sort them by name. Lvl: Easy
    public List<Trader> allTradersFromCambridge(List<Transaction> transactionList) {
        throw new UnsupportedOperationException("Resolve me :3");
    }

    //Return a list of all traders names sorted alphabetically. Lvl: Easy
    public List<String> namesOfTradersSortedAlphabetically(List<Transaction> transactionList) {
        throw new UnsupportedOperationException("Resolve me :3");
    }

    //Return a string of all traders names sorted alphabetically. Lvl: Medium but not obvious. Tip: See Collectors that can working with Stream.
    public String namesOfTradersSortedAlphabeticallyAsOneString(List<Transaction> transactionList) {
        throw new UnsupportedOperationException("Resolve me :3");
    }

    //Are any traders based in Milan? Lvl: Medium
    public boolean areThereTradersFromMilan(List<Transaction> transactionList) {
        throw new UnsupportedOperationException("Resolve me :3");
    }

    //Print the values of all transactions from the traders living in Cambridge. Lvl: Easy
    public List<Integer> valuesOfCambridgeTransactions(List<Transaction> transactionList) {
        throw new UnsupportedOperationException("Resolve me :3");
    }

    //What's the highest value of all the transactions? Lvl: Medium
    public OptionalInt highestValueOfAllTransactions(List<Transaction> transactionList) {
        throw new UnsupportedOperationException("Resolve me :3");
    }

    //Find the transaction with the smallest value. Lvl: Medium
    public Optional<Transaction> transactionWithSmallestValue(List<Transaction> transactionList) {
        throw new UnsupportedOperationException("Resolve me :3");
    }
}
