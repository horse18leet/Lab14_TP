package com.vyatsu.task14.repositories;

import com.vyatsu.task14.entities.Filter;
import com.vyatsu.task14.entities.Product;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductRepository {
    private List<Product> products;
    private List<Product> filteredProducts;

    @PostConstruct
    public void init() {
        products = new ArrayList<Product>();
        products.add(new Product(1L, "Bread", 40));
        products.add(new Product(2L, "Milk", 90));
        products.add(new Product(3L, "Cheese", 200));
    }

    public List<Product> findAll() {
        return products;
    }

    public Product findByTitle(String title) {
        return products.stream().filter(p -> p.getTitle().equals(title)).findFirst().get();
    }

    public Product findById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst().get();
    }

    public void save(Product product) {
        products.add(product);
    }
    public void edit(Product product){
        Product prod = products.get(product.getId().intValue() - 1);

        prod.setPrice(product.getPrice());
        prod.setTitle(product.getTitle());
    }
    public List<Product> filter(Filter filter){
        filteredProducts = products.stream()
                .filter(product -> product.getPrice() >= filter.GetMinPrice()
                        && product.getPrice() <= filter.GetMaxPrice()).collect(Collectors.toList());

        return filteredProducts;
    }

}
