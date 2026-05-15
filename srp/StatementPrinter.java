package com.codurance.srp;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.stream.Collectors.toCollection;

public class StatementPrinter {

    private static final String STATEMENT_HEADER = "DATE | AMOUNT | BALANCE";

    private Console console;
    private StatementFormatter statementFormatter;

    public StatementPrinter(Console console, StatementFormatter statementFormatter) {
        this.console = console;
        this.statementFormatter = statementFormatter;
    }

    public void print(List<Transaction> transactions) {
        printHeader();
        printTransactions(transactions);
    }

    private void printHeader() {
        printLine(STATEMENT_HEADER);
    }

    private void printTransactions(List<Transaction> transactions) {
        final AtomicInteger balance = new AtomicInteger(0);

        transactions.stream()
                .map(transaction -> statementFormatter.statementLine(
                        transaction,
                        balance.addAndGet(transaction.amount())
                ))
                .collect(toCollection(LinkedList::new))
                .descendingIterator()
                .forEachRemaining(this::printLine);
    }

    private void printLine(String line) {
        console.printLine(line);
    }
}