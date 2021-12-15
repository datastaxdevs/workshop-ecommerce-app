package com.datastax.tutorials;

import java.net.URI;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import com.datastax.tutorials.service.category.Category;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class Test03_RestController {
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    @DisplayName("Test endpoint invocation")
    public void test() {
        // Given
        URI targetUrl= UriComponentsBuilder.fromUriString("/api/v1/categories/ffdac25a-0244-4894-bb31-a0884bc82aa9")                             
                .build()                                                
                .encode()                                               
                .toUri();
        // When
        ResponseEntity<Category[]> res = restTemplate.getForEntity(targetUrl, Category[].class);
        // Then
        Assertions.assertEquals(HttpStatus.OK, res.getStatusCode());
        Assertions.assertEquals(4, res.getBody().length);
        System.out.println("List Categories:");
        for (Category catWeb : res.getBody()) {
            System.out.println(catWeb.getName());
        }
    }
    
}
