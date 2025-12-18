package commerce;

public class Main {
    public static void main(String[] args) {
        CommerceSystem cs = new CommerceSystem();
        // 상품 생성
        cs.addProduct(new Product("Galaxy S25", "1,200,000원", "최신 안드로이드 스마트폰", 30));
        cs.addProduct(new Product("iPhone 16", "1,350,000원", "Apple의 최신 스마트폰", 30));
        cs.addProduct(new Product("MacBook Pro", "2,400,000원", "M3 칩셋이 탑재된 노트북", 30));
        cs.addProduct(new Product("AirPods Pro", "350,000원", "노이즈 캔슬링 무선 이어폰", 30));

        // 콘솔
        cs.start();
    }
}
