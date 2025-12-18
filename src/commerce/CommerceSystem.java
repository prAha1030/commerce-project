package commerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommerceSystem {
    Scanner sc = new Scanner(System.in);
    // 상품 목록
    List<Product> products = new ArrayList<>();

    // 상품 추가
    public void addProduct(Product product) {
        products.add(product);
    }

    // 콘솔 기능
    public void start() {
        System.out.println("[ 실시간 커머스 플랫폼 - 전자제품 ]");
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ". " + products.get(i).name + "\t| " + products.get(i).price + " | " + products.get(i).description);
        }
        System.out.println("0. 종료 \t\t\t| 프로그램 종료");
        for (; ; ) {
            System.out.print("번호를 입력해주세요: ");
            int productId = sc.nextInt();
            if (productId == 0) {
                System.out.println("커머스 플랫폼을 종료합니다");
                return;
            }
            try {
                System.out.println("선택한 상품: " + products.get(productId - 1).name + " | "
                        + products.get(productId - 1).price + " | "
                        + products.get(productId - 1).description + " | 재고: "
                        + products.get(productId - 1).inventory + "개");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("잘못된 입력입니다.");
            }
        }
    }
}
