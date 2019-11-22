package com.cliente.servicesImpl;

import com.cliente.bean.Cliente;
import com.cliente.dao.ClienteDAO;
import com.cliente.daoImpl.ClienteDAOImpl;
import com.cliente.services.ClienteService;

import java.util.List;

public class ClienteServicesImpl implements ClienteService {

    private ClienteDAO clienteDAO = new ClienteDAOImpl();
    @Override
    public int insertarCliente(Cliente cliente) {
        return clienteDAO.insertarCliente(cliente);
    }

    @Override
    public List<Cliente> BuscarCliente(String razonSocial) {
        return clienteDAO.BuscarCliente(razonSocial);
    }

    @Override
    public Cliente ObtenerPorId(int id) {
        return clienteDAO.ObtenerPorId(id);
    }

    @Override
    public int actualizar(Cliente cliente) {
        return clienteDAO.actualizar(cliente);
    }

    @Override
    public int eliminar(int id) {
        return clienteDAO.eliminar(id);
    }
}
