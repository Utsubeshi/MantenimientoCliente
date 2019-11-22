package com.cliente.daoImpl;

import com.cliente.bean.Cliente;
import com.cliente.dao.ClienteDAO;
import java.sql.*;
import java.util.List;
import com.cliente.util.UtilSQL;
import org.apache.log4j.Logger;

public class ClienteDAOImpl implements ClienteDAO {

    private static Logger logger = Logger.getLogger(ClienteDAOImpl.class.getName());

    private UtilSQL con;
    private Connection cn;
    private ResultSet rs;
    private PreparedStatement ps;
    private CallableStatement cs;
    private String sql;
    private int flgOperacion = 0;

    @Override
    public int insertarCliente(Cliente cliente) {
        logger.info("insertar");

        sql = "call insertarCliente(?,?,?,?,?)";

        System.out.println(sql + " consulta");

        try {
            con = new UtilSQL();
            cn = con.getConection();
            cn.setAutoCommit(false);

            ps = cn.prepareStatement(sql);
            ps.setInt(1, cliente.getId());
            ps.setString(2,cliente.getRuc());
            ps.setString(3,cliente.getRazonSocial());
            ps.setString(4,cliente.getDireccion());
            ps.setString(5,cliente.getTelefono());

            flgOperacion = ps.executeUpdate();

            if (flgOperacion > 0){
                cn.commit();
                System.out.println(sql + " commit!!");
            }else{
                cn.rollback();
                System.out.printf(sql + "rollback!!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {   } //cierra conexion
        return  flgOperacion;
    }

    @Override
    public List<Cliente> BuscarCliente(String razonSocial) {
        logger.info("buscar");

        sql = "select * from cliente where razonSocial like('% ? %')";

        System.out.println(sql + " busqueda");
        List<Cliente> lstCliente = null;
        Cliente cliente = null;
        try {
            con = new UtilSQL();
            cn = con.getConection();
            cn.setAutoCommit(false);

            ps = cn.prepareStatement(sql);
            ps.setString(1,razonSocial);

            rs = ps.executeQuery();
            while (rs.next()){
                cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setRazonSocial(rs.getString("razonSocial"));
                cliente.setRuc(rs.getString("ruc"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setTelefono(rs.getString("telefono"));
            }

            lstCliente.add(cliente);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {   } //cierra conexion
        return  lstCliente;
    }

    @Override
    public Cliente ObtenerPorId(int id) {
        logger.info("buscar por id");

        sql = "select * from cliente where id = ?";

        System.out.println(sql + "buscar");
        Cliente cliente = null;
        try {
            con = new UtilSQL();
            cn = con.getConection();
            cn.setAutoCommit(false);

            ps = cn.prepareStatement(sql);
            ps.setInt(1, id);

            rs = ps.executeQuery();
            while(rs.next()){
                cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setRazonSocial(rs.getString("razonSocial"));
                cliente.setRuc(rs.getString("ruc"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setTelefono(rs.getString("telefono"));
            }
        }  catch (SQLException e) {
            e.printStackTrace();
        } finally { }

        return cliente;
    }

    @Override
    public int actualizar(Cliente cliente) {
        logger.info("actualizar");

        sql = "call actualizarCliente(?,?,?,?,?)";

        System.out.println(sql + " update");

        try {
            con = new UtilSQL();
            cn = con.getConection();
            cn.setAutoCommit(false);

            ps = cn.prepareStatement(sql);
            ps.setInt(1, cliente.getId());
            ps.setString(2,cliente.getRuc());
            ps.setString(3,cliente.getRazonSocial());
            ps.setString(4,cliente.getDireccion());
            ps.setString(5,cliente.getTelefono());

            flgOperacion = ps.executeUpdate();

            if (flgOperacion > 0){
                cn.commit();
                System.out.println(sql + " commit!!");
            }else{
                cn.rollback();
                System.out.printf(sql + "rollback!!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {   } //cierra conexion
        return  flgOperacion;
    }

    @Override
    public int eliminar(int id) {
        logger.info("eliminar por id!!");

        sql = "delete from cliente where id = ?";

        System.out.println(sql + "eliminar!!");

        try {
            con = new UtilSQL();
            cn = con.getConection();
            cn.setAutoCommit(false);

            ps = cn.prepareStatement(sql);
            flgOperacion = ps.executeUpdate();

            if (flgOperacion > 0){
                cn.commit();
            }else{
                cn.rollback();
            }
        }catch (Exception e){
            logger.error("eliminar" + e.getMessage());
        } finally { }

        return flgOperacion;
    }
}
