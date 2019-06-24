package com.ssy.decorator;

public class ConcreteDecorator1 extends Decorator{

    public ConcreteDecorator1(Component component){
        super(component);
    }

    @Override
    public void doSomeThing() {
        super.doSomeThing();
        doAnotherThing();
    }

    private void doAnotherThing(){
        System.out.println("Function B");
    }
}
