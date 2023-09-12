package com.example.testtask.controller;

import com.example.testtask.service.ComputeSymbolsService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("api/v1/string")
@AllArgsConstructor
public class StringControllerV1 {

    private final ComputeSymbolsService computeSymbolsService;

    @GetMapping("")
    public ResponseEntity getSymbols(
            @RequestParam String input,
            @RequestParam(required = false, defaultValue = "false") boolean ignoreRegister,
            @RequestParam(required = false, defaultValue = "true") boolean ignoreSpacing
    ) {

        if (input.isEmpty()) {
            return ResponseEntity.badRequest().body("no input data");
        }

        Map<Character, Integer> sortedMap = computeSymbolsService.computeSymbols(input, ignoreRegister, ignoreSpacing);

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(sortedMap);
    }
}
