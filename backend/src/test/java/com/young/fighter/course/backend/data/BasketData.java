//package com.young.fighter.course.backend.data;
//
//import com.young.fighter.course.backend.db.entity.Basket;
//import com.young.fighter.course.backend.db.entity.Customer;
//import com.young.fighter.course.backend.db.entity.Product;
//import com.young.fighter.course.backend.dto.BasketView;
//import com.young.fighter.course.backend.dto.ProductView;
//
//import java.util.List;
//
//public class BasketData {
//    public static BasketView getBasketView(Long customerId, List<ProductView> productViews) {
//        return new BasketView(null, customerId, productViews, productViews.stream().mapToLong(ProductView::getPrice).sum());
//    }
//
//    public static Basket getBasket(Customer customer, List<Product> products) {
//        return new Basket(null, customer, products, products.stream().mapToLong(Product::getPrice).sum());
//    }
//}
