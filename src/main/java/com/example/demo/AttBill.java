package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class AttBill {

    private final static Logger LOGGER = Logger.getLogger(AttBill.class.getName());

    public Map<String, Double> generatePhoneBill(Map<String, Double> map) {

        double internet = map.get("internet");
        double totalBill = map.get("totalBill");
        double totalMembers = map.get("numberofpeople");
        double indvInternet = internet / totalMembers;

        Map<String, Double> personsMap = new HashMap<>(map);
        personsMap.remove("internet");
        personsMap.remove("numberofpeople");
        personsMap.remove("totalBill");

        personsMap.forEach((k, v) -> {
            personsMap.put(k, v + indvInternet);
        });

        if(personsMap.containsKey("ramya")) {
            personsMap.put("ramya", personsMap.get("ramya") - 20);
            personsMap.put("rishi", personsMap.get("rishi") + 20);
        }

        LinkedHashMap<String, Double> sortedMap = new LinkedHashMap<>();

        personsMap.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));

        double calculatedBill = sortedMap.values().stream()
                .mapToDouble(v -> v)
                .sum();

        if (calculatedBill < totalBill) {
            LOGGER.log(Level.INFO, "Bill not calculated as expected, you need to revisit Bill buddy else you will be paying from your pocket :( ");
            throw new IllegalStateException();
        }

        return sortedMap;

    }
}
