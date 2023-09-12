package com.example.testtask.controller;

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
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

@ExtendWith(MockitoExtension.class)
class StringControllerV1Test {

    private StringControllerV1 controller = new StringControllerV1();

    @Test
    public void getSymbols_noParameters_Status200() {
        String input = "da434 FFFGccc";

        final ResponseEntity<Map<Character, Integer>> responseEntity = controller.getSymbols("", false, true);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
