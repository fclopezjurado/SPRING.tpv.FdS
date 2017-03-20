package api;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import wrappers.TotalVouchersWrapper;
import wrappers.VoucherWrapper;

public class VoucherResourceFunctionalTesting {

    @Before
    public void init() {
        new RestService().deleteAll();
    }

    @Test
    public void GetTotal() {

        VoucherWrapper newVoucherWrapper = new VoucherWrapper();
        newVoucherWrapper.setValue(new BigDecimal("100.50"));
        new RestBuilder<VoucherWrapper>(RestService.URL).path(Uris.VOUCHERS).clazz(VoucherWrapper.class).body(newVoucherWrapper).post()
                .build();

        newVoucherWrapper = new VoucherWrapper();
        newVoucherWrapper.setValue(new BigDecimal("50"));
        newVoucherWrapper.setExpiration(new GregorianCalendar(2015, 5, 5));

        new RestBuilder<VoucherWrapper>(RestService.URL).path(Uris.VOUCHERS).clazz(VoucherWrapper.class).body(newVoucherWrapper).post()
                .build();

        newVoucherWrapper = new VoucherWrapper();
        newVoucherWrapper.setValue(new BigDecimal("25"));

        VoucherWrapper voucherWrapperToconsume = new RestBuilder<VoucherWrapper>(RestService.URL).path(Uris.VOUCHERS)
                .clazz(VoucherWrapper.class).body(newVoucherWrapper).post().build();

        new RestBuilder<VoucherWrapper>(RestService.URL).path(Uris.VOUCHERS).path("/" + voucherWrapperToconsume.getReference())
                .clazz(VoucherWrapper.class).post().build();

        TotalVouchersWrapper totalVouchersWrapper = new RestBuilder<TotalVouchersWrapper>(RestService.URL).path(Uris.VOUCHERS)
                .clazz(TotalVouchersWrapper.class).get().build();

        assertEquals(new BigDecimal("100.50"), totalVouchersWrapper.getTotal());
    }

    @Test
    public void createVoucher() {
        VoucherWrapper newVoucherWrapper = new VoucherWrapper();
        newVoucherWrapper.setValue(new BigDecimal("100"));
        VoucherWrapper voucherWrapper = new RestBuilder<VoucherWrapper>(RestService.URL).path(Uris.VOUCHERS).clazz(VoucherWrapper.class)
                .body(newVoucherWrapper).post().build();

        assertNotNull(voucherWrapper.getReference());
    }

    @Test
    public void consumeVoucher() {
        VoucherWrapper newVoucherWrapper = new VoucherWrapper();
        newVoucherWrapper.setValue(new BigDecimal("101.00"));
        VoucherWrapper voucherWrapper = new RestBuilder<VoucherWrapper>(RestService.URL).path(Uris.VOUCHERS).clazz(VoucherWrapper.class)
                .body(newVoucherWrapper).post().build();

        VoucherWrapper consumedvoucherWrapper = new RestBuilder<VoucherWrapper>(RestService.URL).path(Uris.VOUCHERS)
                .path("/" + voucherWrapper.getReference()).clazz(VoucherWrapper.class).post().build();

        assertEquals(new BigDecimal("101.00"), consumedvoucherWrapper.getValue());
    }

    @Test
    public void searchVoucher() {

        new RestService().deleteAll();

        VoucherWrapper newVoucherWrapper = new VoucherWrapper();
        newVoucherWrapper.setValue(new BigDecimal("133.00"));
        VoucherWrapper voucherWrapper = new RestBuilder<VoucherWrapper>(RestService.URL).path(Uris.VOUCHERS).clazz(VoucherWrapper.class)
                .body(newVoucherWrapper).post().build();

        List<VoucherWrapper> VoucherList = Arrays.asList(new RestBuilder<VoucherWrapper[]>(RestService.URL).path(Uris.VOUCHERS)
                .path("/" + voucherWrapper.getReference()).clazz(VoucherWrapper[].class).get().build());

        assertEquals(1, VoucherList.size());

        newVoucherWrapper.setValue(new BigDecimal("134.00"));
        voucherWrapper = new RestBuilder<VoucherWrapper>(RestService.URL).path(Uris.VOUCHERS).clazz(VoucherWrapper.class)
                .body(newVoucherWrapper).post().build();

        VoucherList = Arrays.asList(new RestBuilder<VoucherWrapper[]>(RestService.URL).path(Uris.VOUCHERS).path("/all")
                .clazz(VoucherWrapper[].class).get().build());

        assertEquals(2, VoucherList.size());

    }

    // Exceptions

