/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin;

import dal.aboutDAO;
import model.About;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 *
 * @author ThangNPHE151263
 */
@WebServlet(name = "EditAbout", urlPatterns = {"/editAbout"})
public class EditAbout extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditAbout</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditAbout at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("user?action=login");
            return;
        }

        model.User user = (model.User) session.getAttribute("user");
        if (!user.getIsStoreStaff().equalsIgnoreCase("true")&& !user.getIsAdmin().equalsIgnoreCase("true")) {
            response.sendRedirect("home");
            return;
        }
        String aboutId = request.getParameter("id");
        aboutDAO dao = new aboutDAO();
        About about = dao.getAboutById(aboutId);
        request.setAttribute("aboutId", about.getAboutId());
        request.setAttribute("title", about.getTitle());
        request.setAttribute("img", about.getImg());
        request.setAttribute("content", about.getContent());
        request.getRequestDispatcher("editAbout.jsp").forward(request, response);
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
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("user?action=login");
            return;
        }
        model.User user = (model.User) session.getAttribute("user");

        aboutDAO dao = new aboutDAO();
        int aboutId = Integer.parseInt(request.getParameter("aboutId"));
        String title = request.getParameter("title");
        String img = request.getParameter("img");
        String content = request.getParameter("content");
        dao.updateAbout(title,content,img,aboutId);
        response.sendRedirect("aboutmanager");
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
