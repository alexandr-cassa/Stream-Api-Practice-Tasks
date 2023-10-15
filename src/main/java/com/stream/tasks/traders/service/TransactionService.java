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
//                .sorted(Comparator.comparing(Transaction::getValue))
                .sorted()
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
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .collect(Collectors.toList());
    }

    //Return a list of all traders names sorted alphabetically. Lvl: Easy
    public List<String> namesOfTradersSortedAlphabetically(List<Transaction> transactionList) {
        return transactionList.stream()
                .map(transaction -> transaction.getTrader().getName())
                .sorted()
                .distinct()
                .collect(Collectors.toList());
    }

    //Return a string of all traders names sorted alphabetically. Lvl: Medium but not obvious. Tip: See Collectors that can working with Stream.
    public String namesOfTradersSortedAlphabeticallyAsOneString(List<Transaction> transactionList) {
        return transactionList.stream()
                .map(transaction -> transaction.getTrader().getName())
                .sorted()
                .distinct()
                .collect(Collectors.joining(" "));
        //.reduce("", (n1,n2) -> n1 + " " + n2)

        //.reduce(
        //                       new StringBuilder(),
        //                       (StringBuilder sb, String str) -> sb.append(str).append(" "),
        //                       (StringBuilder sb1,StringBuilder sb2) -> sb1.append(sb2))
    }

    //Are any traders based in Milan? Lvl: Medium
    public boolean areThereTradersFromMilan(List<Transaction> transactionList) {
        return transactionList.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
    }

    //Print the values of all transactions from the traders living in Cambridge. Lvl: Easy
    public List<Integer> valuesOfCambridgeTransactions(List<Transaction> transactionList) {
        return transactionList.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
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
                .min(Comparator.comparingInt(Transaction::getValue));
        //.reduce((x1, x2) -> x1.getValue() < x2.getValue() ? x1 : x2);
    }
}
