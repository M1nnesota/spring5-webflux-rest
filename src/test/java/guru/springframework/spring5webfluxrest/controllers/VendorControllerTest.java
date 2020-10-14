package guru.springframework.spring5webfluxrest.controllers;

import guru.springframework.spring5webfluxrest.domain.Vendor;
import guru.springframework.spring5webfluxrest.repositories.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;

/**
 * @author ihber
 */
public class VendorControllerTest {

    private WebTestClient webTestClient;
    private VendorController vendorController;
    private VendorRepository vendorRepository;

    @Before
    public void setUp() throws Exception {
        vendorRepository = Mockito.mock(VendorRepository.class);
        vendorController = new VendorController(vendorRepository);
        webTestClient = WebTestClient.bindToController(vendorController).build();
    }

    @Test
    public void list() {
        BDDMockito.given(vendorController.list())
                .willReturn(Flux.just(Vendor.builder().firstName("Ihor").lastName("Berda").build(),
                        Vendor.builder().firstName("Petr").lastName("Petrov").build()));

        webTestClient.get().uri("/api/v1/vendors")
                .exchange()
                .expectBodyList(Vendor.class)
                .hasSize(2);
    }

    @Test
    public void getVendorById() {
        BDDMockito.given(vendorController.getVendorById(anyString()))
                .willReturn(Mono.just(Vendor.builder().firstName("Ihor").lastName("Berda").build()));

        webTestClient.get().uri("/api/v1/vendors/123")
                .exchange()
                .expectBody(Vendor.class);
    }
}