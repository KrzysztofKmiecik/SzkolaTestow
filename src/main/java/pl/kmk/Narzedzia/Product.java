package pl.kmk.Narzedzia;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.UUID;

class Product {
    private UUID id;
    private BigDecimal netPrice;
    private String type;

    public Product(UUID id, BigDecimal netPrice, String type) {
        this.id = id;
        this.netPrice = netPrice;
        this.type = type;
    }

    public BigDecimal getNetPrice(){
        return netPrice;
    }

    public static final Comparator<Product> BY_NETVALUE=Comparator.comparing(Product::getNetPrice);

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", netValue=" + netPrice +
                '}';
    }
}
