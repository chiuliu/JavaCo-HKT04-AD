package ex2;

import java.util.Scanner;
import java.util.Stack;

public class Exam_Advance_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Bước 1: Nhập số ISBN từ người dùng

        System.out.print("Nhập vào số ISBN (10 chữ số): ");
        String isbn = scanner.nextLine().trim();

        // Bước 2: Kiểm tra độ dài của chuỗi ISBN
        if (isbn.length() != 10) {
            System.out.println("Số ISBN phải có đúng 10 chữ số.");
            return;
        }
        // Bước 3: Sử dụng Stack để lưu trữ các chữ số và tính tổng theo công thức
        Stack<Integer> digits = new Stack<>();
        for (int i = 0; i < isbn.length(); i++) {
            char ch = isbn.charAt(i);
            if (!Character.isDigit(ch)) {
                System.out.println("Số ISBN phải chỉ chứa các chữ số.");
                return;
            }
            int digit = Character.getNumericValue(ch);
            digits.push(digit);
        }

        // Bước 4: Tính tổng theo công thức ISBN
        int sum = 0;
        int weight = 1;
        while (!digits.isEmpty()) {
            sum += weight * digits.pop();
            weight++;
        }

        // Kiểm tra tính chia hết cho 11
        if (sum % 11 == 0) {
            System.out.println("ISBN hợp lệ.");
        } else {
            System.out.println("ISBN không hợp lệ.");
        }

        scanner.close();
    }
}
