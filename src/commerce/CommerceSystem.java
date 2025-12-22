package commerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommerceSystem {
    Scanner sc = new Scanner(System.in);
    // 장바구니
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
            int startNumber = sc.nextInt();
            if (startNumber == 0) {
                System.out.println("커머스 플랫폼을 종료합니다");
                return;
            }
            switch (startNumber) {
                case 1:
                    filterMenu(electronics);
                    break;
                case 2:
                    filterMenu(clothes);
                    break;
                case 3:
                    filterMenu(foods);
                    break;
                case 4:
                    if (cart.isEmpty()) {
                        System.out.println("잘못된 입력입니다.");
                        break;
                    }
                    cartcheck();
                    break;
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
                case 6:
                    adminMenu();
                    break;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }
    // 카테고리 선택 후 필터링 선택
    private void filterMenu(Category c) {
        while (true) {
            System.out.println("[ " + c.getCategoryName() + " 카테고리 ]");
            System.out.println("1. 전체 상품 보기");
            System.out.println("2. 가격대별 필터링 (100만원 이하)");
            System.out.println("3. 가격대별 필터링 (100만원 초과)");
            System.out.println("0. 뒤로가기");
            System.out.print("번호를 입력해주세요: ");
            int filterNumber = sc.nextInt();
            if (filterNumber == 0) {
                break;
            }
            switch (filterNumber) {
                case 1:
                    System.out.println("[ 전체 상품 목록 ]");
                    menuAndInfo(c);
                    break;
                case 2:
                    System.out.println("[ 100만원 이하 상품 목록 ]");
                    menuAndInfoFilter(c, c.filterDownPrice());
                    break;
                case 3:
                    System.out.println("[ 100만원 이상 상품 목록]");
                    menuAndInfoFilter(c, c.filterUpPrice());
                    break;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }
    // 카테고리별 전체 상품 선택 메뉴얼
    private void menuAndInfo(Category c) {
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
    // 카테고리별 필터링 상품 선택 메뉴얼
    private void menuAndInfoFilter(Category c, List<Product> p) {
        while (true) {
            c.productMenuFilter(p);
            System.out.print("번호를 입력해주세요: ");
            int productId = sc.nextInt();
            if (productId == 0) {
                break;
            }
            try {
                c.productInfoFilter(p, productId, sc, cart);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("잘못된 입력입니다.");
            }
        }
    }
    // 장바구니 확인
    private void cartcheck() {
        while (true) {
            System.out.println("아래와 같이 주문 하시겠습니까?\n");
            System.out.println("[ 장바구니 내역 ]");
            for (Product p : cart.stream().distinct().toList()) {
                System.out.println(p.presetNamTapPriDes() + " | 수량: " + p.getInventoryToken() + "개");
            }
            System.out.println("\n[ 총 주문 금액 ]");
            int totalPrice = cart.stream().mapToInt(Product::getPrice).sum();
            System.out.println(String.format("%,d", totalPrice) + "원");
            System.out.println("\n1. 주문 확정 \t\t 2. 메인으로 돌아가기");
            System.out.print("번호를 입력해주세요: ");
            int finalPrice = sc.nextInt();
            if (finalPrice == 1) {
                while (true) {
                    System.out.println("[ 실시간 커머스 고객 등급 ]");
                    System.out.println("1. " + Customer.Grade.BRONZE + "\t:\t " + Customer.Grade.BRONZE.getSale() + "% 할인");
                    System.out.println("2. " + Customer.Grade.SILVER + "\t:\t " + Customer.Grade.SILVER.getSale() + "% 할인");
                    System.out.println("3. " + Customer.Grade.GOLD + " \t:\t" + Customer.Grade.GOLD.getSale() + "% 할인");
                    System.out.println("4. " + Customer.Grade.PLATINUM + "\t:\t" + Customer.Grade.PLATINUM.getSale() + "% 할인");
                    System.out.print("고객 등급을 입력해주세요: ");
                    switch (sc.nextInt()) {
                        case 1:
                            payment(totalPrice, Customer.Grade.BRONZE);
                            return;
                        case 2:
                            payment(totalPrice, Customer.Grade.SILVER);
                            return;
                        case 3:
                            payment(totalPrice, Customer.Grade.GOLD);
                            return;
                        case 4:
                            payment(totalPrice, Customer.Grade.PLATINUM);
                            return;
                        default:
                            System.out.println("잘못된 입력입니다.");
                    }
                }
            } else if (finalPrice == 2) {
                break;
            } else {
                System.out.println("잘못된 입력입니다.");
            }
        }
    }
    // 등급별 할인 적용
    public void payment(int price, Customer.Grade grade) {
        System.out.println("주문이 완료되었습니다!");
        System.out.println("할인 전 금액: " + String.format("%,d", price) + "원");
        System.out.println(grade + " 등급 할인(" + grade.getSale() + "%): -" + String.format("%,d", grade.salePrice(price)) + "원");
        System.out.println("최종 결제 금액: " + String.format("%,d", price - grade.salePrice(price)) + "원");
        for (Product p : cart.stream().distinct().toList()) {
            System.out.println(p.getName() + "의 재고가 " + p.getInventory() + "개 -> " +
                    (p.getInventory() - p.getInventoryToken()) + "개로 업데이트되었습니다.");
            p.setInventory(p.getInventory() - p.getInventoryToken());
        }
        for (Product p : cart) {
            p.setInventoryTokenZero();
        }
        cart.clear();
    }
    // 관리자 모드
    private void adminMenu() {
        int failCount = 0;
        while (failCount <= 2) {
            System.out.print("관리자 비밀번호를 입력해주세요: ");
            String password = sc.next();
            // 현재 관리자 비번 admin123
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
                            adminProductAdd();
                            break;
                        case 2:
                            // 상품 수정
                            adminProductEdit();
                            break;
                        case 3:
                            // 상품 삭제
                            adminProductDelete();
                            break;
                        case 4:
                            // 전체 상품 현황
                            all.allProductsInfo();
                            System.out.print("아무거나 입력하세요 (관리자 모드 메인으로 돌아가기): ");
                            sc.next();
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
    // 관리자 모드 1번 상품 추가 페이지
    private void adminProductAdd() {
        while (true) {
            System.out.println("어느 카테고리에 상품을 추가하시겠습니까?");
            System.out.println("1. " + electronics.getCategoryName());
            System.out.println("2. " + clothes.getCategoryName());
            System.out.println("3. " + foods.getCategoryName());
            System.out.println("0. 뒤로가기");
            System.out.print("번호를 입력해주세요: ");
            switch (sc.nextInt()) {
                case 1:
                    electronics.productAdd(sc);
                    return;
                case 2:
                    clothes.productAdd(sc);
                    return;
                case 3:
                    foods.productAdd(sc);
                    return;
                case 0:
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }
    // 관리자 모드 2번 상품 수정 페이지
    private void adminProductEdit() {
        System.out.print("수정할 상품명을 입력해주세요: ");
        sc.nextLine();
        String editProductName = sc.nextLine();
        List<Product> edit = all.searchProduct(editProductName);
        if (edit.isEmpty()) {
            System.out.println("존재하지 않는 상품입니다.");
            return;
        }
        while (true) {
            System.out.println("현재 상품 정보: " + edit.get(0).presetNamPriDesInv());
            System.out.println("\n어느 항목을 수정하시겠습니까?");
            System.out.println("1. 가격\n2. 설명\n3. 재고수량\n0. 취소");
            System.out.print("번호를 입력해주세요: ");
            switch (sc.nextInt()) {
                case 1:
                    System.out.println("현재 가격: " + edit.get(0).setPriceWon() + "원");
                    System.out.print("새로운 가격을 입력해주세요: ");
                    int editPrice = sc.nextInt();
                    System.out.println("\n" + edit.get(0).getName() + "의 가격이 " +
                            edit.get(0).setPriceWon() + "원 -> " + String.format("%,d", editPrice) + "원으로 수정되었습니다.");
                    edit.get(0).setPrice(editPrice);
                    return;
                case 2:
                    System.out.println("현재 설명: " + edit.get(0).getDescription());
                    System.out.print("새로운 설명을 입력해주세요: ");
                    sc.nextLine();
                    String editDescription = sc.nextLine();
                    System.out.println("\n" + edit.get(0).getName() + "의 설명이 " +
                            edit.get(0).getDescription() + " -> " + editDescription + "(으)로 수정되었습니다.");
                    edit.get(0).setDescription(editDescription);
                    return;
                case 3:
                    System.out.println("현재 재고수량: " + edit.get(0).getInventory() + "개");
                    System.out.print("새로운 재고수량을 입력해주세요: ");
                    int editInventory = sc.nextInt();
                    System.out.println("\n" + edit.get(0).getName() + "의 재고수량이 " +
                            edit.get(0).getInventory() + "개 -> " + editInventory + "개로 수정되었습니다.");
                    edit.get(0).setInventory(editInventory);
                    return;
                case 0:
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }
    // 관리자 모드 3번 상품 삭제 페이지
    private void adminProductDelete() {
        while (true) {
            System.out.println("어느 카테고리의 상품을 삭제하시겠습니까?");
            System.out.println("1. " + electronics.getCategoryName());
            System.out.println("2. " + clothes.getCategoryName());
            System.out.println("3. " + foods.getCategoryName());
            System.out.println("0. 뒤로가기");
            System.out.print("번호를 입력해주세요: ");
            switch (sc.nextInt()) {
                case 1:
                    menuAndDelete(electronics);
                    break;
                case 2:
                    menuAndDelete(clothes);
                    break;
                case 3:
                    menuAndDelete(foods);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }
    // 카테고리 선택 후 상품 삭제 기능
    private void menuAndDelete(Category c) {
        while (true) {
            c.productMenu();
            System.out.print("번호를 입력해주세요: ");
            int productId = sc.nextInt();
            if (productId == 0) {
                break;
            }
            try {
                c.productDelete(productId, sc, cart);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("잘못된 입력입니다.");
            }
        }
    }
}
