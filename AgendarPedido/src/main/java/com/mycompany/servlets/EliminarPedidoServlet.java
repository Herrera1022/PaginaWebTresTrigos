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
@WebServlet(name = "EliminarPedido", urlPatterns = {"/eliminarPedido"})
public class EliminarPedidoServlet extends HttpServlet {

    private static final String URL = "jdbc:mysql://localhost:3306/tres trigos";
    private static final String USER = "root";
    private static final String PASSWORD = "12345678";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // HTML con estilo en línea
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Eliminar Pedido</title>");
        out.println("<style>");
        out.println("body { font-family: 'Arial', sans-serif; background-color: #000; color: #fff; margin: 0; padding: 0; display: flex; justify-content: center; align-items: center; height: 100vh; }");
        out.println(".container-eliminar { background-color: #222; padding: 30px; border-radius: 8px; box-shadow: 0 4px 6px rgba(255, 255, 255, 0.1); text-align: center; }");
        out.println("input[type='text'] { padding: 12px; margin: 10px 0; border: 1px solid #555; border-radius: 5px; font-size: 16px; color: #fff; background-color: #333; }");
        out.println("button { padding: 12px; background-color: #ffc800; color: #fff; border: none; border-radius: 5px; font-size: 16px; cursor: pointer; transition: background-color 0.3s; }");
        out.println("button:hover { background-color: #0056b3; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container-eliminar'>");
        out.println("<h2>Eliminar Pedido</h2>");
        out.println("<form action='eliminarPedido' method='post'>");
        out.println("<label for='id'>Ingrese el ID del pedido a eliminar:</label>");
        out.println("<input type='text' name='id' id='id' required placeholder='ID del pedido'>");
        out.println("<button type='submit'>Eliminar</button>");
        out.println("</form>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener el ID del pedido que el usuario quiere eliminar
        String id = request.getParameter("id");

        // Conexión a la base de datos
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
                String sql = "DELETE FROM clientes WHERE ID_PERSONA = ?";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, Integer.parseInt(id));

                int rowsDeleted = statement.executeUpdate();
                if (rowsDeleted > 0) {
                    response.getWriter().write("Pedido eliminado con éxito.");
                } else {
                    response.getWriter().write("No se encontró el pedido con esa identificación.");
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
