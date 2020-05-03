package org.fibi.usos.controller;


import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String helloFibi() {
        return "Meow!!";
    }
}
