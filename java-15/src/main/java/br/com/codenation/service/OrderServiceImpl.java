package br.com.codenation.service;

import br.com.codenation.model.OrderItem;
import br.com.codenation.model.Product;
import br.com.codenation.repository.ProductRepository;
import br.com.codenation.repository.ProductRepositoryImpl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class OrderServiceImpl implements OrderService {

    private ProductRepository productRepository = new ProductRepositoryImpl();

    @Override
    public Double calculateOrderValue(List<OrderItem> items) {
        return items.stream()
                .mapToDouble(orderItem -> {
                    Product productFound = productRepository.findById(orderItem.getProductId())
                            .orElseThrow(RuntimeException::new);

                    return (orderItem.getQuantity() * productFound.getValue()) * (productFound.getIsSale() ? 0.8 : 1);
                }).sum();
    }

    @Override
    public Set<Product> findProductsById(List<Long> ids) {
        return ids.stream()
                .map(id -> productRepository.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }

    @Override
    public Double calculateMultipleOrders(List<List<OrderItem>> orders) {
        return orders.stream().mapToDouble(this::calculateOrderValue).sum();
    }

    @Override
    public Map<Boolean, List<Product>> groupProductsBySale(List<Long> productIds) {
        return findProductsById(productIds).stream().collect(groupingBy(Product::getIsSale));
    }

}