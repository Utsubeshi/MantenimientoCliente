package com.cliente.servlets;

import com.cliente.bean.Cliente;
import com.cliente.services.ClienteService;
import com.cliente.servicesImpl.ClienteServicesImpl;
import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@javax.servlet.annotation.WebServlet(name = "ClienteServlet")
public class ClienteServlet extends javax.servlet.http.HttpServlet {

    private static Logger logger = Logger.getLogger(ClienteServlet.class.getName());

    private ClienteService clienteService;
    private Cliente cliente;
    private int flgOperacion = 0;
    private String mensaje = null;
    private HttpSession session;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");

        if (accion != null) {
            switch (accion) {
                case "insertar":
                    insertar(request, response );
                    break;
                case "buscar":
                    buscar(request, response);
                    break;
                case "obtenerPorId":
                    obtenerPorId(request, response);
                    break;
                case "actualizar":
                    actualizar(request, response);
                    break;
                case "eliminar":
                    eliminar(request, response);
                    break;
            }
        }
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) {
    }

    private void obtenerPorId(HttpServletRequest request, HttpServletResponse response) {
        logger.info("obtenerPorId");

        int id = Integer.parseInt(request.getParameter("id") == null ? "0" : request.getParameter("id"));

        try{
            clienteService = new ClienteServicesImpl();

            cliente = clienteService.ObtenerPorId(id);
            session = request.getSession();
            session.removeAttribute("msgPostOperacion");
            session.removeAttribute("lstClientes");
            session.removeAttribute("msgListado");
            session.removeAttribute("clienteActualizar");
            session.setAttribute("clienteActualizar" , cliente);
            response.setContentType("clienteMnt.jsp");
        }catch (Exception e){
            logger.error("obtener por id" + e.getMessage());
        }
    }

    private void buscar(HttpServletRequest request, HttpServletResponse response) {
        logger.info("buscar");

        String razonSocial = request.getParameter("razonSocial") == null ?  "" : request.getParameter("razonSocial");

        logger.info(razonSocial);

        try {
            session = request.getSession();
            session.removeAttribute("lstCliente");
            session.removeAttribute("msgListado");
            session.removeAttribute("clienteActualizar");
            if(session.getAttribute("msgPostOperacion") != null){
                razonSocial = "";
            }
            clienteService = new ClienteServicesImpl();
            List<Cliente> lstCliente = clienteService.BuscarCliente(razonSocial);
            if(lstCliente.size() > 0){
                session.setAttribute("lstCliente",lstCliente);
            } else{
                mensaje = "no existen cliente.";
                session.setAttribute("msgListado", mensaje);
            }
            response.setContentType("clienteLst.jsp");

        }catch (Exception e){
            logger.error("buscar" + e.getMessage());
        }
    }

    private void actualizar(HttpServletRequest request, HttpServletResponse response) {
    }

    private void insertar(HttpServletRequest request, HttpServletResponse response) {
        String razonSocial = request.getParameter("razonSocial") == null ? "" : request.getParameter("razonSocial");
        String ruc = request.getParameter("ruc") == null ? "" : request.getParameter("ruc");
        String direccion = request.getParameter("direccion") == null ? "" : request.getParameter("direccion");
        String telefono = request.getParameter("telefono") == null ? "" : request.getParameter("telefono");

        try {
            cliente = new Cliente();
            cliente.setRazonSocial(razonSocial);
            cliente.setRuc(ruc);
            cliente.setDireccion(direccion);
            cliente.setTelefono(telefono);

            clienteService = new ClienteServicesImpl();

            flgOperacion = clienteService.insertarCliente(cliente);

            if(flgOperacion == 0){
                mensaje = "Cliente insertado correctamente.";
            }else{
                mensaje = "Ha ocurido un error.";
            }
        }catch (Exception e){
            logger.error("insertar" + e.getLocalizedMessage());
        }

    }
}
