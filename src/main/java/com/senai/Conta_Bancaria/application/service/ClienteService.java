package com.senai.Conta_Bancaria.application.service;

import com.senai.Conta_Bancaria.application.dto.ClienteAtualizadoDTO;
import com.senai.Conta_Bancaria.application.dto.ClienteRegistroDTO;
import com.senai.Conta_Bancaria.application.dto.ClienteResponseDTO;
import com.senai.Conta_Bancaria.domain.entity.Cliente;
import com.senai.Conta_Bancaria.domain.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;
    private final PasswordEncoder passwordEncoder;


    public ClienteResponseDTO registrarCliente(ClienteRegistroDTO dto){

        var cliente = repository.findByCpfAndAtivoTrue(dto.cpf()).orElseGet(
                () -> repository.save(dto.toEntity())
        );
        var contas = cliente.getContas();
        System.out.println(dto);
        var novaConta = dto.contaDTO().toEntity(cliente);
        System.out.println(novaConta);
        boolean jaTemTipo = contas.stream().anyMatch(
                c -> c.getClass().equals(novaConta.getClass()) && c.isAtiva()
                );
                if (jaTemTipo)
                    throw  new RuntimeException("Cliente já possui uma conta desse tipo");
        cliente.getContas().add(novaConta);
        cliente.setSenha(passwordEncoder.encode(dto.senha()));

        return ClienteResponseDTO.fromEntity(repository.save(cliente));
    }

    public List<ClienteResponseDTO> listarClientesAtivos() {
        return repository.findAllByAtivoTrue().stream()
                .map(ClienteResponseDTO::fromEntity)
                .toList();
    }

    public ClienteResponseDTO buscarClienteAtivoPorCpf(String cpf) {
        var cliente = buscarClientePorCpfEAtivo(cpf, "Cliente não encontrado.");
        return ClienteResponseDTO.fromEntity(cliente);
    }

    private Cliente buscarClientePorCpfEAtivo(String cpf, String message) {
        var cliente = repository.findByCpfAndAtivoTrue(cpf).orElseThrow(
                () -> new RuntimeException(message)
        );
        return cliente;
    }

    public ClienteResponseDTO atualizarCliente(String cpf, ClienteAtualizadoDTO dto) {
        var cliente = buscarClientePorCpfEAtivo(cpf, "Cliente não encontrado");

        cliente.setNome(dto.nome());
 cliente.setCpf(dto.cpf());

 return ClienteResponseDTO.fromEntity(repository.save(cliente));
    }


    public void deletarCliente(String cpf) {
        var cliente = buscarClientePorCpfEAtivo(cpf, "Cliente não encontrado");
        cliente.setAtivo(false);
        cliente.getContas().forEach(
                conta ->conta.setAtiva(false)
        );
        repository.save(cliente);
    }
}
