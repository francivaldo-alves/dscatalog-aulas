package com.f3pro.dscatalog.services;

import com.f3pro.dscatalog.dto.CategoryDTO;
import com.f3pro.dscatalog.entities.Category;
import com.f3pro.dscatalog.exceptions.ResourceNotFoundException;
import com.f3pro.dscatalog.repositories.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly=true)
    public List<CategoryDTO> findAll(){
        List<Category> list =repository.findAll();
        return list.stream().map(CategoryDTO::new).toList();

    }
    @Transactional(readOnly=true)
    public CategoryDTO findById(Long id) {
        Optional<Category> obj = repository.findById(id);
        var entity =obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new CategoryDTO(entity);
    }
    @Transactional
    public CategoryDTO insert(CategoryDTO dto) {
        Category entity = new Category();
        entity.setName(dto.getName());
        entity = repository.save(entity);
        return new CategoryDTO(entity);
    }
    @Transactional
    public CategoryDTO update(CategoryDTO dto, Long id) {
      try {
          Category entity =repository.getReferenceById(id);
          entity.setName(dto.getName());
          entity = repository.save(entity);
          return new CategoryDTO(entity);
      }catch(EntityNotFoundException e){
          throw  new ResourceNotFoundException("Id not found " + id);

      }
    }
}
