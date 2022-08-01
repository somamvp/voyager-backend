package somaMVP.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;
import somaMVP.dto.Customer;

import static org.junit.jupiter.api.Assertions.*;

class FluxControllerTest {
    @Autowired
    public FluxController fluxController;
    @Test
    void findName() {
        //given
        Mono<Customer> customerMono = Mono.just(new Customer("name", "email", "phoneNumber", "address"));
        //when
        Mono<Customer> name = fluxController.findName(1);
        //then
        assertEquals(customerMono, name);
    }
}