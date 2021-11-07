package pl.kmk.Narzedzia;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.UUID;

class Product {
    private UUID id;
    private BigDecimal netValue;

    public Product (UUID id, BigDecimal netValue){
        this.id=id;
        this.netValue=netValue;
    }

    public BigDecimal getNetValue(){
        return netValue;
    }

    public static final Comparator<Product> BY_NETVALUE=Comparator.comparing(Product::getNetValue);

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", netValue=" + netValue +
                '}';
    }
}
