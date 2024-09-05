package tacheservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "projet-service", url = "http://localhost:8081")
public interface ProjetClient {

    @GetMapping("/projets/idprojet")
    Object getProjetby(@RequestParam("idprojet") int idprojet);

}
