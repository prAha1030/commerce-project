package commerce;

public class Customer {
    // 이름, 이메일, 등급
    private String name;
    private String email;
    // 등급에 따른 할인율
    public enum Grade{
        BRONZE(0),
        SILVER(5),
        GOLD(10),
        PLATINUM(15);

        private final int sale;
        Grade(int sale) {
            this.sale = sale;
        }
        public int getSale() {
            return sale;
        }
        public int salePrice(int price) {
            if (sale == 0) {
                return 0;
            }
            return price * sale / 100;
        }
    }
    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
