package org.example.depinjection;

import org.springframework.stereotype.Component;

@Component("bean2")
public class CompBOM implements BillOfMaterial{



    @Override
    public String getComp() {
        return "Component BOM it is";
    }

    @Override
    public int getID() {
        return 789;
    }
}
