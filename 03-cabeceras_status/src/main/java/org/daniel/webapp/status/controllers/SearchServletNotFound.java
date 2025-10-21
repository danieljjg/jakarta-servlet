package org.daniel.webapp.status.controllers;

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

import java.util.Optional;

@WebServlet("/status-search")
public class SearchServletNotFound extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductoService service = new ProductoServiceImpl();
        Optional<Producto> productoOptional;
        String nombre = req.getParameter("producto");

        productoOptional = service.getAllProducts().stream().filter(
                p -> p.getNombre().equals(nombre)
        ).findFirst();

        if (productoOptional.isPresent()) {
            
            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("    <head>");
            out.println("        <meta charset = \"UTF-8\">");
            out.println("        <title>Producto encontrado</title>");
            out.println("    </head>");
            out.println("    <body>");
            out.println("        <h1>Producto encontrado</h1>");
            out.println("        <h3>Hemos encontrado el producto " + productoOptional.get().getNombre() + "</h3>");
            out.println("    </body>");
            out.println("</html>");
            
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Lo sentimos no se encontro el producto " + nombre);
        }
    }
}
