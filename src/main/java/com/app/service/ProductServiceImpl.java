package com.app.service;

import com.app.dao.CategoryDao;
import com.app.dao.GuaranteeComponentsDao;
import com.app.dao.ProducerDao;
import com.app.dao.ProductDao;
import com.app.model.Category;
import com.app.model.GuaranteeComponents;
import com.app.model.Producer;
import com.app.model.Product;
import com.app.model.dto.ProductDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;
    private CategoryDao categoryDao;
    private ProducerDao producerDao;
    private GuaranteeComponentsDao guaranteeComponentsDao;

    public ProductServiceImpl(ProductDao productDao, CategoryDao categoryDao, ProducerDao producerDao, GuaranteeComponentsDao guaranteeComponentsDao) {
        this.productDao = productDao;
        this.categoryDao = categoryDao;
        this.producerDao = producerDao;
        this.guaranteeComponentsDao = guaranteeComponentsDao;
    }

    @Override
    public void add(ProductDto productDto) {
        if(productDto != null) {
            Category category = categoryDao.findOneById(productDto.getCategoryId()).get();
            Producer producer = producerDao.findOneById(productDto.getProducerId()).get();
            ModelMapper mapper = new ModelMapper();
            PropertyMap<ProductDto, Product> propertyMap = new PropertyMap<ProductDto, Product>() {
                @Override
                protected void configure() {
                    map().setName(source.getName());
                    map().setPrice(source.getPrice());
                    map().setCategory(category);
                    map().setProducer(producer);
                }
            };
            mapper.addMappings(propertyMap);
            Product product = mapper.map(productDto, Product.class);
            System.out.println("add product");
            System.out.println(product.toString());
            productDao.add(product);
/*
            ModelMapper mapperGuarante = new ModelMapper();

            PropertyMap<ProductDto, GuaranteeComponents> guaranteeComponentspropertyMap = new PropertyMap<ProductDto, GuaranteeComponents>() {
                @Override
                protected void configure() {
                    map().setGuarantee_component(source.getEGuarantee());
                    map().setProduct(product);
                }
            };
            mapper.addMappings(guaranteeComponentspropertyMap);
            GuaranteeComponents guaranteeComponents = mapper.map(productDto, GuaranteeComponents.class);
            System.out.println(guaranteeComponents.toString());
            guaranteeComponentsDao.add(guaranteeComponents);*/

        }
    }

    @Override
    public Optional<ProductDto> getById(Long id) {
        Optional<ProductDto> optionalProductDto = Optional.empty();
        if(id != null)
        {
            Product product = productDao.getById(id);

            ModelMapper mapper = new ModelMapper();
            PropertyMap<Product, ProductDto> propertyMap = new PropertyMap<Product, ProductDto>() {
                @Override
                protected void configure() {
                    map().setName(source.getName());
                    map().setPrice(source.getPrice());
                    map().setCategoryId(source.getCategory().getId());
                    map().setProducerId(source.getProducer().getId());
                    map().setEGuarantee(null);
                }
            };
            mapper.addMappings(propertyMap);
            ProductDto productDto = mapper.map(product, ProductDto.class);
            optionalProductDto = Optional.of(productDto);
            System.out.println(productDto.toString());

        }
        return optionalProductDto;
    }
}
