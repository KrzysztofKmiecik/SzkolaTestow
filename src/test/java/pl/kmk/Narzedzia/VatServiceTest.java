package pl.kmk.Narzedzia;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.UUID;

class VatServiceTest {
    VatService vatService;

    @Test
    @DisplayName("Should Count With Default Vat Value")
    void shouldCountDefaultGrossValue() throws Exception {
        //given
        Product product = generateProductWithPrice("20.00");
        BigDecimal expected = new BigDecimal("24.60");
        //when
        BigDecimal actual = vatService.getGrossPriceForDefaultVat(product);
        //then
        assertEquals(expected, actual);
    }


    @Test
    @DisplayName("Should Count with other Vat Value")
    void shouldCountGrossForDifferentValue() throws Exception {
        //given
        Product product = generateProductWithPrice("20.00");
        BigDecimal expected = new BigDecimal("24.00");
        //when
        BigDecimal actual = vatService.getGrossPrice(product.getNetValue(), new BigDecimal("0.20"));
        //then
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should throw exeption")
    void shouldThrow(){
        //given
        Product product= generateProductWithPrice("12.00");
        //when
        //then
        assertThrows(Exception.class,()->
                vatService.getGrossPrice(product.getNetValue(),BigDecimal.TEN));

    }

    private Product generateProductWithPrice(String s) {
        return new Product(UUID.randomUUID(), new BigDecimal(s));
    }


    @BeforeEach
    void setupBeforeEach() {
        vatService = new VatService();
    }

}