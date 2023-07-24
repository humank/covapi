package solid.humank.covapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MobileCovService {

    @GetMapping("/covapi/mobile/cov/testGet")
    public String mobileCovGetTest() {
        return "{\"result\":\"success with path covapi-mobile-cov-testGet\"}";
    }

    @GetMapping("/covapi/kimDemo/testGet")
    public String kimdemoGetTest() {
        return "{\"result\":\"success with path covapi-kimDemo-testGet\"}";
    }
}
