package main.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TakeBotController {

    @RequestMapping("/")
    public String helloWorld(){
        return "Hello World";
    }
}
