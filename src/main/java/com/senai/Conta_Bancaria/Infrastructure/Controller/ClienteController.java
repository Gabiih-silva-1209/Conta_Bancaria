package com.senai.Conta_Bancaria.Infrastructure.Controller;

import com.senai.Conta_Bancaria.Application.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

  //  @GetMapping
   // public List<ClienteDTO> listarClientes(){
     //   return clienteService.listarClientes();
    //}
  //  @PostMapping
   // public ClienteDTO salvarCliente(@RequestBody ClienteDTO clienteDTO){
  //      return clienteService.salvarCliente(clienteDTO);
  //  }
   // @PutMapping("/{id}")
}
