package io.ispacc.orion.order.service;

import io.ispacc.orion.order.entity.Candy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TestService {
    private Candy candy = new Candy();

    public synchronized void method1() {
        candy.setQuantity(candy.getQuantity() - 1);
        log.info("candy quantity: {}", candy.getQuantity());
    }

}
