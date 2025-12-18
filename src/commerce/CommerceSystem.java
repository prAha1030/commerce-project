package commerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommerceSystem {
    Scanner sc = new Scanner(System.in);
    // 장바구니 TODO 장바구니 클래스 생성?
    private List<Product> cart = new ArrayList<>();
    // 카테고리 생성
    private Category all = new Category("전체");
    private Category electronics = new Category("전자제품");
    private Category clothes = new Category("의류");
    private Category foods = new Category("식품");
    // 카테고리 값 반환
    public Category getAll() {
        return all;
    }
    public Category getElectronics() {
        return electronics;
    }
    public Category getClothes() {
        return clothes;
    }
    public Category getFoods() {
        return foods;
    }
    // 메인 콘솔
    public void start() {
        while (true) {
            System.out.println("[ 실시간 커머스 플랫폼 메인 ]");
            System.out.println("1. " + electronics.getCategoryName() + "\n2. " +
                    clothes.getCategoryName() + "\n3. " + foods.getCategoryName());
            System.out.println("0. 종료\t\t\t| 프로그램 종료");
            System.out.println("6. 관리자 모드");
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
                // 관리자 모드
                case 6:
                    adminMenu();
                    break;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }
    // 카테고리 선택 후 상품 선택 메뉴얼
    public void menuAndInfo(Category c) {
        while (true) {
            c.productMenu();
            System.out.print("번호를 입력해주세요: ");
            int productId = sc.nextInt();
            if (productId == 0) {
                break;
            }
            try {
                c.productInfo(productId, sc, cart);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("잘못된 입력입니다.");
            }
        }
    }
    // 관리자 모드
    public void adminMenu() {
        int failCount = 0;
        while (failCount <= 2) {
            System.out.print("관리자 비밀번호를 입력해주세요: ");
            String password = sc.next();
            // 현재 관리자 비번 admin123 / TODO 관리자 비밀번호 설정 기능 구현
            if (password.equals("admin123")) {
                while (true) {
                    System.out.println("[ 관리자 모드 ]");
                    System.out.println("1. 상품 추가");
                    System.out.println("2. 상품 수정");
                    System.out.println("3. 상품 삭제");
                    System.out.println("4. 전체 상품 현황");
                    System.out.println("0. 메인으로 돌아가기");
                    System.out.print("번호를 입력해주세요: ");
                    switch (sc.nextInt()) {
                        case 1:
                            while (true) {
                                // 카테고리에 상품 추가
                                // TODO 0.관리자 모드로 돌아가기 기능 추가?
                                System.out.println("어느 카테고리에 상품을 추가하시겠습니까?");
                                System.out.println("1. " + electronics.getCategoryName());
                                System.out.println("2. " + clothes.getCategoryName());
                                System.out.println("3. " + foods.getCategoryName());
                                System.out.print("번호를 입력해주세요: ");
                                int productAddNumber =  sc.nextInt();
                                if (productAddNumber == 1) {
                                    electronics.productAdd(sc);
                                    break;
                                } else if (productAddNumber == 2) {
                                    clothes.productAdd(sc);
                                    break;
                                } else if (productAddNumber == 3) {
                                    foods.productAdd(sc);
                                    break;
                                } else {
                                    System.out.println("잘못된 입력입니다.");
                                }
                            }
                            break;
                        case 2:
                            // 상품 수정
                            break;
                        case 3:
                            // 상품 삭제
                            break;
                        case 4:
                            // 전체 상품 현황
                            break;
                        case 0:
                            return;
                        default:
                            System.out.println("잘못된 입력입니다.");
                    }
                }
            } else {
                failCount += 1;
            }
        }
    }
}
