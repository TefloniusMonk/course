package com.young.fighter.course.backend.service;

import com.young.fighter.course.backend.db.entity.Product;
import com.young.fighter.course.backend.db.repository.ProductRepository;
import com.young.fighter.course.backend.dto.ProductView;
import com.young.fighter.course.backend.exception.BusinessLogicException;
import com.young.fighter.course.backend.service.api.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DefaultProductService implements ProductService {
    private ProductRepository productRepository;
    private final ModelMapper mapper;

    @Autowired
    public DefaultProductService(ProductRepository productRepository, ModelMapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    public ProductView save(ProductView view) {
        if (view.getProductId() != null) {
            if (productRepository.findById(view.getProductId()).isPresent()) {
                ProductView productView = mapper.map(productRepository.save(mapper.map(view, Product.class)), ProductView.class);
                log.info("Creating new product: {}", productView.toString());
                return productView;
            } else {
                log.error("Cannot find product with id: {}", view.getProductId());
                throw new BusinessLogicException("entity.not.exist");
            }
        }
        log.info("Updating product: {}", view.toString());
        return mapper.map(productRepository.save(mapper.map(view, Product.class)), ProductView.class);
    }

    @Override
    public void delete(Long id) {
        if (productRepository.findById(id).isPresent()) {
            log.info("Deleting product with id: {}", id);
            productRepository.deleteById(id);
        } else {
            log.error("Can not delete product with id: {}", id);
            throw new BusinessLogicException("entity.not.exist");
        }
    }

    @Override
    @Transactional
    public ProductView findById(Long id) {
        if (productRepository.findById(id).isPresent()) {
            Product product = productRepository.findById(id).get();
            Hibernate.initialize(product.getBaskets());
            Hibernate.initialize(product.getCatalogs());
            return mapper.map(product, ProductView.class);
        } else {
            log.error("Product with id {} doesn't exist", id);
            throw new BusinessLogicException("entity.not.exist");
        }
    }

    @Override
    @Transactional
    public List<ProductView> findAll() {
        return productRepository.findAll().stream()
                .map(entity -> {
                    Hibernate.initialize(entity.getBaskets());
                    Hibernate.initialize(entity.getCatalogs());
                    return mapper.map(entity, ProductView.class);
                })
                .collect(Collectors.toList());
    }

    @Override
    public boolean allExist(List ids) {
        return productRepository.existsAllByProductIdIn(ids);
    }

}
