package commerce;

public class Main {
    public static void main(String[] args) {
        CommerceSystem cs = new CommerceSystem();
        Customer customer1 = new Customer("ez", "a1b2c3@gmail.com", "Bronze");
        // 전자제품 상품 생성
        cs.getElectronics().addProduct(new Product("Galaxy S25", 1200000, "최신 안드로이드 스마트폰", 30));
        cs.getElectronics().addProduct(new Product("iPhone 16", 1350000, "Apple의 최신 스마트폰", 30));
        cs.getElectronics().addProduct(new Product("MacBook Pro", 2400000, "M3 칩셋이 탑재된 노트북", 30));
        cs.getElectronics().addProduct(new Product("AirPods Pro", 350000, "노이즈 캔슬링 무선 이어폰", 30));
        // 의류 상품 생성 테스트
        cs.getClothes().addProduct(new Product("발렌시아가", 1200000, "멋진 옷", 30));
        // 식품 상품 생성 테스트
        cs.getFoods().addProduct(new Product("비비고 왕만두", 100000, "맛있는 왕만두", 30));
        // 전체 상품
        cs.getAll().addList(cs.getElectronics());
        cs.getAll().addList(cs.getClothes());
        cs.getAll().addList(cs.getFoods());
        // 콘솔
        cs.start();
    }
}
