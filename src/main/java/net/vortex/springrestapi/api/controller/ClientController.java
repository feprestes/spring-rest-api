package net.vortex.springrestapi.api.controller;

import net.vortex.springrestapi.domain.model.Client;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ClientController {

    @GetMapping("/clients")
    public List<Client> list(){
        var client1 = new Client();
        client1.setId(1L);
        client1.setName("Zé");
        client1.setEmail("ze@ze.ze");
        client1.setPhone("+55 11 98765-4321");

        var client2 = new Client();
        client2.setId(2L);
        client2.setName("Jão");
        client2.setEmail("jao@jao.jao");
        client2.setPhone("+55 11 98765-4321");

        var client3 = new Client();
        client3.setId(3L);
        client3.setName("Maria");
        client3.setEmail("maria@maria.mar");
        client3.setPhone("+55 11 98765-4321");

        return Arrays.asList(client1, client2, client3);
    }
}
