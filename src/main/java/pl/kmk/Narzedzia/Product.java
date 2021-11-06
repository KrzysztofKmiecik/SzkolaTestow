package pl.kmk.Narzedzia;

import java.math.BigDecimal;
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
}
