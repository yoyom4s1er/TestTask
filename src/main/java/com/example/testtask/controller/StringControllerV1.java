package com.example.testtask.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/string")
public class StringControllerV1 {

    @GetMapping("")
    public ResponseEntity<Map<Character, Integer>> getSymbols(
            @RequestParam String input,
            @RequestParam(required = false, defaultValue = "false") boolean ignoreRegister,
            @RequestParam(required = false, defaultValue = "true") boolean ignoreSpacing
    ) {

        if (ignoreSpacing) {
            input = input.replaceAll("\\s", "");
        }

        if (ignoreRegister) {
            input = input.toLowerCase();
        }

        HashMap<Character, Integer> unsortedMap = new HashMap<>();

        input.chars().forEach(c ->
                unsortedMap.merge((char)c, 1, Integer::sum)
            );

        Map<Character, Integer> sortedMap =
                unsortedMap.entrySet().stream()
                .sorted(((o1, o2) -> Integer.compare(o2.getValue(), o1.getValue())))
                .collect(Collectors.toMap(
                        Entry::getKey,
                        Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new)
                );

        return ResponseEntity.ok(sortedMap);
    }
}
