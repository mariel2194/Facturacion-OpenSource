package com.sg.facturacion.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.facturacion.models.Clientes;
import com.sg.facturacion.repositories.ClienteRepository;
@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // Retorna la lista de clientes
    public List<Clientes> listClientes() {
        return clienteRepository.findAll();
    }

    // Obtiene un cliente por su ID
    public Clientes getClienteById(Integer id) {
        return clienteRepository.findById(id).orElse(null);
    }

    // Guarda un nuevo cliente
    public void saveNew(Clientes cliente) {
        clienteRepository.save(cliente);
    }

    // Actualiza un cliente existente
    public void updateCliente(Clientes cliente) {
        Clientes existingCliente = clienteRepository.findById(cliente.getId()).orElse(null);
        if (existingCliente != null) {
            existingCliente.setNombre(cliente.getNombre());
            existingCliente.setCedula(cliente.getCedula());
            existingCliente.setRnc(cliente.getRnc());
            existingCliente.setTipoPersona(cliente.getTipoPersona());
            existingCliente.setActivo(cliente.isActivo());
            clienteRepository.save(existingCliente);
        }
    }

    // Elimina un cliente por su ID
    public void deleteCliente(Integer id) {
        clienteRepository.deleteById(id);
    }
}
