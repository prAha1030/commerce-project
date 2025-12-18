package commerce;

public class Main {
    public static void main(String[] args) {
        CommerceSystem cs = new CommerceSystem();
        // 전자제품 상품 생성
        cs.electronics.products.add(new Product("Galaxy S25", "1,200,000원", "최신 안드로이드 스마트폰", 30));
        cs.electronics.products.add(new Product("iPhone 16", "1,350,000원", "Apple의 최신 스마트폰", 30));
        cs.electronics.products.add(new Product("MacBook Pro", "2,400,000원", "M3 칩셋이 탑재된 노트북", 30));
        cs.electronics.products.add(new Product("AirPods Pro", "350,000원", "노이즈 캔슬링 무선 이어폰", 30));
        // 의류 상품 생성 테스트
        cs.clothes.products.add(new Product("발렌시아가", "1,200,000원", "멋진 옷", 30));
        // 식품 상품 생성 테스트
        cs.foods.products.add(new Product("비비고 왕만두", "100,000원", "맛있는 왕만두", 30));

        // 콘솔
        cs.start();
    }
}
