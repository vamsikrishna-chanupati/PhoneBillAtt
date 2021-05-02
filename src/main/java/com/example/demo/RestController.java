package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    AttBill attBill;

    @RequestMapping("/getIndividualTotalBill")
    @PostMapping
    public Map<String, Double> index(@RequestBody Map<String, Double> map) {

        return attBill.generatePhoneBill(map);
    }

}