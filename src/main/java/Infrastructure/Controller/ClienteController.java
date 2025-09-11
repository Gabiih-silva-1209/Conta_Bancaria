package Infrastructure.Controller;

import Application.DTO.ClienteDTO;
import Application.Service.ClienteService;
import Domain.Entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping
    public List<ClienteDTO> listarClientes(){
        return clienteService.listarClientes();
    }
    @PostMapping
    public ClienteDTO salvarCliente(@RequestBody ClienteDTO clienteDTO){
        return clienteService.salvarCliente(clienteDTO);
    }
    @PutMapping("/{id}")
}
