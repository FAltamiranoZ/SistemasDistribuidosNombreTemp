/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Polupero
 */
public class Empaquetador extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8"); Es tipo Json y esta definido abajo
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//Esto es el envío de un solo objeto, la parte no comentada es para enviar arreglos
//            JSONObject jsonResponse = new JSONObject();
//            jsonResponse.put("field1", "value1"); //Field1 es el nombre del atributo, a este llamo después
//            jsonResponse.put("field2", "value2");
//            jsonResponse.put("field3", "value3");
//            response.setContentType("application/json");
//            System.out.println(jsonResponse.toString());//Nomas por ver
//            out.write(jsonResponse.toString());
            JSONObject jsonResponse = new JSONObject();
            JSONArray transformers = new JSONArray();
            for (int i=0 ; i<10; i++) {
                JSONObject transformer = new JSONObject();
                transformer.put("name" , "Decepticon" + i);
                transformer.put("id" , i);
                transformers.add(transformer);
            }
            jsonResponse.put("transformers", transformers); //el primer transformers es el nombre del atributo y el que llamo después
            response.setContentType("application/json");
            System.out.println(jsonResponse.toString());
            out.write(jsonResponse.toString());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
