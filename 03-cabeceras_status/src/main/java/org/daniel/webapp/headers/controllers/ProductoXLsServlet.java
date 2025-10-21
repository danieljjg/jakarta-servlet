package org.daniel.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.daniel.webapp.headers.models.Producto;
import org.daniel.webapp.headers.services.ProductoService;
import org.daniel.webapp.headers.services.ProductoServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet({"/productos.xls", "/productos.html", "/productos"})
public class ProductoXLsServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductoService productoService = new ProductoServiceImpl();
        List<Producto> productos = productoService.getAllProducts();

        resp.setContentType("text/html");

        String servletPath = req.getServletPath();
        boolean isXls = servletPath.endsWith(".xls");

        if(isXls) {
            resp.setContentType("application/vnd.ms-excel");
            resp.setHeader("Content-Disposition", "attachment;filename=productos.xls");
        }

        PrintWriter out = resp.getWriter();

        if(!isXls) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("    <head>");
            out.println("        <meta charset = \"UTF-8\">");
            out.println("        <title>Lista de Productos</title>");
            out.println("    </head>");
            out.println("    <body>");
            out.println("        <h1>Lista de Productos</h1>");
        }


        out.println("        <table>");

        out.println("            <tr>");
        out.println("                <th>Id</th>");
        out.println("                <th>Nombre</th>");
        out.println("                <th>Tipo</th>");
        out.println("                <th>Precio</th>");
        out.println("            </tr>");

        productos.forEach(p -> {
            out.println("            <tr>");
            out.println("                <td>" + p.getId() + "</td>");
            out.println("                <td>" + p.getNombre() + "</td>");
            out.println("                <td>" + p.getTipo() + "</td>");
            out.println("                <td>" + p.getPrecio() + "</td>");
            out.println("            </tr>");
        });

        out.println("        </table>");

        if(!isXls) {
            out.println("    </body>");
            out.println("</html>");
        }

    }
}
