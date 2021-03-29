package cloudalibaba.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: sunQB
 * @Date: 2021-03-29 21:22
 * @Since: JDK-
 */
@RestController
@Slf4j
@RequestMapping("/provider/nacos")
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/{id}")
    public String getPayment(@PathVariable("id") String id) {
        return "nacos registry,serverPort: " + serverPort + "   id = " + id;
    }
}
