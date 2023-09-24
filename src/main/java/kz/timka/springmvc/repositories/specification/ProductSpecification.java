package kz.timka.springmvc.repositories.specification;

import kz.timka.springmvc.models.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    public static Specification<Product> priceGreaterOrEqualsThan(Integer price) {
        return ((root, query, criteriaBuilder)
                -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), price));
    }

    public static Specification<Product> priceLessThanOrEqualsThan(Integer price) {
        return ((root, query, criteriaBuilder)
                -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), price));
    }
    // select s from Student s where s.name like %Bo%
    public static Specification<Product> titleLike(String titlePart) {
        return ((root, query, criteriaBuilder)
                -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titlePart)));
    }

}
