/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin;

import dal.billDAO;
import dal.categoryDAO;
import dal.productDAO;
import dal.userDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Bill;
import model.Category;
import model.User;

/**
 *
 * @author hieupham
 */
@WebServlet(name = "Dashboard", urlPatterns = {"/dashboard"})
public class Dashboard extends HttpServlet {

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
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user == null) {
                response.sendRedirect("user?action=login");
                return;
            }
            if ((user.getIsAdmin() == null || !Boolean.parseBoolean(user.getIsAdmin()))
                    && (user.getIsStoreStaff() == null || !Boolean.parseBoolean(user.getIsStoreStaff()))) {    //Nếu ko phải admin và Store Staff
                //Role còn lại: Client
                response.sendRedirect("home");
                return;
            }
            
            productDAO dao = new productDAO();
            billDAO bdao = new billDAO();
            categoryDAO cdao = new categoryDAO();
            userDAO udao = new userDAO();
            //pie chart
            List<Category> categoryList = cdao.getCategoryCounts();
            request.setAttribute("categoryList", categoryList);


            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");
            String message1 = null;
            String message2 = null;
            if (startDate != null && endDate != null) {  // Chỉ xử lý khi có tìm kiếm
                if (startDate.isEmpty()) {
                    message2 = "Chưa chọn ngày bắt đầu";
                    session.setAttribute("message2", message2);
                }
                if (endDate.isEmpty()) {
                    message2 = "Chưa chọn ngày kết thúc";
                    session.setAttribute("message2", message2);
                }
                if (startDate.isEmpty() && endDate.isEmpty()) {
                    message2 = "Chọn ngày bắt đầu và kết thúc";
                    session.setAttribute("message2", message2);
                }
                List<Bill> bills = bdao.getBillBetweenDates(startDate, endDate);
                if (bills != null && !bills.isEmpty()) {

                    message1 = "Tìm kiếm thành công từ " + startDate + " đến " + endDate;
                    session.setAttribute("message1", message1);
                } else {

                    message2 = "Không có đơn hàng " + startDate + " đến " + endDate;
                    session.setAttribute("message2", message2);
                }
                request.setAttribute("bills", bills);
            }

            int count = dao.CountProduct();
            int countuser = udao.CountUser();
            int countbill = bdao.CountBill();
            int countproductlow = dao.CountProductLow();
            request.setAttribute("product", count);
            request.setAttribute("user", countuser);
            request.setAttribute("bill", countbill);
            request.setAttribute("low", countproductlow);

            List<Object[]> monthlyTotals = bdao.getTotalBillAmountByMonth();
            request.setAttribute("monthlyTotals", monthlyTotals);
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("404.jsp");
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
