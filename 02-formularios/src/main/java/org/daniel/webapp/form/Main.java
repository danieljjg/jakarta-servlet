package org.daniel.webapp.form;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet("/registro")
public class Main extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        // getParameter used to when a parameter only have one value.
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String pais = req.getParameter("pais");



        // getParameterValues used to when a parameter have more than one value.
        String[] lenguajes = Optional.ofNullable(req.getParameterValues("lenguajes"))
                .orElse(new String[0]);
        String[] roles = Optional.ofNullable(req.getParameterValues("roles"))
                .orElse(new String[0]);


        String idioma = req.getParameter("idioma");
        String habilitar = req.getParameter("habilitar");
        String secret = req.getParameter("secret");

        // Error List to show in the url registro.
        List<String> errores = getErrores(username, password, email, pais, idioma, lenguajes, roles);

        PrintWriter out = resp.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("    <head>");
        out.println("        <meta charset = \"UTF-8\">");
        out.println("        <title>Resultado form</title>");
        out.println("    </head>");
        out.println("    <body>");

        out.println("        <h1>Resultado form</h1>");
        out.println("        <ul>");

        if(!errores.isEmpty()) {
            errores.forEach( error -> {
                out.println("<li>" + error + "</li>");
            } );
        }
        out.println("<p><a href=\"/formulario\"> Volver al formulario </a> </p>");

        out.println("            <li> Name: " + username + "</li>");
        out.println("            <li> Password: " + password + "</li>");
        out.println("            <li> Email: " + email + "</li>");
        out.println("            <li> Pais: " + pais + "</li>");

        out.println("            <li>Lenguajes: <ul>");
        Arrays.asList(lenguajes).forEach(lenguaje -> {
            out.println("                <li>" + lenguaje + "</li>");
        });
        out.println("            </ul></li>");

        out.println("            <li>Roles: <ul>");
        Arrays.asList(roles).forEach(role -> {
            out.println("                <li>" + role + "</li>");
        });
        out.println("            </ul></li>");

        out.println("            <li> idioma: " + idioma + "</li>");
        out.println("            <li> habilitar: " + habilitar + "</li>");
        out.println("            <li> secret: " + secret + "</li>");

        out.println("        </ul>");
        out.println("    </body>");
        out.println("</html>");

    }

    // METHOD WHERE CREATE AN ERRORS ABOUT THE FIELDS FORM.
    private List<String> getErrores(String username, String password, String email, String pais, String idioma, String[] lenguajes, String[] roles) {

        List<String> errores = new ArrayList<>();

        if(username == null || username.isBlank()) {
            errores.add("El username es requerido");
        }

        if(password == null || password.isBlank()) {
            errores.add("El password no puede ser vacio!");
        }

        if(email == null || !email.contains("@")) {
            errores.add("El email es requerido y debe tener un formato correcto");
        }

        if(pais == null || pais.equals("") || pais.equals(" ")) {
            errores.add("El Pais es requerido");
        }

        if(lenguajes == null || lenguajes.length == 0) {
            errores.add("Debes seleccionar al menos una tecnologia");
        }

        if(roles == null || roles.length == 0) {
            errores.add("Debes seleccionar al menos un role!");
        }

        if(idioma == null) {
            errores.add("Debe seleccionar un idioma!");
        }

        return errores;
    }
}