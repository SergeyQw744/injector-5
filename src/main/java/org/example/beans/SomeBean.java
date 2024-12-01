package org.example.beans;

import org.example.annotation.AutoInjectable;
import org.example.interfaces.InterfaceOne;
import org.example.interfaces.InterfaceTwo;

public class SomeBean {
    @AutoInjectable
    private InterfaceOne fieldOne;
    @AutoInjectable
    private InterfaceTwo fieldTwo;

    public void process(){
        fieldOne.doSomeOne();
        fieldTwo.doSomeTwo();
    }
}

