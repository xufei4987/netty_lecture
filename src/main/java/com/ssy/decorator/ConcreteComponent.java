package com.ssy.decorator;

public class ConcreteComponent implements Component {
    @Override
    public void doSomeThing() {
        System.out.println("Function A");
    }
}
