package commerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Product> products = new ArrayList<>();
        // 상품 생성
        products.add(new Product("Galaxy S25", "1,200,000원", "최신 안드로이드 스마트폰", 30));
        products.add(new Product("iPhone 16", "1,350,000원", "Apple의 최신 스마트폰", 30));
        products.add(new Product("MacBook Pro", "2,400,000원", "M3 칩셋이 탑재된 노트북", 30));
        products.add(new Product("AirPods Pro", "350,000원", "노이즈 캔슬링 무선 이어폰", 30));

        System.out.println("[ 실시간 커머스 플랫폼 - 전자제품 ]");
        System.out.println("1. " + products.get(0).name + "\t| " + products.get(0).price + " | " + products.get(0).description);
        System.out.println("2. " + products.get(1).name + "\t| " + products.get(1).price + " | " + products.get(1).description);
        System.out.println("3. " + products.get(2).name + "\t| " + products.get(2).price + " | " + products.get(2).description);
        System.out.println("4. " + products.get(3).name + "\t|\t" + products.get(3).price + " | " + products.get(3).description);
        System.out.println("0. 종료 \t\t\t| 프로그램 종료" );

        for (;;) {
            System.out.print("번호를 입력해주세요: ");
            int productId = sc.nextInt();
            if (productId == 0) {
                System.out.println("커머스 플랫폼을 종료합니다");
                return;
            }
            switch (productId) {
                case 1:
                    System.out.println("선택한 상품: " + products.get(0).name + " | " + products.get(0).price + " | " + products.get(0).description + " | 재고: " + products.get(0).inventory + "개");
                    break;
                case 2:
                    System.out.println("선택한 상품: " + products.get(1).name + " | " + products.get(1).price + " | " + products.get(1).description + " | 재고: " + products.get(1).inventory + "개");
                    break;
                case 3:
                    System.out.println("선택한 상품: " + products.get(2).name + " | " + products.get(2).price + " | " + products.get(2).description + " | 재고: " + products.get(2).inventory + "개");
                    break;
                case 4:
                    System.out.println("선택한 상품: " + products.get(3).name + " | " + products.get(3).price + " | " + products.get(3).description + " | 재고: " + products.get(3).inventory + "개");
                    break;
                default:
                    System.out.println("잘못된 입력입니다.");
                    break;
            }
        }
    }
}
