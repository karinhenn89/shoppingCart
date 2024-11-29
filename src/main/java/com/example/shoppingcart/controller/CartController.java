package com.example.shoppingcart.controller;

import com.example.shoppingcart.model.Product;
import com.example.shoppingcart.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
/*@CrossOrigin(origins = "*")*/  //lubab kõik pordid, mis käimas on
public class CartController {

    private final CartService cartService;


    public CartController(CartService service) {
        this.cartService = service;
    }

    @PostMapping("/add-product")
    public String addProduct(@RequestBody Product product){
        return cartService.addProduct(product);
    }

    @GetMapping("/get-cart-items")
    public List<Product> getCartItems(){
        return cartService.getAllCartItems();
    }

    @DeleteMapping("/remove/{name}")
    public void removeProductByName(@PathVariable String name){
        cartService.removeProductByName(name);
    }

    @GetMapping("/calculateCartTotal")
    public double calculateCartTotal(){
        return cartService.calculateCartTotal();
    }
    @GetMapping("/tax")
    public double getTax(){
        return cartService.getTax();
    }

    @GetMapping("/discount10")
    public double discount10(){
        return cartService.discount10();
    }
    @GetMapping("/taxtotal")
    public double gettaxtotal(){
        return cartService.gettaxtotal();
    }
}
