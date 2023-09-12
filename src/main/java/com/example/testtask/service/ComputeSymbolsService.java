package com.example.testtask.service;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public interface ComputeSymbolsService {

    public Map<Character, Integer> computeSymbols(String input, boolean ignoreRegister, boolean ignoreSpacing);
}
