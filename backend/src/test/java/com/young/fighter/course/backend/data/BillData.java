package com.young.fighter.course.backend.data;

import com.young.fighter.course.backend.db.entity.Bill;
import com.young.fighter.course.backend.db.entity.Customer;
import com.young.fighter.course.backend.db.entity.Product;
import com.young.fighter.course.backend.dto.BillView;
import com.young.fighter.course.backend.dto.ProductView;

import java.time.LocalDateTime;
import java.util.List;

public class BillData {
    public static Bill getBill(Customer customer, List<Product> products) {
        return new Bill(null, customer, products, LocalDateTime.now(), products.stream().mapToLong(Product::getPrice).sum());
    }

    public static BillView getBillView(Long customerId, List<ProductView> products) {
        return new BillView(null, customerId, products, null, products.stream().mapToLong(ProductView::getPrice).sum());
    }
}
