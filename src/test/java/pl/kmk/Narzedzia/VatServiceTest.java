package pl.kmk.Narzedzia;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

class VatServiceTest {
    VatService vatService;
    VatProvider vatProvider;


    @Test
    @DisplayName("Should Count With Default Vat Value")
    void shouldCountDefaultGrossValue() throws IncorrectVatPriceException{
        //given
        Mockito.when(vatProvider.getDefaultVat()).thenReturn(new BigDecimal("0.23"));
        Product product=generateProductWithPrice("20.00","clothes");
        BigDecimal expected = new BigDecimal("24.60");
        //when
        BigDecimal actual = vatService.getGrossPriceForDefaultVat(product);
        //then
        assertThat(actual).isEqualTo(expected);
        //  assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should Count with other Vat Value")
    void shouldCountGrossForDifferentValue() throws Exception, IncorrectVatPriceException {
        //given
        String type="Book";
        Mockito.when(vatProvider.getVatByType(type)).thenReturn(new BigDecimal("0.08"));
        Product product = generateProductWithPrice("20.00",type);
        BigDecimal expected = new BigDecimal("21.60");
        //when
        BigDecimal actual = vatService.getGrossPrice(product.getNetPrice(),type);
        //then
        assertThat(actual).isEqualTo(expected);
        //  assertEquals(expected, actual);
    }
    @Test
    @DisplayName("should throw exeption")
    void shouldThrow() {
        //given
        Product product = generateProductWithPrice("12.00","book");
        Mockito.when(vatProvider.getVatByType("book")).thenReturn(BigDecimal.TEN);
        //when
        //then
        assertThrows(IncorrectVatPriceException.class, () ->
                vatService.getGrossPrice(product.getNetPrice(), "book"));

        assertThatExceptionOfType(IncorrectVatPriceException.class).isThrownBy(
                () -> vatService.getGrossPrice(product.getNetPrice(), "book"));

    }
    @Test
    void shouldSort() {
        //given
        List<Product> productsActual = new ArrayList<>();
        Product p1 = new Product(UUID.randomUUID(), new BigDecimal("10.00"),"book");
        Product p2 = new Product(UUID.randomUUID(), new BigDecimal("20.00"),"book");
        Product p3 = new Product(UUID.randomUUID(), new BigDecimal("30.00"),"book");
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

    @Test
    @DisplayName(value = "sum")
    void shoulSum() {
        //given
        int first = 3;
        int second = 2;
        int expected = 5;
        //when
        int result = first + second;
        //then
        assertEquals(expected, result);
    }

    private Product generateProductWithPrice(String netPrice, String type){
        return new Product(UUID.randomUUID(), new BigDecimal(netPrice), type);
    }


    @BeforeEach
    void setupBeforeEach() {
        vatProvider = Mockito.mock(VatProvider.class);
        vatService = new VatService(vatProvider);
    }


}