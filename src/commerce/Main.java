package commerce;

import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        CommerceSystem cs = new CommerceSystem();
        Customer customer1 = new Customer("ez", "a1b2c3@gmail.com");
        // 전자제품 상품 생성
        cs.getElectronics().addProduct(new Product("Galaxy S25", 1200000, "최신 안드로이드 스마트폰", 30));
        cs.getElectronics().addProduct(new Product("iPhone 16", 1350000, "Apple의 최신 스마트폰", 30));
        cs.getElectronics().addProduct(new Product("MacBook Pro", 2400000, "M3 칩셋이 탑재된 노트북", 30));
        cs.getElectronics().addProduct(new Product("AirPods Pro", 350000, "노이즈 캔슬링 무선 이어폰", 30));
        // 의류 상품 생성
        cs.getClothes().addProduct(new Product("마크 데님 후드 점퍼", 158000, "스타일리쉬한 겨울 아우터", 30));
        cs.getClothes().addProduct(new Product("스노우 라운드 니트", 70000, "겨울에 잘 어울리는 니트", 30));
        cs.getClothes().addProduct(new Product("아나토믹 스니커즈", 1180000, "발렌시아가 남성 슈즈", 30));
        cs.getClothes().addProduct(new Product("캐시미어 혼방 가디건", 1078000, "부드럽고 가벼운 가디건", 30));
        // 식품 상품 생성
        cs.getFoods().addProduct(new Product("업사이드다운징거", 100000, "기묘한 이야기 콜라보 버거", 30));
        cs.getFoods().addProduct(new Product("비비고 왕만두", 200000, "간편한 냉동만두", 30));
        cs.getFoods().addProduct(new Product("한우", 2000000, "예술같은 한우", 30));
        cs.getFoods().addProduct(new Product("바나나", 1000000, "비싸지만 맛있는 바나나", 30));
        // 전체 상품 통합
        cs.getAll().addList(cs.getElectronics());
        cs.getAll().addList(cs.getClothes());
        cs.getAll().addList(cs.getFoods());
        // 커머스 시스템 시작
        try {
            cs.start();
        } catch (InputMismatchException e) {
            System.out.println("오류! 잘못된 입력입니다! 프로그램을 종료합니다");
        }
    }
}
