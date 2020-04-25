package com.young.fighter.course.backend.data;

import com.young.fighter.course.backend.db.entity.Product;
import com.young.fighter.course.backend.dto.ProductView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ProductData {
    public static List<ProductView> getProductViews() {
        return Arrays.asList(
                new ProductView(null, 1034L, "Product1", "Description of first product"),
                new ProductView(null, 1035L, "Product2", "Description of second product"),
                new ProductView(null, 1036L, "Product3", "Description of third product")
        );
    }

    public static List<Product> getProducts() {
        return Arrays.asList(
                new Product(null, 1034L, "Product1", "Description of first product", Collections.emptyList(), Collections.emptyList()),
                new Product(null, 1035L, "Product2", "Description of second product", Collections.emptyList(), Collections.emptyList()),
                new Product(null, 1036L, "Product3", "Description of third product", Collections.emptyList(), Collections.emptyList())
        );
    }
}
