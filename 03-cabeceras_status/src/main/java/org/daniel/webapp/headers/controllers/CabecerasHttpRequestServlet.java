package org.daniel.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/cabeceras-request")
public class CabecerasHttpRequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");


        String metodoHttp = req.getMethod();
        String requestUri = req.getRequestURI();
        String requestUrl = req.getRequestURL().toString();
        String contextPath = req.getContextPath();
        String servletPath = req.getServletPath();

        // INFORMACIÓN LOCAL DEL SERVIDOR
        String ip = req.getLocalAddr();
        int port = req.getLocalPort();

        String scheme = req.getScheme();
        String host = req.getHeader("host");

        StringBuilder url = new StringBuilder()
                .append(scheme)
                .append("://")
                .append(host)
                .append(contextPath)
                .append(servletPath);

        String url2 = scheme + "://" + ip + ":" + port + contextPath + servletPath;

        // INFORMACIÓN DEL CLIENTE
        String ipCliente = req.getRemoteAddr();

        //TODAS LAS CABECERAS
        Enumeration<String> headerNames = req.getHeaderNames();

        PrintWriter out = resp.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("    <head>");
        out.println("        <meta charset = \"UTF-8\">");
        out.println("        <title>Cabeceras HTTP</title>");
        out.println("    </head>");
        out.println("    <body>");
        out.println("        <h1>Cabeceras HTTP</h1>");

        out.println("        <ul>");
        out.println("            <li>Metodo http: " + metodoHttp + "</li>");
        out.println("            <li>Request uri: " + requestUri + "</li>");
        out.println("            <li>Request url: " + requestUrl + "</li>");
        out.println("            <li>Context path: " + contextPath + "</li>");
        out.println("            <li>Servlet path: " + servletPath + "</li>");
        out.println("            <li>Ip local: " + ip + "</li>");
        out.println("            <li>Ip cliente: " + ipCliente + "</li>");
        out.println("            <li>Puerto local: " + port + "</li>");
        out.println("            <li>Scheme: " + scheme + "</li>");
        out.println("            <li>Host: " + host + "</li>");
        out.println("            <li>Url 1: " + url + "</li>");
        out.println("            <li>Url 2: " + url2 + "</li>");

        out.println("<h3> CABECERAS : </h3>");
        while(headerNames.hasMoreElements()) {
            String cabecera = headerNames.nextElement();

            out.println("<li>" + cabecera.substring(0, 1).toUpperCase() + cabecera.substring(1) + ": " + req.getHeader(cabecera) + "</li>");
        }

        out.println("        </ul>");

        out.println("    </body>");
        out.println("</html>");
        
    }
}