package com.f3pro.dscatalog.services;

import com.f3pro.dscatalog.entities.Product;
import com.f3pro.dscatalog.services.ProductService;
import com.f3pro.dscatalog.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ProductServicesTests {

    @InjectMocks
    private ProductService service;
    @Mock
    private ProductRepository repository;

    private long existingId;
    private long nonExistingId;
    private long dependentId;
    private Product product;
    private PageImpl<Product> page;

    @Test
    public void deleteShoulDoNothingWhenIdExists() {


    }
}
