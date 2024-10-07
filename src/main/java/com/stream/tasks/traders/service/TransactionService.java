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
        return transactionList.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getYear))
                .collect(Collectors.toList());
    }

    //What are all the unique cities where the traders work? Lvl: Easy
    public List<String> allUniqueCities(List<Transaction> transactionList) {
        return transactionList.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
    }

    //Find all traders from Cambridge and sort them by name. Lvl: Easy
    public List<Trader> allTradersFromCambridge(List<Transaction> transactionList) {
        return transactionList.stream()
                .map(Transaction::getTrader)
                .distinct()
                .filter(trader -> isTraderFromCity(trader, "Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
    }

    //Return a list of all traders names sorted alphabetically. Lvl: Easy
    public List<String> namesOfTradersSortedAlphabetically(List<Transaction> transactionList) {
        return transactionList.stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .distinct()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
    }

    //Return a string of all traders names sorted alphabetically. Lvl: Medium but not obvious. Tip: See Collectors that can working with Stream.
    public String namesOfTradersSortedAlphabeticallyAsOneString(List<Transaction> transactionList) {
        return namesOfTradersSortedAlphabetically(transactionList).stream()
                .collect(Collectors.joining(" "));
    }

    //Are any traders based in Milan? Lvl: Medium
    public boolean areThereTradersFromMilan(List<Transaction> transactionList) {
        return transactionList.stream()
                .map(Transaction::getTrader)
                .anyMatch(trader -> isTraderFromCity(trader, "Milan"));
    }

    //Print the values of all transactions from the traders living in Cambridge. Lvl: Easy
    public List<Integer> valuesOfCambridgeTransactions(List<Transaction> transactionList) {
        return transactionList.stream()
                .filter(transaction -> isTraderFromCity(transaction.getTrader(), "Cambridge"))
                .map(Transaction::getValue)
                .collect(Collectors.toList());
    }

    //What's the highest value of all the transactions? Lvl: Medium
    public OptionalInt highestValueOfAllTransactions(List<Transaction> transactionList) {
        return transactionList.stream()
                .mapToInt(Transaction::getValue)
                .max();
    }

    //Find the transaction with the smallest value. Lvl: Medium
    public Optional<Transaction> transactionWithSmallestValue(List<Transaction> transactionList) {
        return transactionList.stream()
                .min(Comparator.comparing(Transaction::getValue));
    }

    private static boolean isTraderFromCity(Trader trader, String city){
        return trader.getCity().equals(city);
    }
}
