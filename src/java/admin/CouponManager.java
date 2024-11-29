/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin;

import dal.couponDAO;
import dal.couponTypeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Coupon;
import model.CouponType;

/**
 *
 * @author Putaa
 */
@WebServlet(name = "CouponManager", urlPatterns = {"/couponmanager"})
public class CouponManager extends HttpServlet {

    private couponDAO couponDAO = new couponDAO();
    private couponTypeDAO couponTypeDAO = new couponTypeDAO();

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
            out.println("<title>Servlet CouponManager</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CouponManager at " + request.getContextPath() + "</h1>");
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
        if (!user.getIsStoreStaff().equalsIgnoreCase("true")&& !user.getIsAdmin().equalsIgnoreCase("true") ) {
            response.sendRedirect("home");
            return;
        }
        
        String action = request.getParameter("action");
        List<Coupon> coupons = null;
        List<CouponType> couponTypes = null;

        if ("list".equals(action)) {
            try {
                coupons = couponDAO.getAllCoupons();
            } catch (Exception ex) {
                Logger.getLogger(CouponManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("coupons", coupons);
            request.getRequestDispatcher("listCoupon.jsp").forward(request, response);
        } else if ("delete".equals(action)) {
            int couponId = Integer.parseInt(request.getParameter("couponId"));
            try {
                couponDAO.deleteCoupon(couponId);
            } catch (Exception ex) {
                Logger.getLogger(CouponManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.sendRedirect("couponmanager?action=list");
        } else if ("generate".equals(action)) {
            try {
                couponTypes = couponTypeDAO.getAllCouponTypes();
            } catch (Exception ex) {
                Logger.getLogger(CouponManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("couponTypes", couponTypes);
            request.getRequestDispatcher("generateCoupon.jsp").forward(request, response);
        } else {
            response.sendRedirect("couponmanager?action=list");
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
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("user?action=login");
            return;
        } 
        model.User user = (model.User) session.getAttribute("user");
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            int couponTypeId = Integer.parseInt(request.getParameter("couponTypeId"));
            String startDateStr = request.getParameter("startDate");
            String endDateStr = request.getParameter("endDate");
            int usageLimit = Integer.parseInt(request.getParameter("usageLimit"));

            Date startDate = parseDate(startDateStr);
            Date endDate = parseDate(endDateStr);

            if (startDate != null && endDate != null) {
                String code = generateCouponCode();
                Coupon coupon = new Coupon(0, startDate, endDate, usageLimit, couponTypeId, code, user.getUser_id());

                try {
                    couponDAO.addCoupon(coupon);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                response.sendRedirect("couponmanager?action=list");
            } else {
                request.setAttribute("errorMessage", "Invalid date format. Please use yyyy-MM-dd.");
                request.getRequestDispatcher("generateCoupon.jsp").forward(request, response);
            }
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

    private String generateCouponCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(characters.length());
            code.append(characters.charAt(index));
        }
        return code.toString();
    }

    private Date parseDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (date != null) ? new Date(date.getTime()) : null;
    }
}
