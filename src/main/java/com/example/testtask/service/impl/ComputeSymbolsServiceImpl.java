package com.example.testtask.service.impl;

import com.example.testtask.service.ComputeSymbolsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ComputeSymbolsServiceImpl implements ComputeSymbolsService {
    @Override
    public Map<Character, Integer> computeSymbols(String input, boolean ignoreRegister, boolean ignoreSpacing) {

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
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e1,
                                LinkedHashMap::new)
                        );

        return sortedMap;
    }
}
