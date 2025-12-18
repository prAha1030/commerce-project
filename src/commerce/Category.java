package commerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Category {
    private String categoryName;
    // 상품 목록
    private List<Product> products = new ArrayList<>();

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }
    // 카테고리 조회
    // TODO 사실상 제거 가능
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
    // 선택한 카테고리의 상품 메뉴판
    public void productMenu() {
        System.out.println("[ " + categoryName + " 카테고리 ]");
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ". " + products.get(i).presetNamTapPriDes());
        }
        System.out.println("0. 뒤로가기");
    }
    // 선택한 상품 정보 + 장바구니 추가
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
}
