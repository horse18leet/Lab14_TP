package com.vyatsu.task14.controllers;

import com.vyatsu.task14.entities.Filter;
import com.vyatsu.task14.entities.Product;
import com.vyatsu.task14.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/products")
public class ProductsController {
    private ProductsService productsService;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    public String showProductsList(Model model) {
        Product product = new Product();
        model.addAttribute("products", productsService.getAllProducts());
        model.addAttribute("product", product);
        return "products";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute(value = "product")Product product) {
        productsService.add(product);
        return "redirect:/products";
    }
    @PostMapping("/edit")
    public String editProduct(@ModelAttribute(value = "product")Product product) {
        Product prod = productsService.getById(product.getId());

        prod.setTitle(product.getTitle());
        prod.setPrice(product.getPrice());

        productsService.edit(prod);
        return "redirect:/products";
    }


    @GetMapping("/show/{id}")
    public String showOneProduct(Model model, @PathVariable(value = "id") Long id) {
        Product product = productsService.getById(id);
        model.addAttribute("product", product);
        return "product-page";
    }

    @GetMapping("/edit/{id}")
    public String editOneProduct(Model model, @PathVariable(value = "id") Long id) {
        Product product = productsService.getById(id);
        model.addAttribute("product", product);
        return "edit-product-page";
    }

    @GetMapping("/products")
    public String filterProduct(@RequestParam("title") String title,
                                @RequestParam("minPrice") int minPrice,
                                @RequestParam("maxPrice") int maxPrice, Model model) {

        Filter filter = new Filter(title, minPrice, maxPrice);
        productsService.Filter(filter);

        return "filtered";
    }
}
