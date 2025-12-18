package commerce;

import java.util.Scanner;

public class CommerceSystem {
    Scanner sc = new Scanner(System.in);
    // 카테고리 생성
    private Category electronics = new Category("전자제품");
    private Category clothes = new Category("의류");
    private Category foods = new Category("식품");
    // 카테고리 값 반환
    public Category getElectronics() {
        return electronics;
    }
    public Category getClothes() {
        return clothes;
    }
    public Category getFoods() {
        return foods;
    }

    // 콘솔 기능
    public void start() {
        while (true) {
            System.out.println("[ 실시간 커머스 플랫폼 메인 ]");
            System.out.println("1. " + electronics.getCategoryName() + "\n2. " +
                    clothes.getCategoryName() + "\n3. " + foods.getCategoryName());
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
        System.out.println("[ " + c.getCategoryName() + " 카테고리 ]");
        for (int i = 0; i < c.getProductsCount(); i++) {
            System.out.println((i + 1) + ". " + c.getProducts(i).getName() + "\t| " +
                    c.getProducts(i).getPrice() + " | " + c.getProducts(i).getDescription());
        }
        System.out.println("0. 뒤로가기");
    }
    // 선택한 상품 정보
    public void productInfo(Category c, int num) {
        System.out.println("선택한 상품: " + c.getProducts(num - 1).getName() + " | " +
                c.getProducts(num - 1).getPrice() + " | " +
                c.getProducts(num - 1).getDescription() + " | 재고: " +
                c.getProducts(num - 1).getInventory() + "개");
    }
}
