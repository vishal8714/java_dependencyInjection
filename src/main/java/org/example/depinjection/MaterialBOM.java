package org.example.depinjection;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("bean1")
public class MaterialBOM implements BillOfMaterial{
    @Override
    public String getComp() {
        return "Material BOM it is ";
    }

    @Override
    public int getID() {

        return 123;
      }
}
