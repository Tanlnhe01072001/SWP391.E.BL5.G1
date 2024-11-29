/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.userDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ResetPass", urlPatterns = {"/resetPass"})
public class ResetPass extends HttpServlet {

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
            out.println("<title>Servlet ResetPass</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ResetPass at " + request.getContextPath() + "</h1>");
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
        String email = (String) request.getSession().getAttribute("email");
        String code = (String) request.getSession().getAttribute("code");
        String enteredCode = request.getParameter("code");
        String newPass = request.getParameter("Pass");
        String rePass = request.getParameter("Repass");

// Định nghĩa biểu thức chính quy cho mật khẩu
        String passwordRegex = "^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{6,}$";

        HttpSession session = request.getSession();
        userDAO dao = new userDAO();

        if (code.equals(enteredCode)) {
            if (newPass.equals(rePass)) {
                // Kiểm tra mật khẩu mới theo biểu thức chính quy
                if (!newPass.matches(passwordRegex)) {
                    session.setAttribute("msg", "Mật khẩu phải có ít nhất 6 ký tự, bao gồm ít nhất một chữ cái viết hoa và một chữ số");
                    request.getRequestDispatcher("resetPassword.jsp").forward(request, response);
                    return;
                }

                // Thay đổi mật khẩu
                dao.updatePasswordbyEmail(email, newPass);
                // Nếu cập nhật thành công
                session.setAttribute("signupMessage", "Mật khẩu đã được thay đổi thành công!");
                response.sendRedirect("login.jsp");
            } else {
                session.setAttribute("msg", "Mật khẩu nhập lại không khớp!");
                response.sendRedirect("resetPassword.jsp");
            }
        } else {
            session.setAttribute("msg", "Mã xác nhận không đúng!");
            response.sendRedirect("resetPassword.jsp");
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
