package com.f3pro.dscatalog.services;


import com.f3pro.dscatalog.dto.ProductDTO;
import com.f3pro.dscatalog.entities.Product;
import com.f3pro.dscatalog.exceptions.DatabaseException;
import com.f3pro.dscatalog.exceptions.ResourceNotFoundException;
import com.f3pro.dscatalog.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(Pageable pageable) {
        Page<Product> list = repository.findAll(pageable);
        return list.map(ProductDTO::new);
    }
    @Transactional(readOnly=true)
    public ProductDTO findById(Long id) {
        Optional<Product> obj = repository.findById(id);
        var entity =obj.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new ProductDTO(entity, entity.getCategories());
    }
    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product entity = new Product();
      //  entity.setName(dto.getName());
        entity = repository.save(entity);
        return new ProductDTO(entity);
    }
    @Transactional
    public ProductDTO update(ProductDTO dto, Long id) {
      try {
          Product entity =repository.getReferenceById(id);
          //entity.setName(dto.getName());
          entity = repository.save(entity);
          return new ProductDTO(entity);
      }catch(EntityNotFoundException e){
          throw  new ResourceNotFoundException(" Recurso não encontrado Id: " + id);

      }
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)){
            throw  new ResourceNotFoundException("Recurso não encontrado");
        }
        try{
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException("Falha de integridade referencial");
        }
    }
}