    @Test
    public void invalidNewVoucher() {

        VoucherWrapper newVoucherWrapper = new VoucherWrapper();

        try {

            new RestBuilder<VoucherWrapper>(RestService.URL).path(Uris.VOUCHERS).clazz(VoucherWrapper.class).body(newVoucherWrapper).post()
                    .build();
            fail();

        } catch (HttpClientErrorException httpError) {
            assertEquals(HttpStatus.BAD_REQUEST, httpError.getStatusCode());
            System.out.println("ERROR>>>>> " + httpError.getMessage());
            System.out.println("ERROR>>>>> " + httpError.getResponseBodyAsString());
        }

        newVoucherWrapper = new VoucherWrapper();
        newVoucherWrapper.setValue(new BigDecimal("-1"));

        try {

            new RestBuilder<VoucherWrapper>(RestService.URL).path(Uris.VOUCHERS).clazz(VoucherWrapper.class).body(newVoucherWrapper).post()
                    .build();
            fail();

        } catch (HttpClientErrorException httpError) {
            assertEquals(HttpStatus.BAD_REQUEST, httpError.getStatusCode());
            System.out.println("ERROR>>>>> " + httpError.getMessage());
            System.out.println("ERROR>>>>> " + httpError.getResponseBodyAsString());
        }

    }

    @Test
    public void WorngConsumeVoucher() {

        VoucherWrapper newVoucherWrapper = new VoucherWrapper();

        try {

            new RestBuilder<VoucherWrapper>(RestService.URL).path(Uris.VOUCHERS).path("/" + newVoucherWrapper.getReference())
                    .clazz(VoucherWrapper.class).post().build();
            fail();

        } catch (HttpClientErrorException httpError) {
            assertEquals(HttpStatus.BAD_REQUEST, httpError.getStatusCode());
            System.out.println("ERROR>>>>> " + httpError.getMessage());
            System.out.println("ERROR>>>>> " + httpError.getResponseBodyAsString());
        }

        newVoucherWrapper = new VoucherWrapper();
        newVoucherWrapper.setReference("PEPE");

        try {

            new RestBuilder<VoucherWrapper>(RestService.URL).path(Uris.VOUCHERS).path("/" + newVoucherWrapper.getReference())
                    .clazz(VoucherWrapper.class).post().build();
            fail();

        } catch (HttpClientErrorException httpError) {
            assertEquals(HttpStatus.BAD_REQUEST, httpError.getStatusCode());
            System.out.println("ERROR>>>>> " + httpError.getMessage());
            System.out.println("ERROR>>>>> " + httpError.getResponseBodyAsString());
        }

        newVoucherWrapper = new VoucherWrapper();
        newVoucherWrapper.setReference("XXXXXXXXXXYYYYYYYYYYZZZZZZZ");

        try {

            new RestBuilder<VoucherWrapper>(RestService.URL).path(Uris.VOUCHERS).path("/" + newVoucherWrapper.getReference())
                    .clazz(VoucherWrapper.class).post().build();
            fail();

        } catch (HttpClientErrorException httpError) {
            assertEquals(HttpStatus.NOT_FOUND, httpError.getStatusCode());
            System.out.println("ERROR>>>>> " + httpError.getMessage());
            System.out.println("ERROR>>>>> " + httpError.getResponseBodyAsString());
        }

    }

    @Test
    public void wrongVoucher() {

        VoucherWrapper newVoucherWrapper = new VoucherWrapper();

        try {

            Arrays.asList(new RestBuilder<VoucherWrapper[]>(RestService.URL).path(Uris.VOUCHERS)
                    .path("/" + newVoucherWrapper.getReference()).clazz(VoucherWrapper[].class).get().build());
            fail();

        } catch (HttpClientErrorException httpError) {
            assertEquals(HttpStatus.BAD_REQUEST, httpError.getStatusCode());
            System.out.println("ERROR>>>>> " + httpError.getMessage());
            System.out.println("ERROR>>>>> " + httpError.getResponseBodyAsString());
        }

        newVoucherWrapper = new VoucherWrapper();
        newVoucherWrapper.setReference("PEPE");

        try {

            Arrays.asList(new RestBuilder<VoucherWrapper[]>(RestService.URL).path(Uris.VOUCHERS)
                    .path("/" + newVoucherWrapper.getReference()).clazz(VoucherWrapper[].class).get().build());
            fail();

        } catch (HttpClientErrorException httpError) {
            assertEquals(HttpStatus.BAD_REQUEST, httpError.getStatusCode());
            System.out.println("ERROR>>>>> " + httpError.getMessage());
            System.out.println("ERROR>>>>> " + httpError.getResponseBodyAsString());
        }

    }

    @After
    public void after() {
        new RestService().deleteAll();
    }

}
