package kz.timka.springmvc.controllers;

import kz.timka.springmvc.dto.ResultDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/calc/add")
    public ResultDTO calculate(@RequestParam Integer a, @RequestParam Integer b) {
        return new ResultDTO(a + b);
    }

}
