package com.f3pro.dscatalog.services;

import com.f3pro.dscatalog.dto.CategoryDTO;
import com.f3pro.dscatalog.entities.Category;
import com.f3pro.dscatalog.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly=true)
    public List<CategoryDTO> findAll(){
        List<Category> list =repository.findAll();
        return list.stream().map(CategoryDTO::new).toList();

    }
}
