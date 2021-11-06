package pl.kmk.Narzedzia;

import java.math.BigDecimal;
import java.math.MathContext;

class VatService {
    BigDecimal vatValue;
    public VatService(){
        vatValue=new BigDecimal("0.23");
    }

    public BigDecimal getGrossPriceForDefaultVat(Product product) throws Exception {
        return getGrossPrice(product.getNetValue(),vatValue);
    }

    public BigDecimal getGrossPrice(BigDecimal netValue,BigDecimal vatValue) throws Exception {
        MathContext m=new MathContext(4);

        if (vatValue.compareTo(BigDecimal.ONE)==1){
            throw new Exception("VAT needs to be lower");

        }

        return netValue.multiply(vatValue.add(BigDecimal.ONE)).round(m);
    }


}
