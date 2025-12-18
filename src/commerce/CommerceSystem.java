package commerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommerceSystem {
    Scanner sc = new Scanner(System.in);
    // 장바구니 TODO 장바구니 클래스 생성?
    private List<Product> cart = new ArrayList<>();
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
            if (!cart.isEmpty()) {
                System.out.println("\n[ 주문 관리 ]");
                System.out.println("4. 장바구니 확인\t| 장바구니를 확인 후 주문합니다.");
                System.out.println("5. 주문 취소\t\t| 진행중인 주문을 취소합니다.");
            }
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
                case 4:
                    if (cart.isEmpty()) {
                        System.out.println("잘못된 입력입니다.");
                        break;
                    }
                    while (true) {
                        System.out.println("아래와 같이 주문 하시겠습니까?\n");
                        System.out.println("[ 장바구니 내역 ]");
                        for (Product p : cart.stream().distinct().toList()) {
                            System.out.println(p.getName() + "\t| " + p.setPriceWon() + "원 | " +
                                    p.getDescription() + " | 수량: " + cart.stream().filter(n -> n == p).count() + "개");
                        }
                        System.out.println("\n[ 총 주문 금액 ]");
                        int totalPrice = cart.stream().mapToInt(Product::getPrice).sum();
                        System.out.println(String.format("%,d", totalPrice) + "원");
                        System.out.println("\n1. 주문 확정 \t\t 2. 메인으로 돌아가기");
                        System.out.print("번호를 입력해주세요: ");
                        int finalPrice = sc.nextInt();
                        if (finalPrice == 1) {
                            System.out.println("주문이 완료되었습니다! 총 금액: " + String.format("%,d", totalPrice) + "원");
                            for (Product p : cart.stream().distinct().toList()) {
                                System.out.println(p.getName() + "의 재고가 " + p.getInventory() + "개 -> " +
                                        (p.getInventory() - p.getInventoryToken()) + "개로 업데이트되었습니다.");
                                p.setInventory(p.getInventory() - p.getInventoryToken());
                            }
                            for (Product p : cart) {
                                p.setInventoryTokenZero();
                            }
                            cart.clear();
                            break;
                        } else if (finalPrice == 2) {
                            break;
                        } else {
                            System.out.println("잘못된 입력입니다.");
                        }
                    } break;
                case 5:
                    if (cart.isEmpty()) {
                        System.out.println("잘못된 입력입니다.");
                        break;
                    }
                    System.out.println("진행중인 주문이 취소되었습니다.");
                    for (Product p : cart) {
                        p.setInventoryTokenZero();
                    }
                    cart.clear();
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
