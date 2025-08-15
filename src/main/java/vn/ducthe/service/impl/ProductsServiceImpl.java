package vn.ducthe.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.ducthe.dto.request.ProductCreateRequest;
import vn.ducthe.dto.request.ProductUpdateRequest;
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
    public Long createProduct(ProductCreateRequest productCreateRequest) {
        // Phần Edit sản phẩm thì sẻ làm ở đây là vì nếu có ID thì có sẻ cập nhật lại thôi không tạo mới.

        ProductsEntity products = productMapper.toEntity(productCreateRequest);
        // Sau do minh se tách các bien the cua san pham do ra roi lưu lại.
        List<VariantsEntity> variantsEntities = productCreateRequest.getVariants().stream().map(item -> {
            return variantMapper.toEntity(item, products);
        }).toList();
        products.setVariantsEntities(variantsEntities);
        // Xu li them phan thong so ki thuat nua
        List<ProductSpecificationsEntity> productSpeci = productCreateRequest.getSpecifications().stream().map(spec -> {
            return productSpecificationMapper.toEntity(spec, products);
        }).toList();
        products.setProductSpecificationsEntities(productSpeci);
        productsRepository.save(products);
        return products.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateProduct(ProductUpdateRequest productUpdateRequest) {
        // Lay Product Tồn tại
        ProductsEntity product = productsRepository.findById(productUpdateRequest.getProductId()).get();
        // Quan hệ ManyToOne thì không nên xóa rồi mới update --> Lỗi. NÊn chỉ cần set thôi.

        // Update basic info
        ShopsEntity shopsEntity = shopsRepository.findById(productUpdateRequest.getBasicInfo().getShopId()).get();
        product.setShopsEntity(shopsEntity);
        CategoriesEntity categoriesEntity = categoriesRepository.findById(productUpdateRequest.getBasicInfo().getCategoryId()).get();
        product.setCategoriesEntity(categoriesEntity);
        BrandsEntity brandsEntity = brandsRepository.findById(productUpdateRequest.getBasicInfo().getBrandId()).get();
        product.setBrandsEntity(brandsEntity);
        product.setName(productUpdateRequest.getBasicInfo().getName());
        product.setDescription(productUpdateRequest.getBasicInfo().getDescription());
        product.setSlug(productUpdateRequest.getBasicInfo().getSlug());

        // Chi xoa nhung quan he la OneToMany thoi --> Product --> Variants.
        // XỬ LÝ VARIANTS. Không nên clear rồi add vì gây ra lỗi Hibernate sẻ bị xung đột, vì nó vừa xóa vừa thêm vào nó không biết
        // được là nên làm như nào.
        List<VariantsEntity> newVarians = productUpdateRequest.getVariants().stream().map(var -> variantMapper.toEntity(var, product)).toList();
        // Set parent cho tất cả variants mới
        newVarians.forEach(variant -> variant.setProductsEntity(product));
        // Thay thế collection cũ bằng collection mới
        product.setVariantsEntities(newVarians);

        // XỬ LÝ SPECIFICATIONS
        List<ProductSpecificationsEntity> newSpecs = productUpdateRequest.getSpecifications().stream().map(spe ->  productSpecificationMapper.toEntity(spe, product)).toList();
        newSpecs.forEach(spec -> spec.setProductsEntity(product));
        product.setProductSpecificationsEntities(newSpecs);

        productsRepository.save(product);
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
