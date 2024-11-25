/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author juanh
 */
@WebServlet("/showOrders")
public class ShowOrdersServlet extends HttpServlet {

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
        out.println("<title>Mostrar Pedidos</title>");
        out.println("<style>");
        out.println("body { font-family: 'Arial', sans-serif; background-color: #000; color: #fff; margin: 0; padding: 0; display: flex; justify-content: center; align-items: center; height: 100vh; }");
        out.println(".table-container { width: 90%; max-width: 800px; margin: auto; background-color: #222; padding: 20px; border-radius: 8px; box-shadow: 0 4px 6px rgba(255, 255, 255, 0.1); }");
        out.println("table { width: 100%; border-collapse: collapse; margin: 20px 0; font-size: 16px; text-align: center; }");
        out.println("th, td { padding: 12px; border: 1px solid #444; }");
        out.println("th { background-color: #ffc800; color: #fff; }");
        out.println("td { background-color: #222; color: #fff; }");
        out.println("h2 { text-align: center; color: #fff; margin-bottom: 20px; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");

        // Contenido de la página
        out.println("<div class='table-container'>");
        out.println("<h2>Pedidos Realizados</h2>");
        out.println("<table>");
        out.println("<tr><th>ID</th><th>Nombre</th><th>Fecha</th><th>Correo</th><th>Categoría</th><th>Producto</th></tr>");

        // Aquí se deben incluir los datos de la tabla
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
                String sql = "SELECT * FROM clientes";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    out.println("<tr>");
                    out.println("<td>" + rs.getInt("ID_PERSONA") + "</td>");
                    out.println("<td>" + rs.getString("NOMBRE") + "</td>");
                    out.println("<td>" + rs.getString("FECHA_PEDIDO") + "</td>");
                    out.println("<td>" + rs.getString("CORREO") + "</td>");
                    out.println("<td>" + rs.getString("CATEGORIA") + "</td>");
                    out.println("<td>" + rs.getString("PRODUCTO") + "</td>");
                    out.println("</tr>");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<tr><td colspan='6'>Error al cargar los datos</td></tr>");
        }

        out.println("</table>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

}
