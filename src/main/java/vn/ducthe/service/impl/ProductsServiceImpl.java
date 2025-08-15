package vn.ducthe.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.ducthe.dto.request.CreateProductRequest;
import vn.ducthe.dto.response.ProductDTO;
import vn.ducthe.entity.*;
import vn.ducthe.mapper.*;

import vn.ducthe.repository.*;
import vn.ducthe.service.ProductsService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductsServiceImpl implements ProductsService {

    private final ProductMapper productMapper;
    private final VariantsRepository variantsRepository;
    private final CategoriesRepository categoriesRepository;
    private final ProductsRepository productsRepository;
    private final ProductSpecificationMapper  productSpecificationMapper;
    private final VariantMapper variantMapper;
    private final ShopsRepository shopsRepository;
    private final BrandsRepository  brandsRepository;

    @Override
    public List<ProductDTO> getProductsTopDeal() {
        List<ProductDTO> ProductTopDeals = new ArrayList<>();
        // Get Product All Cu The Luon
        List<VariantsEntity> variantsEntities = variantsRepository.findAll();
        // Look Product, Search Variant, then set and information.
        for (VariantsEntity variantsEntity : variantsEntities) {
            ProductTopDeals.add(productMapper.toDto(variantsEntity));
        }
        return ProductTopDeals;
    }

    @Override
    public List<ProductDTO> getProductsByCategory(String slug) {
        List<ProductDTO>  productDTOs = new ArrayList<>();
        // Get ALl Product Variants
        List<VariantsEntity> variantsEntities = variantsRepository.findAll();
        // For Look qua Items Sau Do minh kiem tra Bien The do Thuoc san pham nao
        // Sau do la minh kiem tra san pham do thuoc category cha nao. Neu la cai dang can thi OKI
        // Chon va in ra
        for (VariantsEntity variantsEntity : variantsEntities) {
            CategoriesEntity categoriesEntity = variantsEntity.getProductsEntity().getCategoriesEntity();
            // Kiem tra xem Cha Thuc su cua no co la laptop-may-tinh-de-ban hay khong
            CategoriesEntity parent = categoriesEntity.getParent();
            if (parent.getSlug().equals(slug)) {
                productDTOs.add(productMapper.toDto(variantsEntity));
            }
        }
        return productDTOs;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createProduct(CreateProductRequest createProductRequest) {

        // Informatin Basic.
        ProductsEntity product = new ProductsEntity();
        ShopsEntity shopsEntity = shopsRepository.findById(createProductRequest.getShopId()).get();
        BrandsEntity brandsEntity = brandsRepository.findById(createProductRequest.getBrandId()).get();
        CategoriesEntity categoriesEntity = categoriesRepository.findById(createProductRequest.getCategoryId()).get();
        product.setShopsEntity(shopsEntity);
        product.setBrandsEntity(brandsEntity);
        product.setCategoriesEntity(categoriesEntity);
        product.setName(createProductRequest.getName());
        product.setSlug(createProductRequest.getSlug());
        product.setDescription(createProductRequest.getDescription());

        // Variant
        List<VariantsEntity> variantsEntities = createProductRequest.getVariants().stream().map(var -> variantMapper.toEntityCreate(var, product)).toList();
        product.setVariantsEntities(variantsEntities);

        // Thong so.
        List<ProductSpecificationsEntity> speci = createProductRequest.getSpecifications().stream().map(spec -> productSpecificationMapper.toEntityCreate(spec, product)).toList();
        product.setProductSpecificationsEntities(speci);

        productsRepository.save(product);
        return product.getId();
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> allProducts = new ArrayList<>();
        List<VariantsEntity> variantsEntities = variantsRepository.findAll();
        for (VariantsEntity variantsEntity : variantsEntities) {
            ProductDTO productDTO = productMapper.toDto(variantsEntity);
            allProducts.add(productDTO);
        }
        return allProducts;
    }

    @Override
    public List<ProductDTO> getNewProducts() {
        // Lay ra thoi gian hien tai va tru di 3 thang truoc
        LocalDateTime localDateTime = LocalDateTime.now().minusMonths(3);
        List<VariantsEntity> variants = variantsRepository.findByCreatedDateAfter(localDateTime);

        return variants.stream().map(var -> {
            return productMapper.toDto(var);
        }).toList();
    }
}
