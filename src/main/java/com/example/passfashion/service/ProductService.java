package com.example.passfashion.service;

import com.example.passfashion.dto.Request.PostProductRequest;
import com.example.passfashion.dto.Response.BasicProductResponse;
import com.example.passfashion.dto.Response.PostProductResponse;
import com.example.passfashion.model.Category;
import com.example.passfashion.model.Image;
import com.example.passfashion.model.Product;
import com.example.passfashion.model.User;
import com.example.passfashion.repository.CategoryRepository;
import com.example.passfashion.repository.ImageRepository;
import com.example.passfashion.repository.ProductRepository;
import com.example.passfashion.repository.UserRepository;
import com.example.passfashion.utils.VietnameseUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Transactional
    public PostProductResponse createProduct(PostProductRequest request) {
        if (!userRepository.existsByIdAndIsDeleted(request.getUserId(), false)) {
            throw new IllegalArgumentException("Người dùng không tồn tại hoặc đã bị xóa");
        }

        // 🔍 Xử lý categoryTitle: tìm trong DB, nếu chưa có thì tạo mới
        String normalizedTitle = request.getCategoryTitle().trim();
        Category category = categoryRepository.findByTitleIgnoreCaseAndIsDeletedFalse(normalizedTitle)
                .orElseGet(() -> {
                    // Tạo mới nếu không tồn tại
                    Category newCategory = new Category();
                    newCategory.setTitle(normalizedTitle);
                    newCategory.setLink(generateCategoryLink(normalizedTitle));
                    newCategory.setIsDeleted(false);
                    return categoryRepository.save(newCategory);
                });

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Người dùng không tồn tại"));

        // Tạo sản phẩm
        Product product = new Product();
        product.setName(request.getName());
        product.setUnsignedName(normalizeName(request.getName()));
        product.setPrice(request.getPrice());
        product.setSalePrice(request.getSalePrice());
        product.setQty(request.getQty() > 0 ? request.getQty() : 1);
        product.setDescription(request.getDescription());
        product.setCondition(request.getCondition());
        product.setMaterial(request.getMaterial());
        product.setBrand(request.getBrand());
        product.setNegotiable(request.isNegotiable());
        product.setPickupAddress(request.getPickupAddress());
        product.setTermsAccepted(request.isTermsAccepted());
        product.setIsSold(false);
        product.setIsDeleted(false);
        product.setCreatedAt(LocalDateTime.now());
        product.setUser(user);
        product.setCategory(category);

        // Lưu sản phẩm trước để có ID
        Product savedProduct = productRepository.save(product);

        // Xử lý ảnh
        if (request.getImageUrls() != null && !request.getImageUrls().isEmpty()) {
            List<Image> images = request.getImageUrls().stream()
                    .map(url -> {
                        Image image = new Image();
                        image.setUrl(url);
                        image.setAlt("Hình ảnh sản phẩm " + product.getName());
                        image.setProduct(savedProduct);
                        return image;
                    })
                    .collect(Collectors.toList());
            savedProduct.setImages(images);
            productRepository.save(savedProduct);
        }

        // Tạo response
        PostProductResponse response = new PostProductResponse();
        response.setId(savedProduct.getId());
        response.setName(savedProduct.getName());
        response.setPrice(savedProduct.getPrice());
        response.setSalePrice(savedProduct.getSalePrice());
        response.setCategoryId(savedProduct.getCategory().getId());
        response.setCategoryTitle(savedProduct.getCategory().getTitle());
        response.setUserId(savedProduct.getUser().getId());
        response.setQty(savedProduct.getQty());
        response.setDescription(savedProduct.getDescription());
        response.setCondition(savedProduct.getCondition());
        response.setMaterial(savedProduct.getMaterial());
        response.setBrand(savedProduct.getBrand());
        response.setNegotiable(savedProduct.isNegotiable());
        response.setPickupAddress(savedProduct.getPickupAddress());
        response.setTermsAccepted(savedProduct.isTermsAccepted());
        response.setIsSold(savedProduct.getIsSold());
        response.setIsDeleted(savedProduct.getIsDeleted());
        response.setCreatedAt(savedProduct.getCreatedAt());
        response.setImageUrls(savedProduct.getImages().stream()
                .map(Image::getUrl)
                .collect(Collectors.toList()));

        return response;
    }

    @PersistenceContext
    private EntityManager entityManager;

    public Page<Product> searchByKeywords(List<String> keywords, Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT * FROM products WHERE is_deleted = 0 AND is_sold = 0");

        for (int i = 0; i < keywords.size(); i++) {
            sql.append(" AND (LOWER(unsigned_name) LIKE :kw")
                    .append(i)
                    .append(" OR LOWER(name) LIKE :kw")
                    .append(i)
                    .append(")");
        }

        Query query = entityManager.createNativeQuery(sql.toString(), Product.class);
        for (int i = 0; i < keywords.size(); i++) {
            String keywordParam = "%" + keywords.get(i).toLowerCase() + "%";
            query.setParameter("kw" + i, keywordParam);
        }

        // Phân trang
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        List<Product> resultList = query.getResultList();

        // Đếm tổng kết quả
        StringBuilder countSql = new StringBuilder(
                "SELECT COUNT(*) FROM products WHERE is_deleted = 0 AND is_sold = 0");

        for (int i = 0; i < keywords.size(); i++) {
            countSql.append(" AND (LOWER(unsigned_name) LIKE :kw")
                    .append(i)
                    .append(" OR LOWER(name) LIKE :kw")
                    .append(i)
                    .append(")");
        }

        Query countQuery = entityManager.createNativeQuery(countSql.toString());
        for (int i = 0; i < keywords.size(); i++) {
            String keywordParam = "%" + keywords.get(i).toLowerCase() + "%";
            countQuery.setParameter("kw" + i, keywordParam);
        }

        long total = ((Number) countQuery.getSingleResult()).longValue();

        return new PageImpl<>(resultList, pageable, total);
    }

    private String normalizeName(String name) {
        // Hàm chuẩn hóa tên (ví dụ: bỏ dấu, chuyển thành chữ thường)
        return name.toLowerCase().replaceAll("[^a-z0-9]", "-");
    }

    private String generateCategoryLink(String title) {
        return title.toLowerCase().replaceAll("[^a-z0-9]", "-");
    }

    public void deleteProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm với ID: " + productId));

        productRepository.delete(product); // Tự động xoá images vì cascade
    }

    public List<String> findDistinctMaterials() {
        return productRepository.findDistinctMaterials();
    }

    public List<Product> getProductByUser(long userId) {
        User user = entityManager.find(User.class, userId);
        return productRepository.findByUserIdWithImages(userId);
    }

    public Product getProductById(long userId) {
        return productRepository.findById(userId);
    }

    public void updateProduct(long productId, Product product) {
        Product oldProduct = productRepository.findById(productId);
        oldProduct.setName(product.getName());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setDescription(product.getDescription());
        productRepository.save(oldProduct);
    }
}
