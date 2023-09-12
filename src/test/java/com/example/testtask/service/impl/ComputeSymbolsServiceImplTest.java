package com.example.testtask.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedHashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class ComputeSymbolsServiceImplTest {

    ComputeSymbolsServiceImpl service = new ComputeSymbolsServiceImpl();

    @Test
    public void computeSymbols_defaultParameters_validResult() {
        String input = "aaaaAAAdsce 2222";
        boolean ignoreRegister = false;
        boolean ignoreSpacing = true;
        Map<Character, Integer> mapToCompare = new LinkedHashMap<>();
        mapToCompare.put('a', 4);
        mapToCompare.put('2', 4);
        mapToCompare.put('A', 3);
        mapToCompare.put('c', 1);
        mapToCompare.put('s', 1);
        mapToCompare.put('d', 1);
        mapToCompare.put('e', 1);

        Map<Character, Integer> actual = service.computeSymbols(input, ignoreRegister, ignoreSpacing);

        Assertions.assertEquals(actual, mapToCompare);
    }

    @Test
    public void computeSymbols_ignoreRegister_validResult() {
        String input = "aaaaAAAdsce 2222";
        boolean ignoreRegister = true;
        boolean ignoreSpacing = true;
        Map<Character, Integer> mapToCompare = new LinkedHashMap<>();
        mapToCompare.put('a', 7);
        mapToCompare.put('2', 4);
        mapToCompare.put('c', 1);
        mapToCompare.put('s', 1);
        mapToCompare.put('d', 1);
        mapToCompare.put('e', 1);

        Map<Character, Integer> actual = service.computeSymbols(input, ignoreRegister, ignoreSpacing);

        Assertions.assertEquals(actual, mapToCompare);
    }

    @Test
    public void computeSymbols_noIgnoreSpacing_validResult() {
        String input = "aaaaAAAdsce 2222";
        boolean ignoreRegister = false;
        boolean ignoreSpacing = false;
        Map<Character, Integer> mapToCompare = new LinkedHashMap<>();
        mapToCompare.put('a', 4);
        mapToCompare.put('2', 4);
        mapToCompare.put('A', 3);
        mapToCompare.put(' ', 1);
        mapToCompare.put('c', 1);
        mapToCompare.put('s', 1);
        mapToCompare.put('d', 1);
        mapToCompare.put('e', 1);

        Map<Character, Integer> actual = service.computeSymbols(input, ignoreRegister, ignoreSpacing);

        Assertions.assertEquals(actual, mapToCompare);
    }

    @Test
    public void computeSymbols_ignoreRegisterAndNoIgnoreSpacing_validResult() {
        String input = "aaaaAAAdsce 2222";
        boolean ignoreRegister = true;
        boolean ignoreSpacing = false;
        Map<Character, Integer> mapToCompare = new LinkedHashMap<>();
        mapToCompare.put('a', 7);
        mapToCompare.put('2', 4);
        mapToCompare.put(' ', 1);
        mapToCompare.put('c', 1);
        mapToCompare.put('s', 1);
        mapToCompare.put('d', 1);
        mapToCompare.put('e', 1);

        Map<Character, Integer> actual = service.computeSymbols(input, ignoreRegister, ignoreSpacing);

        Assertions.assertEquals(actual, mapToCompare);
    }
}
