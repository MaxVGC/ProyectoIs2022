/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import AddOns.Core;
import AddOns.Usuarios;
import Serializar.DeserializarUsuario;
import Serializar.SerializarUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlo
 */
public class ValidarUsuario extends HttpServlet {

    DeserializarUsuario aux = null;
    SerializarUsuario Su = null;

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String nick = request.getParameter("nick");
            String token = request.getParameter("sessionid");
            String url = request.getParameter("url");

            aux = new DeserializarUsuario();
            ArrayList<Usuarios> usuarios = aux.getCurrentUsuariosList();
            int nick_aux = 0;
            int token_aux = 0;

            if (usuarios != null) {
                for (int i = 0; i < usuarios.size(); i++) {
                    if (usuarios.get(i).getNickname().equals(nick)) {
                        nick_aux = 1;
                    }
                    if (usuarios.get(i).getSessionId().equals(token)) {
                        token_aux = 1;
                    }
                }
            }

            if (url != null && nick_aux == 0 && token_aux == 0) {
                if (usuarios != null) {
                    Su = new SerializarUsuario();
                    Usuarios aux3 = new Usuarios();
                    aux3.setNickname(nick);
                    aux3.setSessionId(token);
                    usuarios.add(aux3);
                    Su.setCurrentUsuariosList(usuarios);
                    usuarios = aux.getCurrentUsuariosList();
                } 
            }
            if (nick_aux == 0 && token_aux == 0) {
                out.print("null");
            } else if (nick_aux == 1) {
                out.print("Usuario no disponible");
            } else if (token_aux == 1) {
                out.print("Ya se encuentra una session abierta");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ValidarUsuario.class.getName()).log(Level.SEVERE, null, ex);
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
