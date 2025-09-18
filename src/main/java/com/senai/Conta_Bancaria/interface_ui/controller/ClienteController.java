package com.senai.Conta_Bancaria.interface_ui.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @PostMapping
    public ClienteResponse registrarCliente(){

    }
}
