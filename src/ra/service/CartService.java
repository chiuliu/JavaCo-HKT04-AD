package ra.service;

import ra.model.CartItem;
import ra.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CartService implements ICartItem {
    public static List<CartItem> cartItems = new ArrayList<>();
    public Product product;

    @Override
    public List<CartItem> getAll() {
        return cartItems;
    }

    @Override
    public void save(Object o) {

    }

    @Override
    public void save(CartItem cartItem) {
        CartItem existingCartItem = findByProduct(cartItem.getProduct());
        if (existingCartItem != null) {
            int newQuantity = existingCartItem.getQuantity() + cartItem.getQuantity();
            if (newQuantity <= cartItem.getProduct().getStock()) {
                existingCartItem.setQuantity(newQuantity);
                existingCartItem.setPrice(existingCartItem.getProduct().getProductPrice() * newQuantity);
                cartItem.getProduct().setStock(cartItem.getProduct().getStock() - cartItem.getQuantity());
            } else {
                System.out.println("Không đủ hàng có sẵn.");
            }
        } else {
            if (cartItem.getQuantity() <= cartItem.getProduct().getStock()) {
                cartItems.add(cartItem);
                cartItem.getProduct().setStock(cartItem.getProduct().getStock() - cartItem.getQuantity());
            } else {
                System.out.println("Không đủ hàng có sẵn.");
            }
        }

    }

    private CartItem findByProduct(Product product) {
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct().equals(product)) {
                return cartItem;
            }
        }
        return null;

    }

//    private CartItem findByProduct(Integer id) {
//
//
//    }

    @Override
    public Object findById(Object o) {
        return null;
    }

    @Override
    public void delete(Object o) {

    }
}