package ra.service;

import ra.model.CartItem;

public interface ICartItem extends  IGenericService{

    void save(CartItem cartItem);
}
