package vn.ducthe.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.ducthe.common.Util;
import vn.ducthe.dto.request.CreateProductRequest;
import vn.ducthe.dto.response.*;
import vn.ducthe.exception.ResourceNotFoundException;
import vn.ducthe.mapper.*;
import vn.ducthe.model.*;
import vn.ducthe.repository.*;
import vn.ducthe.service.ProductOptionsService;
import vn.ducthe.service.ProductsService;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductsServiceImpl implements ProductsService {
    private final ProductRepository productRepository;
    private final SpecificationRepository specificationRepository;
    private final VariantRepository variantRepository;
    private final Util  util;
    private final VariantMapper  variantMapper;
    private final ProductMapper productMapper;
    private final SpecificationMapper specificationMapper;
    private final ProductDetailMapper productDetailMapper;
    private final CategoryAttributeMapper  categoryAttributeMapper;
    private final ProductOptionsService  productOptionsService;

    // Chưa code clean and validation dữ liệu.
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long createProduct(CreateProductRequest createProduct) {
        // Cache memory check attribute or specification đã tồn tại chưa.
        Map<String, AttributeOptionEntity> attributeOptionCache = new HashMap<>();
        ProductEntity product = productMapper.createToEntity(createProduct);
        //Specication
        List<ProductSpecificationEntity> productSpecificationEntities = createProduct.getSpecifications()
                .stream()
                .map(speci -> {
            SpecificationEntity specificationEntity;
            if (speci.getIsNew()) {
                specificationEntity = new SpecificationEntity();
                specificationEntity.setName(speci.getName());
                specificationEntity = specificationRepository.save(specificationEntity);
            } else {
                specificationEntity = specificationRepository.findByName(speci.getName());
            }
            ProductSpecificationEntity productSpecificationEntity = new ProductSpecificationEntity();
            productSpecificationEntity.setProductEntity(product);
            productSpecificationEntity.setSpecificationEntity(specificationEntity);
            productSpecificationEntity.setValue(speci.getValue());
            return productSpecificationEntity;
        }).toList();
        product.setProductSpecificationEntities(productSpecificationEntities);
        // Variant
        List<VariantEntity> variantEntities = createProduct.getVariants().stream().map(var -> {
            return variantMapper.createToDto(var, product, attributeOptionCache);
        }).toList();
        product.setVariantEntities(variantEntities);
        productRepository.save(product);
        return product.getId();
    }

    @Override
    public ProductDetailDTO getProductDetails(String slug, Map<String, String> params) {
        ProductEntity product = productRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        ProductDetailDTO productDetailDTO = productDetailMapper.toDTO(product);

        // Specification.
        List<SpecificationDTO> specifications = product.getProductSpecificationEntities().stream()
                .map(specificationMapper::toDTO).toList();
        productDetailDTO.setSpecification(specifications);

        // Lay sign ra de di tim current variants
        List<CategoryAttributeEntity> categoryAttributeEntities = categoryAttributeMapper.toCategoryAttributeEntities(product.getCategoryEntity());
        String sign = util.getSignatureFromOptions(params, categoryAttributeEntities);
        // current variant
        VariantEntity variant = variantRepository.findByProductEntity_IdAndOptionSignature(product.getId(), sign)
                .orElseThrow(() -> new ResourceNotFoundException("Variant not found"));
        VariantDTO currentVariant = variantMapper.toVariantDTO(variant, params);
        productDetailDTO.setCurrentVariant(currentVariant);

        // Lay ra cac option hien co.
        Map<String, Object> availableOptions = productOptionsService.buildAvailableOptions(product, categoryAttributeEntities);

        productDetailDTO.setAvailableOptions(availableOptions);
        return productDetailDTO;
    }

    @Override
    public List<ProductDTO> getProductsTopDeal() {
        List<ProductDTO>  productDTOS = new ArrayList<>();

        List<VariantEntity> variant = variantRepository.findAll();
        variant.forEach(var -> {
            productDTOS.add(productMapper.toDTO(var));
        });
        return productDTOS;
    }

    @Override
    public List<ProductDTO> getNewProducts() {
        // Lay ra thoi gian hien tai va tru di 3 thang truoc
        LocalDateTime localDateTime = LocalDateTime.now().minusMonths(3);
        List<VariantEntity> variants = variantRepository.findByCreatedDateAfter(localDateTime);
        return variants.stream().map(productMapper::toDTO).toList();
    }

    @Override
    public List<ProductDTO> getProductsByCategory(String slug) {
        List<ProductDTO>  productDTOs = new ArrayList<>();
        // Get ALl Product Variants
        List<VariantEntity> variantsEntities = variantRepository.findAll();
        // For Look qua Items Sau Do minh kiem tra Bien The do Thuoc san pham nao
        // Sau do la minh kiem tra san pham do thuoc category cha nao. Neu la cai dang can thi OKI
        // Chon va in ra
        for (VariantEntity variantEntity : variantsEntities) {
            CategoryEntity categoriesEntity = variantEntity.getProductEntity().getCategoryEntity();
            // Kiem tra xem Cha Thuc su cua no co la laptop-may-tinh-de-ban hay khong
            CategoryEntity parent = categoriesEntity.getParent();
            if (parent.getSlug().equals(slug)) {
                productDTOs.add(productMapper.toDTO(variantEntity));
            }
        }
        return productDTOs;
    }


}
