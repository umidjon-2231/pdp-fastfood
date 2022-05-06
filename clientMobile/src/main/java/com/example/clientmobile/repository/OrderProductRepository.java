package com.example.clientmobile.repository;

import com.example.clientmobile.entity.OrderProduct;
import com.example.clientmobile.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
    @Query(nativeQuery = true, 
            value = "select op.product_id from order_product op group by op.product_id order by count(op.product_id)*sum(op.count) desc limit ?")
    List<Long> findTopProducts(Integer limit);

}