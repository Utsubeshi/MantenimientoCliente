package com.cliente.dao;

import com.cliente.bean.Cliente;
import java.util.List;

public interface ClienteDAO {

    int insertarCliente(Cliente cliente);
    List<Cliente> BuscarCliente(String razonSocial);
    Cliente ObtenerPorId(int id);
    int actualizar(Cliente cliente);
    int eliminar(int id);

}
