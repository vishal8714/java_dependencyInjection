package org.example.depinjection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomePage {


    BillOfMaterial bom;


    public HomePage(@Qualifier("bean2") BillOfMaterial billofM){
        this.bom = billofM;
        String s = bom.getComp();
        System.out.println(s);
    }

    @GetMapping("/home")
    public String Home(){
        return "Home";
    }

}
