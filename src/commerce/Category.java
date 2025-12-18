package commerce;

import java.util.ArrayList;
import java.util.List;

public class Category {
    String categoryName;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    // 상품 목록 관리
    List<Product> products = new ArrayList<>();
}
