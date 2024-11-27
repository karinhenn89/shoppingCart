package com.example.shoppingcart.service;

import com.example.shoppingcart.model.Product;
import com.example.shoppingcart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
//andmebaasipäringud - ülekanded - kui üks asi pole lõpuni läinud, siis ei tee teist pool tka . näide näiteks pangaülekandega, et kui üks ülekanne ei jõua kohale, siis ei liiguta ka algset raha nt

public class CartService {

    @Autowired
    private final ProductRepository productRepository;


    public CartService(ProductRepository productRepository) {
        this.productRepository = productRepository;

    }

    public String addProduct(Product product) {
        productRepository.save(product);
        return product.getName() + ", added";
    }

    public List<Product> getAllCartItems(){
        return productRepository.findAll();
    }

    public void removeProductByName(String name){
        productRepository.deleteByName(name);
    }

    //  @Scheduled(fixedRate = 10000)                   //millisekundid, kontrollib iga selle aja tagant
    public Double calculateCartTotal(){
        List<Product> cartItems = getAllCartItems();
        Double total = 0.0;
        for (Product product : cartItems) {
            total += product.calculatetotalPrice();         //selle jaoks on meetod Product klassis
        }
        //      System.out.println("Scheduler total: " + (total));
        return total;
    }
    public double getTax(){
        Double tax = calculateCartTotal() * 0.22;
        return tax;
    }

    public double discount10(){
        boolean isMembership = false;
        double discount = 0;
        if(isMembership){
            discount = (calculateCartTotal() + getTax())*0.1;
        }else {
            discount = 0;
        }
        return discount;
    }
    public double gettaxtotal(){
        double taxplustotal = calculateCartTotal() + getTax();
        return taxplustotal;
    }
}
