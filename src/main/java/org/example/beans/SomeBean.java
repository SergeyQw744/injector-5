package org.example.beans;

import org.example.annotation.AutoInjectable;
import org.example.interfaces.InterfaceOne;
import org.example.interfaces.InterfaceTwo;

public class SomeBean {
    @AutoInjectable
    private InterfaceOne oneField;
    @AutoInjectable
    private InterfaceTwo twoField;

    public void process(){
        oneField.doSomeOne();
        twoField.doSomeTwo();
    }
}

