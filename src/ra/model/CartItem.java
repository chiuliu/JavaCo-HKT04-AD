package ra.model;

import ra.run.ProductManagement;
import ra.service.CartItemService;
import ra.service.ProductService;

import java.util.Scanner;

public class CartItem {

    private int cartItemId;
    private Product product;
    private Double price;
    private int quantity;

    public CartItem() {
    }

    public CartItem(int cartItemId, Product product, Double price, int quantity) {
        this.cartItemId = cartItemId;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
    }

    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void inputCartItemData(Scanner scanner){
        this.cartItemId = inputCartItemId();
        this.product = inputProduct(scanner);
        this.quantity = inputQuantity(scanner);
        this.price = (this.product.getProductPrice() * this.quantity);
    }

    private int inputQuantity(Scanner scanner) {
        System.out.println("Nhập số lượng sản phẩm trong giỏ hàng");
        do {
            try {
                int quantity = Integer.parseInt(scanner.nextLine());
                if (quantity>= 0 && quantity <= product.getStock()) {
                    return quantity;
                }else {
                    System.out.println("Sản phẩm k có sẵn, vui lòng thử lại");
                }
            }catch (NumberFormatException e) {
                System.out.println("Nhập số lượng sản phẩm trong giỏ hàng : ");
            }

        }while (true);
    }

    private Product inputProduct(Scanner scanner) {

        do {
            for (int i = 0; i < ProductService.products.size(); i++) {
                System.out.println((i + 1) + ". " + ProductService.products.get(i).getProductName()
                        + "   price : " +( ProductService.products.get(i).getProductPrice()
                        + "   Stock : " + ProductService.products.get(i).getStock()));

            }
            System.out.println((ProductService.products.size()+1) +". Back");
            System.out.println("Nhập lựa chọn của bạn : ");
            int choice = Integer.parseInt(scanner.nextLine());
            if ( choice >0 && choice <= ProductService.products.size()) {
                return ProductService.products.get(choice - 1);
            }else if (choice == (ProductService.products.size()+1)) {
                return ProductManagement.menuUser(scanner);
            }
        }while (true);
    }

    private int inputCartItemId() {
        if (CartItemService.cartItems.isEmpty()){
            return 1;
        }else {
            int maxIndex = CartItemService.cartItems.get(0).getCartItemId();
            for (int i = 1; i < CartItemService.cartItems.size(); i++) {
                if (CartItemService.cartItems.get(i).getCartItemId() > maxIndex) {
                    maxIndex = CartItemService.cartItems.get(i).getCartItemId();
                }
            }
            return maxIndex +1 ;
        }
    }


}
