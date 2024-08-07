package com.ursklap.ecommerce.services;

import com.ursklap.ecommerce.dto.requests.CartRequest;
import com.ursklap.ecommerce.models.Cart;
import com.ursklap.ecommerce.models.CartDetail;
import com.ursklap.ecommerce.models.CustomUserDetails;
import com.ursklap.ecommerce.models.Product;
import com.ursklap.ecommerce.repositories.CartDetailRepository;
import com.ursklap.ecommerce.repositories.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class CartService {
    private ProductService productService;
    private CartDetailRepository cartDetailRepository;
    private CartRepository cartRepository;

    @Transactional
    public void addProduct(CartRequest request, CustomUserDetails userDetails) {
        Product product = this.productService.findById(request.getProductId(), "Cannot add product to cart, product not found");
        if (!product.getIsInStock()) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Product out of stock");
        }
        if (request.getQuantity() > product.getStock()) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Maximum quantity product is " + product.getStock());
        }
        CartDetail cartDetail = new CartDetail();
        cartDetail.setCart(userDetails.getCredential().getUser().getCart());
        cartDetail.setProduct(product);
        cartDetail.setQuantity(request.getQuantity());
        cartDetail.setTotalPrice(product.getPrice() * request.getQuantity());
        this.cartDetailRepository.save(cartDetail);

        Cart cart = userDetails.getCredential().getUser().getCart();
        cart.setTotalQuantity(cart.getTotalQuantity() + cartDetail.getQuantity());
        cart.setTotalPrice(cart.getTotalPrice() + cartDetail.getTotalPrice());
        this.cartRepository.save(cart);

        product.setStock(product.getStock() - request.getQuantity());
        product.setIsInStock(product.getStock() >= 1);
        this.productService.create(product);
    }
}
