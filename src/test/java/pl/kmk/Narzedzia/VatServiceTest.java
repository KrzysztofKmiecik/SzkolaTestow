package pl.kmk.Narzedzia;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

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
        assertThat(actual).isEqualTo(expected);
        //  assertEquals(expected, actual);
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
        assertThat(actual).isEqualTo(expected);
        //  assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should throw exeption")
    void shouldThrow() {
        //given
        Product product = generateProductWithPrice("12.00");
        //when
        //then
       /* assertThrows(Exception.class, () ->
                vatService.getGrossPrice(product.getNetValue(), BigDecimal.TEN));*/

        assertThatExceptionOfType(Exception.class).isThrownBy(
                () -> vatService.getGrossPrice(product.getNetValue(), BigDecimal.TEN));

    }

    @Test
    void shouldSort() {
        //given
        List<Product> productsActual = new ArrayList<>();
        Product p1 = new Product(UUID.randomUUID(), new BigDecimal("10.00"));
        Product p2 = new Product(UUID.randomUUID(), new BigDecimal("20.00"));
        Product p3 = new Product(UUID.randomUUID(), new BigDecimal("30.00"));
        System.out.println("BEFORE");
        productsActual.add(p3);
        productsActual.add(p2);
        productsActual.add(p1);
        productsActual.forEach(System.out::println);

        System.out.println();
        List<Product> productsExpected = new ArrayList<>();
        productsExpected.add(p1);
        productsExpected.add(p2);
        productsExpected.add(p3);
        productsExpected.forEach(System.out::println);
        //when
        productsActual.sort(Product.BY_NETVALUE);

        System.out.println("AFTER");
        productsActual.forEach(System.out::println);
        //then


        assertArrayEquals(productsExpected.toArray(), productsActual.toArray());

    }


    private Product generateProductWithPrice(String s) {
        return new Product(UUID.randomUUID(), new BigDecimal(s));
    }


    @BeforeEach
    void setupBeforeEach() {
        vatService = new VatService();
    }

}