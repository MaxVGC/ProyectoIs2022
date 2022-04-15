/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import AddOns.Salas;
import Serializar.CurrentSalas;
import com.google.gson.Gson;
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
public class MostrarSalas extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            ArrayList<Salas> aux = new CurrentSalas().getCurrentSalasList();
            String aux2 = "";
            //String div_crear = "<div id=\"room-create\"> <i class=\"fa-solid fa-plus\" style=\"font-size:50px;\"></i> <div id=\"span\"> <span style=\"color: white;\">Crear sala</span> </div></div>";
            if (aux!=null) {
                for (int i = 0; i < aux.size(); i++) {
                    aux2 = aux2 + "<div class=\"room\"> <div class=\"background\"> <div class=\"room-card\"> <img src=\"../img/Plantilla_logo3.png\" style=\"width: 70%;\" alt=\"\"> </div><img id=\"agua-icon\" class=\"card-icon\" src=\"../img/Agua100x100.png\"> <img id=\"fuego-icon\" class=\"card-icon\" src=\"../img/fuego100x100.png\"> <img id=\"hielo-icon\" class=\"card-icon\" src=\"../img/Hielo100x100.png\"> </div><div class=\"content\"> <span class=\"roomName\">" + aux.get(i).getNombre() + "</span><span style=\"color:white\">"+aux.get(i).getJuego()+"</span> <marquee class=\"room-creator\">Creada por " + aux.get(i).getUsuarios().get(0).getNickname() + "</marquee></div></div>";
                }
            }
            out.write( aux2);
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MostrarSalas.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MostrarSalas.class.getName()).log(Level.SEVERE, null, ex);
        }
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

//if (aux != null) {
//                for (int i = 0; i < aux.size(); i++) {
//                    out.write(aux.get(i).getNombre());
//                    out.write("  ");
//                    out.write(aux.get(i).getJuego());
//                    out.write("<br>");
//                    for (int j = 0; j < aux.get(i).getUsuarios().size(); j++) {
//                        out.write(aux.get(i).getUsuarios().get(j).getNickname());
//                        out.write("<br>");
//                    }
//                    out.write("<br>");
//                }
//            }
