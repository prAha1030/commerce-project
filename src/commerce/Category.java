package commerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Category {
    private String categoryName;
    // 상품 저장공간
    private List<Product> products = new ArrayList<>();

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }
    // 카테고리 이름 조회
    public String getCategoryName() {
        return categoryName;
    }
    // 리스트 합체
    public void addList(Category category) {
        this.products.addAll(category.products);
    }
    // 상품 추가
    public void addProduct(Product product) {
        products.add(product);
    }
    // 상품 검색 기능
    public List<Product> searchProduct(String productName) {
        return products.stream().filter(n -> n.getName().equals(productName)).toList();
    }
    // 100만원 이하 필터
    public List<Product> filterDownPrice() {
        return products.stream().filter(n -> n.getPrice() <= 1000000).toList();
    }
    // 100만원 초과 필터
    public List<Product> filterUpPrice() {
        return products.stream().filter(n -> n.getPrice() > 1000000).toList();
    }
    // 선택한 카테고리의 전체 상품 메뉴판
    public void productMenu() {
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ". " + products.get(i).presetNamTapPriDes());
        }
        System.out.println("0. 뒤로가기");
    }
    // 선택한 카테고리의 필터링된 상품 메뉴판
    public void productMenuFilter(List<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ". " + products.get(i).presetNamTapPriDes());
        }
        System.out.println("0. 뒤로가기");
    }
    // 전체 상품 중 선택한 상품 정보 + 장바구니 추가
    public void productInfo(int num, Scanner sc, List<Product> cart) {
        if (products.get(num -1).getInventoryToken() == products.get(num -1).getInventory()) {
            System.out.println("선택한 상품은 더 이상 재고가 없습니다!");
            return;
        }
        while (true) {
            System.out.println("선택한 상품: " + products.get(num -1).presetNamPriDesInv());
            System.out.println("\"" + products.get(num - 1).presetNamPriDes() + "\"");
            System.out.println("위 상품을 장바구니에 추가하시겠습니까?");
            System.out.println("1. 확인 \t\t 2. 취소");
            System.out.print("번호를 입력해주세요: ");
            switch (sc.nextInt()) {
                case 1:
                    cart.add(products.get(num - 1));
                    products.get(num - 1).plusOneToken();
                    System.out.println(products.get(num - 1).getName() + "이(가) 장바구니에 추가되었습니다.");
                    return;
                case 2:
                    System.out.println("장바구니 추가가 취소되었습니다.");
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }
    // 필터링된 상품 중 선택한 상품 정보 + 장바구니 추가
    public void productInfoFilter(List<Product> filterProducts, int num, Scanner sc, List<Product> cart) {
        if (filterProducts.get(num -1).getInventoryToken() == filterProducts.get(num -1).getInventory()) {
            System.out.println("선택한 상품은 더 이상 재고가 없습니다!");
            return;
        }
        while (true) {
            System.out.println("선택한 상품: " + filterProducts.get(num -1).presetNamPriDesInv());
            System.out.println("\"" + filterProducts.get(num - 1).presetNamPriDes() + "\"");
            System.out.println("위 상품을 장바구니에 추가하시겠습니까?");
            System.out.println("1. 확인 \t\t 2. 취소");
            System.out.print("번호를 입력해주세요: ");
            switch (sc.nextInt()) {
                case 1:
                    cart.add(filterProducts.get(num - 1));
                    filterProducts.get(num - 1).plusOneToken();
                    System.out.println(filterProducts.get(num - 1).getName() + "이(가) 장바구니에 추가되었습니다.");
                    return;
                case 2:
                    System.out.println("장바구니 추가가 취소되었습니다.");
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }
    // 관리자 상품 추가 기능
    public void productAdd(Scanner sc) {
        System.out.println("[ " + categoryName + " 카테고리에 상품 추가 ]");
        System.out.print("상품명을 입력해주세요: ");
        sc.nextLine();
        String addName = sc.nextLine();
        if (products.stream().anyMatch(n -> n.getName().equals(addName))) {
            System.out.println("이미 동일한 이름의 제품이 존재합니다.");
            return;
        }
        System.out.print("가격을 입력해주세요: ");
        int addPrice = sc.nextInt();
        System.out.print("상품 설명을 입력해주세요: ");
        sc.nextLine();
        String addDescription = sc.nextLine();
        System.out.print("재고수량을 입력해주세요: ");
        int addInventory = sc.nextInt();
        while (true) {
            System.out.println("\n" + addName + " | " + String.format("%,d", addPrice) + "원 | " +
                    addDescription + " | 재고: " + addInventory + "개");
            System.out.println("위 정보로 상품을 추가하시겠습니까?");
            System.out.println("1. 확인\t2.취소");
            System.out.print("번호를 입력해주세요: ");
            switch (sc.nextInt()) {
                case 1:
                    System.out.println("성공적으로 상품이 추가되었습니다.");
                    products.add(new Product(addName, addPrice, addDescription, addInventory));
                    return;
                case 2:
                    System.out.println("상품 추가가 취소되었습니다.");
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }
    // 관리자 상품 삭제 기능
    public void productDelete(int num, Scanner sc, List<Product> cart) {
        while (true) {
            System.out.println("선택한 상품: " + products.get(num -1).presetNamPriDesInv());
            System.out.println("\"" + products.get(num - 1).presetNamPriDes() + "\"");
            System.out.println("위 상품을 정말 삭제하시겠습니까?");
            System.out.println("1. 확인 \t\t 2. 취소");
            System.out.print("번호를 입력해주세요: ");
            switch (sc.nextInt()) {
                case 1:
                    System.out.println(products.get(num - 1).getName() + "이(가) 삭제되었습니다.");
                    cart.remove(products.get(num - 1));
                    products.remove(num - 1);
                    return;
                case 2:
                    System.out.println("선택한 상품 삭제가 취소되었습니다.");
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }
    // 관리자 전체 상품 현황
    public void allProductsInfo() {
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ". " + products.get(i).presetNamTapPriDesInv());
        }
    }
}
