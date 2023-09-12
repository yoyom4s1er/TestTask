package com.example.testtask.controller;

import com.example.testtask.service.ComputeSymbolsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.LinkedHashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class StringControllerV1Test {

    @Mock
    ComputeSymbolsService computeSymbolsService;
    @InjectMocks
    private StringControllerV1 controller;

    @Test
    public void getSymbols_noInputData_Status401() {
        String input = "";

        final ResponseEntity responseEntity = controller.getSymbols(input, false, true);

        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void getSymbols_correctParameters_Status200() {
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

        Mockito
                .doReturn(mapToCompare)
                .when(this.computeSymbolsService)
                .computeSymbols(input, ignoreRegister, ignoreSpacing);


        final ResponseEntity responseEntity = controller.getSymbols(input, ignoreRegister, ignoreSpacing);

        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
        Assertions.assertEquals(mapToCompare, responseEntity.getBody());
    }
}
