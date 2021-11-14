package pl.kmk.Narzedzia;

import java.math.BigDecimal;
import java.math.MathContext;

class VatService {
    VatProvider vatprovider;

    public VatService(VatProvider vatProvider) {
        this.vatprovider = vatProvider;
    }

    public BigDecimal getGrossPriceForDefaultVat(Product product) throws IncorrectVatPriceException {
        return calculateGrossPrice(product.getNetPrice(), vatprovider.getDefaultVat());
    }


    public BigDecimal getGrossPrice(BigDecimal netValue, String productType) throws IncorrectVatPriceException {
        BigDecimal vatValue = vatprovider.getVatByType(productType);
        return calculateGrossPrice(netValue, vatValue);
    }

    private BigDecimal calculateGrossPrice(BigDecimal netPrice, BigDecimal vatValue) throws IncorrectVatPriceException {
        MathContext m = new MathContext(4);
        if (vatValue.compareTo(BigDecimal.ONE) == 1) {
            throw new IncorrectVatPriceException("VAT needs to be lower");
        }
        return netPrice.multiply(vatValue.add(BigDecimal.ONE)).round(m);
    }
}
