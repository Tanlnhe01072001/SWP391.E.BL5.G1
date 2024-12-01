/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin;

import dal.userDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.User;


@WebServlet(name = "CustomerManager", urlPatterns = {"/customermanager"})
public class CustomerManager  extends HttpServlet {

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
            out.println("<title>Servlet CustomerManager</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CustomerManager at " + request.getContextPath() + "</h1>");
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
      
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
         if (user == null) {
            response.sendRedirect("user?action=login");
            return;
        }
        if (user.getIsAdmin() == null || !Boolean.parseBoolean(user.getIsAdmin())) {    //Nếu ko phải admin
            if (user.getIsStoreStaff() != null && Boolean.parseBoolean(user.getIsStoreStaff())) {  //Nếu là Store Staff
                response.sendRedirect("dashboard");
                return;
            }
            //Role còn lại: Client
            response.sendRedirect("home");
            return;
        }
        String page = "customer.jsp";
        String action = request.getParameter("action");

        if (null == action) {
            userDAO dao = new userDAO();
            List<User> user1 = dao.getUser();
            request.setAttribute("user", user1);
            page = "customer.jsp";
        } else {
            switch (action) {
                case "delete" -> {
                    String user_id = request.getParameter("user_id");
                    int id = Integer.parseInt(user_id);
                    userDAO dao = new userDAO();
                    dao.deleteUser(id);
                    response.sendRedirect("customermanager");
                    return;
                }
                case "ban" -> {
                    int banUserId = Integer.parseInt(request.getParameter("user_id"));
                    userDAO dao = new userDAO();
                    dao.banUser(banUserId);
                    response.sendRedirect("customermanager");
                    return;
                }
                case "unban" -> {
                    String user_id = request.getParameter("user_id");
                    int id = Integer.parseInt(user_id);
                    userDAO dao = new userDAO();
                    dao.unbanUser(id);
                    response.sendRedirect("customermanager");
                    return;
                }
                default -> {
                }
            }
        }
        request.getRequestDispatcher(page).forward(request, response);
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String page = "";
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String user_id = request.getParameter("user_id");
        String isAdmin = request.getParameter("permission");
        String isStoreStaff = request.getParameter("storeStaffPermission");
        String adminReason = request.getParameter("adminReason");
        int id = Integer.parseInt(user_id);
        userDAO dao = new userDAO();
        dao.setAdmin(id, isAdmin, isStoreStaff, adminReason);
        response.sendRedirect("customermanager");
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
