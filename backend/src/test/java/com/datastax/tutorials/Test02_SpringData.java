package com.datastax.tutorials;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.datastax.tutorials.service.category.CategoryEntity;
import com.datastax.tutorials.service.category.CategoryRepository;

@SpringBootTest
public class Test02_SpringData {
    
    @Autowired
    CategoryRepository catRepo;
    
    @Test
    public void show_toplevelItems() {
        List<CategoryEntity> categories = catRepo.findByKeyParentId(UUID.fromString("ffdac25a-0244-4894-bb31-a0884bc82aa9"));
        Assertions.assertEquals(4, categories.size());
        System.out.println("Categories:");
        categories.stream().forEach(c -> {
            System.out.println("- " + c.getName() + " with ids:" + c.getKey().getCategoryId());
        });
    }
}
