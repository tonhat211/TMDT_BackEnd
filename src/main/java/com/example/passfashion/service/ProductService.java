package com.example.passfashion.service;

import com.example.passfashion.model.Product;
import com.example.passfashion.utils.VietnameseUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
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
        StringBuilder countSql = new StringBuilder("SELECT COUNT(*) FROM products WHERE is_deleted = 0 AND is_sold = 0");

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
}
