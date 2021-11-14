package pl.kmk.Narzedzia;

import java.math.BigDecimal;

public interface VatProvider {
    BigDecimal getDefaultVat();
    BigDecimal getVatByType(String type);

}
