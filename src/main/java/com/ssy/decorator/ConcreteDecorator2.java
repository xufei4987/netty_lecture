package com.ssy.decorator;

public class ConcreteDecorator2 extends Decorator{

    public ConcreteDecorator2(Component component){
        super(component);
    }

    @Override
    public void doSomeThing() {
        super.doSomeThing();
        doAnotherThing();
    }

    private void doAnotherThing(){
        System.out.println("Function C");
    }
}
