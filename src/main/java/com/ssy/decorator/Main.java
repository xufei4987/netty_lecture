package com.ssy.decorator;

public class Main {
    public static void main(String[] args) {
        Component component = new ConcreteDecorator2(new ConcreteDecorator1(new ConcreteComponent()));
        component.doSomeThing();
    }
}
