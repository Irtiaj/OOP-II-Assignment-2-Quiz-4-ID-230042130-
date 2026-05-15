package com.codurance.ocp;

public class Manager implements EmployeeType {

    @Override
    public int payAmount(int salary, int bonus) {
        return salary + bonus;
    }
}