package com.f3pro.dscatalog.resources;


import com.f3pro.dscatalog.dto.CategoryDTO;
import com.f3pro.dscatalog.entities.Category;
import com.f3pro.dscatalog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CaregoryResources {

    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findall() {
        List<CategoryDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
}
