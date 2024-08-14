package com.ursklap.ecommerce.services;

import com.ursklap.ecommerce.dto.requests.CartDetailUpdateRequest;
import com.ursklap.ecommerce.dto.requests.CartRequest;
import com.ursklap.ecommerce.dto.responses.CartResponse;
import com.ursklap.ecommerce.models.Cart;
import com.ursklap.ecommerce.models.CartDetail;
import com.ursklap.ecommerce.models.CustomUserDetails;
import com.ursklap.ecommerce.models.Product;
import com.ursklap.ecommerce.repositories.CartDetailRepository;
import com.ursklap.ecommerce.repositories.CartRepository;
import com.ursklap.ecommerce.utils.ValidateProduct;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartService {
    private ProductService productService;
    private CartDetailRepository cartDetailRepository;
    private CartRepository cartRepository;

    @Transactional
    public void addProduct(CartRequest request, CustomUserDetails userDetails) {
        Cart userCart = userDetails.getCredential().getUser().getCart();
        Product product = this.productService.findById(request.getProductId(), "Cannot add product to cart, product not found");
        ValidateProduct.validateAvailability(product, request.getQuantity());

        Optional<CartDetail> optionalCartDetail = this.cartDetailRepository.findByCartAndProduct(userCart, product);
        CartDetail cartDetail = new CartDetail();

        if (optionalCartDetail.isPresent()) {
            cartDetail = optionalCartDetail.get();
            this.updateCartDetail(new CartDetailUpdateRequest(cartDetail.getId(), cartDetail.getQuantity() + request.getQuantity()));
        } else {
            cartDetail.setCart(userDetails.getCredential().getUser().getCart());
            cartDetail.setProduct(product);
            cartDetail.setQuantity(request.getQuantity());
            cartDetail.setTotalPrice(product.getPrice() * request.getQuantity());
            cartDetail.setTotalDiscountedPrice(product.getDiscountedPrice() * request.getQuantity());
            this.cartDetailRepository.save(cartDetail);

            Cart cart = userDetails.getCredential().getUser().getCart();
            cart.setTotalQuantity(cart.getTotalQuantity() + cartDetail.getQuantity());
            cart.setTotalPrice(cart.getTotalPrice() + cartDetail.getTotalPrice());
            this.cartRepository.save(cart);
        }
    }

    public List<CartResponse> findCartDetailsByCurrentUserCart(CustomUserDetails userDetails) {
        return cartDetailRepository.findCartDetailsByCurrentUserCart(userDetails.getCredential().getUser().getCart().getId());
    }

    public CartResponse findCartDetailByIdAsCartResponse(Long id) {
        return this.cartDetailRepository.findCartDetailById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart detail is not found"));
    }

    public CartDetail findCartDetailById(Long id) {
        return this.cartDetailRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart detail for id " + id + " is not found"));
    }

    public void updateCartDetail(CartDetailUpdateRequest request) {
        CartDetail cartDetail = this.findCartDetailById(request.getCartDetailId());
        cartDetail.setQuantity(request.getQuantity());
        cartDetail.setTotalPrice(request.getQuantity() * cartDetail.getProduct().getPrice());
        cartDetail.setTotalDiscountedPrice(request.getQuantity() * cartDetail.getProduct().getDiscountedPrice());
        cartDetailRepository.save(cartDetail);
    }

    public void removeCartDetail(Long id) {
        CartDetail cartDetail = this.findCartDetailById(id);
        cartDetailRepository.delete(cartDetail);
    }
}
