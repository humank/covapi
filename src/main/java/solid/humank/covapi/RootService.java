package solid.humank.covapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootService {
    @GetMapping("/")
    public String healthCheck() {
        return "success";
    }
}
