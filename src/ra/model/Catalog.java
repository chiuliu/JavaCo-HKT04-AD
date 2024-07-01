package ra.model;

import java.util.Scanner;

public class Catalog {
    private int catalogId;
    private String catalogName;
    private String descriptions;

    public Catalog() {
    }

    public Catalog(int catalogId, String catalogName, String descriptions) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.descriptions = descriptions;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public void inputCatalog(Scanner scanner) {
        System.out.print("Vui lòng nhập ID danh mục: ");
        this.catalogId = Integer.parseInt(scanner.nextLine());
        this.catalogName = inputCatalogName(scanner);
        this.descriptions = inputCatalogDescription(scanner);
    }

    public String inputCatalogName(Scanner scanner) {
        System.out.println("Nhập tên danh mục: ");
        do {
            String catalogName = scanner.nextLine();
            if (catalogName.trim().isEmpty()) {
                System.out.println("Tên danh mục không được để trống");
            }else {
                return this.catalogName = catalogName;
            }
        }while (true);
    }
    public String inputCatalogDescription(Scanner scanner) {
        System.out.println("Nhập mô tả danh mục : ");
        do {
            String catalogDescription = scanner.nextLine();
            if (catalogDescription.trim().isEmpty()) {
                System.out.println("Mô tả danh mục không được đẻ trống : ");
            }else {
                return this.descriptions = catalogDescription;
            }
        }while (true);
    }

    @Override
    public String toString() {
        return "Danh mục{" +
                "Id danh mục=" + catalogId +
                ", Tên danh mục='" + catalogName + '\'' +
                '}';
    }
}