/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author juanh
 */
@WebServlet(name = "Agendar", urlPatterns = {"/agendar"})
public class Agendar extends HttpServlet {

    private static final String URL = "jdbc:mysql://localhost:3306/tres trigos"; 
    private static final String USER = "root";
    private static final String PASSWORD = "12345678";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idPersona = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String fechaPedido = request.getParameter("fecha");
        String correo = request.getParameter("email");
        String categoria = request.getParameter("categoria");
        String productos = request.getParameter("producto");

        System.out.println(nombre + fechaPedido);

        // Conexión a la base de datos
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
                String sql = "INSERT INTO clientes (ID_PERSONA, NOMBRE, FECHA_PEDIDO, CORREO, CATEGORIA, PRODUCTO) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, Integer.parseInt(idPersona));
                statement.setString(2, nombre);
                statement.setString(3, fechaPedido);
                statement.setString(4, correo);
                statement.setString(5, categoria);
                statement.setString(6, productos);

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    response.getWriter().write("Pedido agendado con éxito.");
                } else {
                    response.getWriter().write("Hubo un problema al agendar el pedido.");
                }
            }
        } catch (ClassNotFoundException e) {
            response.getWriter().write("Error: Driver de MySQL no encontrado.");
            e.printStackTrace();
        } catch (SQLException e) {
            response.getWriter().write("Error al conectar con la base de datos: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            response.getWriter().write("Ocurrió un error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    }

