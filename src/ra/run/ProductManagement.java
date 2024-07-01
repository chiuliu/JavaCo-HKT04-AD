package ra.run;

import ra.model.CartItem;
import ra.model.Catalog;
import ra.model.Product;
import ra.service.*;

import java.util.Collections;
import java.util.Scanner;

public class ProductManagement {
    public static final CatalogService catalogFuture = new CatalogService();
    public static final ProductService productFuture = new ProductService();
    public static final CartService CART_SERVICE = new CartService();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("******************BASIC MENU****************************");
            System.out.println("1. Quản lý danh mục");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");
            System.out.println("Chọn từ 1-3");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    menuCatalog();
                    break;
                case 2:
                    menuProduct();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.err.println("Chọn từ 1-3");
            }

        }while (true);
    }


    public static void menuCatalog(){
        Scanner scanner = new Scanner(System.in);
        boolean isExit = true;
        do {
            System.out.println("********************CATALOG-MANAGEMENT********************");
            System.out.println("1. Nhập số danh mục thêm mới và nhập thông tin cho từng danh mục");
            System.out.println("2. Hiển thị thông tin tất cả các danh mục");
            System.out.println("3.Sửa tên danh mục theo mã danh mục");
            System.out.println("4.Xóa danh muc theo mã danh mục (lưu ý ko xóa khi có sản phẩm)");
            System.out.println("5.Quay lại");
            System.out.println("Chọn từ 1 -5 : ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    addCatalog(scanner);
                    break;
                case 2:
                    showCatalog();
                    break;
                case 3:
                    updateCatalogName(scanner);
                    break;
                case 4:
                    deleteCatalog(scanner);
                    break;
                case 5:
                    isExit = false;
                    break;
                default:
                    System.err.println("Chọn từ 1 - 5");
            }
        }while (isExit);
    }

    public static void addCatalog(Scanner scanner){
        System.out.println("Nhập số danh mục b muốn thêm : ");
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            Catalog catalog = new Catalog();
            catalog.inputCatalog(scanner);
            catalogFuture.save(catalog);
        }

    }
    public static void showCatalog(){
        if (catalogFuture.getAll().isEmpty()){
            System.err.println("Không có sp trong hệ thống");
        }
        for (Catalog catalog : CatalogService.categories){
            System.out.println(catalog.toString());
        }
    }

    public static void updateCatalogName(Scanner scanner){
        System.out.println("Chọn danh mực muốn sửa: ");
        int catalogid =Integer.parseInt(scanner.nextLine());
        Catalog indexUpdate = catalogFuture.findById(catalogid);
        if(indexUpdate != null){

            System.out.println("Danh mục muốn sửa: ");
            indexUpdate.setCatalogName(scanner.nextLine());
        }else {
            System.err.println("catalog id not found");
        }
    }
    public static void deleteCatalog(Scanner scanner) {
        System.out.println("Không tìm thấy danh mục");
        int catalogId = Integer.parseInt(scanner.nextLine());
        catalogFuture.delete(catalogId);
    }
    public static void menuProduct(){
        Scanner scanner = new Scanner(System.in);
        boolean isExit = true;
        do {
            System.out.println("********************PRODUCT-MANAGEMENT********************");
            System.out.println("1. Nhập số sản sản phẩm và nhập thông tin sản phẩm");
            System.out.println("2. Hiển thị thông tin các sản phẩm");
            System.out.println("3. Sắp xếp sản phẩm theo giá giảm dần");
            System.out.println("4. Xóa sản phẩm theo mã");
            System.out.println("5. Tìm kiếm sách theo tên sách");
            System.out.println("6. Thay đổi thông tin của sách theo mã sách");
            System.out.println("7. quay lai");
            System.out.println("Chọn từ  1-7");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    addProduct(scanner);
                    break;
                case 2:
                    showProduct();
                    break;
                case 3:
                    sortByProductPrice();
                    break;
                case 4:
                    deleteProduct(scanner);
                    break;
                case 5:
                    searchProduct(scanner);
                    break;
                case 6:
                    updateProduct(scanner);
                    break;
                case 7:
                    isExit = false;
                    break;
                default:
                    System.err.println("Chon từ 1 -7 : ");
            }
        }while (isExit);
    }

    public static void addProduct(Scanner scanner){
        System.out.println("Nhập số danh mục bạn muốn thêm : ");
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            Product product = new Product();
            product.inputData(scanner);
            productFuture.save(product);
        }

    }
    public static void showProduct(){
        if (productFuture.getAll().isEmpty()){
            System.err.println("Không có sản phẩm trong hệ thống");
        }
        for (Product product : ProductService.products){
            System.out.println(product.toString());
        }
    }
    public static void deleteProduct(Scanner scanner){
        System.out.println("Nhập sản phẩm muốn xóa");
        String deleteId = scanner.nextLine();
        productFuture.delete(deleteId);
    }
    public static void sortByProductPrice(){
        Collections.sort(productFuture.getAll());
        for (Product product : ProductService.products){
            System.out.println(product.toString());
        }

    }
    public static void searchProduct(Scanner scanner){
        System.out.println("Nhập sản phẩm muốn tìm kiếm");
        String searchName = scanner.nextLine();
        for (Product product : ProductService.products){
            if (product.getProductName().toLowerCase().contains(searchName.toLowerCase())){
                System.out.println(product.toString());
            }
        }
        System.out.println("Tìm thành công");
    }
    public static void updateProduct(Scanner scanner){
        System.out.println("Nhập sản phẩm muốn update");
        String updateId = scanner.nextLine();
        Product indexProductUpdate = productFuture.findById(updateId);
        if (indexProductUpdate != null){
            boolean isExit = true;
            do {
                System.out.println("1. Sửa tên sp ");
                System.out.println("2. Sửa giá sp ");
                System.out.println("3. Sửa mô tả sp ");
                System.out.println("4. Sửa danh mục sp ");
                System.out.println("5. Sửa kho sp ");
                System.out.println("6. Thoát");
                System.out.println("Chọn từ 1 - 6 : ");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        indexProductUpdate.setProductName(indexProductUpdate.inputProductName(scanner));
                        break;
                    case 2:
                        indexProductUpdate.setProductPrice(indexProductUpdate.inputProductPrice(scanner));
                        break;
                    case 3:
                        indexProductUpdate.setDescription(scanner.nextLine());
                        break;
                    case 4:
                        indexProductUpdate.setCatalog(indexProductUpdate.inputCatalog(scanner));
                        break;
                    case 5:
                        indexProductUpdate.setStatus(Boolean.parseBoolean(scanner.nextLine()));
                        break;
                    case 6:
                        isExit = false;
                        break;
                    default:
                        System.err.println("Chọn từ 1 - 6 : ");
                }
            }while (isExit);
        }else {
            System.err.println("Không tìm thấy sp");
        }
    }


    public static Product menuUser(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("********************CATALOG-MANAGEMENT********************");
            System.out.println("1. Xem danh sách sản phẩm");
            System.out.println("2. Thêm vào giỏ hàng");
            System.out.println("3. Xem tất cả sản phẩm giỏ hàng");
            System.out.println("4. Thay đổi số lượng sản phẩm trong giỏ hàng");
            System.out.println("5. Xóa 1 sản phẩm trong giỏ hàng");
            System.out.println("6. Xóa toàn bộ sản phẩm trong giỏ hàng");
            System.out.println("7. quay lai");
            System.out.println("Bạn chọn từ 1 -7 : ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    showAllProduct();
                    break;
                case 2:
                    addProductToCart(scanner);
                    break;
                case 3:
                    showProductInCart();
                    break;
                case 4:
                    updateCartItemQuantity(scanner);
                    break;
                case 5:
                    deleteProductInCart(scanner);
                    break;
                case 6:
                    allDeleteProductCart();
                    break;
                case 7:
                    isExit = false;
                    break;
                default:
                    System.err.println("Invalid choice, please try again choice 1-5");
            }
        }while (isExit);
        return null;
    }

    private static void allDeleteProductCart() {
        CART_SERVICE.getAll().clear();
        System.out.println("Đã xóa tất cả.");

    }

    private static void deleteProductInCart(Scanner scanner) {
        System.out.println("Chọn Id cart mà bạn muốn xóa : ");
        int indexDelete = Integer.parseInt(scanner.nextLine());
        CART_SERVICE.delete(indexDelete);
        
    }

    private static void updateCartItemQuantity(Scanner scanner) {
    }

    private static void showProductInCart() {
        
        
    }

    private static void addProductToCart(Scanner scanner) {
        CartItem cartItem = new CartItem();
        cartItem.inputCartItemData(scanner);
        CART_SERVICE.save(cartItem);
    }

    private static void showAllProduct() {
        
        
    }
}