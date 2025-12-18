package commerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommerceSystem {
    Scanner sc = new Scanner(System.in);
    // 카테고리 생성
    Category electronics = new Category("전자제품");
    Category clothes = new Category("의류");
    Category foods = new Category("식품");
    // 상품 목록
    List<Product> products = new ArrayList<>();

    // 상품 추가
    public void addProduct(Product product) {
        products.add(product);
    }

    // 콘솔 기능
    public void start() {
        while (true) {
            System.out.println("[ 실시간 커머스 플랫폼 메인 ]");
            System.out.println("1. " + electronics.categoryName + "\n2. " +
                    clothes.categoryName + "\n3. " + foods.categoryName);
            System.out.println("0. 종료 \t\t\t| 프로그램 종료");
            System.out.print("번호를 입력해주세요: ");
            int categoryId = sc.nextInt();
            if (categoryId == 0) {
                System.out.println("커머스 플랫폼을 종료합니다");
                return;
            }
            switch (categoryId) {
                case 1:
                    menuAndInfo(electronics);
                    break;
                case 2:
                    menuAndInfo(clothes);
                    break;
                case 3:
                    menuAndInfo(foods);
                    break;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }
    // 카테고리 선택 후 상품 선택 메뉴얼
    public void menuAndInfo(Category c) {
        productMenu(c);
        while (true) {
            System.out.print("번호를 입력해주세요: ");
            int productId = sc.nextInt();
            if (productId == 0) {
                break;
            }
            try {
                productInfo(c, productId);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("잘못된 입력입니다.");
            }
        }
    }
    // 선택한 카테고리의 상품 메뉴판
    public void productMenu(Category c) {
        System.out.println("[ " + c.categoryName + " 카테고리 ]");
        for (int i = 0; i < c.products.size(); i++) {
            System.out.println((i + 1) + ". " + c.products.get(i).name + "\t| " +
                    c.products.get(i).price + " | " + c.products.get(i).description);
        }
        System.out.println("0. 뒤로가기");
    }
    // 선택한 상품 정보
    public void productInfo(Category c, int num) {
        System.out.println("선택한 상품: " + c.products.get(num - 1).name + " | " +
                c.products.get(num - 1).price + " | " +
                c.products.get(num - 1).description + " | 재고: " +
                c.products.get(num - 1).inventory + "개");
    }
}
