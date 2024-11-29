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
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import model.UserC;

/**
 *
 * @author Admin
 */
@WebServlet(name = "User", urlPatterns = {"/user"})
public class User extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");
        if (action.equals("login")) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        if (action.equals("checkLogin")) {
            String user_email = request.getParameter("user_email");
            String user_pass = request.getParameter("user_pass");
            String remember = request.getParameter("remember");
            userDAO dao = new userDAO();
            model.User user = dao.checkUser(user_email, user_pass);
            if (user == null) {
                HttpSession session = request.getSession();
                session.setAttribute("error_exist", "Tài khoản không tồn tại !");
                request.getRequestDispatcher("user?action=login").forward(request, response);
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setAttribute("loginMessage", "Đăng nhập thành công!");
                Cookie email = new Cookie("email", user_email);
                Cookie pass = new Cookie("pass", user_pass);
                Cookie rem = new Cookie("remember", remember);
                if (remember != null) {
                    email.setMaxAge(60 * 60 * 24 * 30);
                    pass.setMaxAge(60 * 60 * 24 * 30);
                    rem.setMaxAge(60 * 60 * 24 * 30);
                } else {
                    email.setMaxAge(0);
                    pass.setMaxAge(0);
                    rem.setMaxAge(0);
                }
                response.addCookie(email);
                response.addCookie(pass);
                response.addCookie(rem);
                response.sendRedirect("home");
            }
        }

        if (action.equals("logout")) {
            HttpSession session = request.getSession();
            session.removeAttribute("user");
            session.setAttribute("logoutMessage", "Đăng xuất thành công!");
            response.sendRedirect("home");
        }

        if (action.equals("myaccount")) {
            try {
                HttpSession session = request.getSession();
                model.User user = (model.User) session.getAttribute("user");
                if (user != null) {
                    int user_id = user.getUser_id();
                    billDAO dao = new billDAO();
                    List<model.Bill> bill = dao.getBillByID(user_id);
                    request.setAttribute("bill", bill);
                    request.getRequestDispatcher("my-account.jsp").forward(request, response);
                } else {
                    response.sendRedirect("user?action=myaccount");
                }
            } catch (Exception e) {
                response.sendRedirect("user?action=myaccount");
            }
        }
        if (action.equals("showdetail")) {
            String bill_id = request.getParameter("bill_id");
            int id = Integer.parseInt(bill_id);
            billDAO dao = new billDAO();
            List<BillDetail> detail = dao.getDetail(id);
            request.setAttribute("detail", detail);
            request.getRequestDispatcher("billdetail.jsp").forward(request, response);
        }
        if (action.equals("updateinfo")) {
            HttpSession session = request.getSession();
            model.User user = (model.User) session.getAttribute("user");

            if (user != null) {
                try {
                    String user_name = request.getParameter("user_name");
                    String dateOfBirth = request.getParameter("dateOfBirth");
                    String address = request.getParameter("address");
                    String phoneNumber = request.getParameter("phoneNumber");

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date dob = sdf.parse(dateOfBirth);
                    Date currentDate = new Date();

                    if (dob.after(currentDate)) {
                        session.setAttribute("error_dob", "Ngày sinh không được lớn hơn ngày hiện tại");
                        request.getRequestDispatcher("my-account.jsp").forward(request, response);
                        return;
                    }
                    String phoneNumberPattern = "\\d{10}";
                    if (phoneNumber == null || !phoneNumber.matches(phoneNumberPattern)) {
                        session.setAttribute("error_dob", "Số điện thoại không hợp lệ");
                        request.getRequestDispatcher("my-account.jsp").forward(request, response);
                        return;
                    }

                    int user_id = user.getUser_id();
                    userDAO dao = new userDAO();
                    dao.updateUser2(user_id, user_name, dateOfBirth, address, phoneNumber);

                    model.User updatedUser = new model.User(user.getUser_id(), user_name, user.getUser_email(), user.getIsAdmin(), dateOfBirth, address, phoneNumber, user.isBanned(), user.getAdminReason(), user.getIsStoreStaff());
                    session.setAttribute("user", updatedUser);
                    session.setAttribute("updateMessage", "Cập nhật thông tin thành công!");
                    response.sendRedirect("user?action=myaccount");
                } catch (ParseException e) {

                    session.setAttribute("error_dob", "Ngày sinh không hợp lệ");
                    request.getRequestDispatcher("my-account.jsp").forward(request, response);
                } catch (Exception e) {

                    e.printStackTrace();
                    response.sendRedirect("user?action=myaccount");
                }
            } else {
                response.sendRedirect("user?action=myaccount");
            }
        }
        if (action.equals("signup")) {
            HttpSession session = request.getSession();
            userDAO da = new userDAO();
            String name = request.getParameter("user_name");
            String email = request.getParameter("user_email");
            String pass = request.getParameter("user_pass");
            String repass = request.getParameter("re_pass");

            String passwordRegex = "^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{6,}$";
            if (!pass.matches(passwordRegex)) {
                session.setAttribute("error_ban", "Mật khẩu phải có ít nhất 6 ký tự, bao gồm ít nhất một chữ cái viết hoa và một chữ số");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }

            if (!pass.equals(repass)) {
                session.setAttribute("error_rePass", "Vui lòng nhập lại mật khẩu cho đúng");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            } else {
                model.User a = da.checkAcc(email);
                if (a != null) {
                    session.setAttribute("msg", "Email đã tồn tại");
                    request.getRequestDispatcher("register.jsp").forward(request, response);

                } else {
                    // da.signup(name, email, repass);
                    SendEmailCode sm = new SendEmailCode();
                    String code = sm.getRandom();
                    UserC userc = new UserC(code, email);
                    boolean test = sm.sendEmail1(userc);
                    if (test == true && userc != null) {
                        session.setAttribute("userc", userc);
                        session.setAttribute("name", name);
                        session.setAttribute("email", email);
                        session.setAttribute("pass", pass);
                        response.sendRedirect("verify.jsp");
                        return;
                    }

                }
            }
        }
        if (action.equals("updatepassword")) {
            HttpSession session = request.getSession();
            model.User user = (model.User) session.getAttribute("user");

            if (user != null) {
                try {
                    // Retrieve form parameters
                    String currentPassword = request.getParameter("current_password");
                    String newPassword = request.getParameter("new_password");
                    String confirmNewPassword = request.getParameter("confirm_new_password");

                    // Basic validation
                    if (currentPassword == null || newPassword == null || confirmNewPassword == null) {
                        session.setAttribute("error_dob", "Tất cả các trường phải được điền đầy đủ.");
                        response.sendRedirect("user?action=myaccount");
                        return;
                    }

                    String passwordRegex = "^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{6,}$";
                    if (!newPassword.matches(passwordRegex)) {
                        session.setAttribute("error_dob", "Mật khẩu phải có ít nhất 6 ký tự, bao gồm ít nhất một chữ cái viết hoa và một chữ số");
                        request.getRequestDispatcher("user?action=myaccount").forward(request, response);
                        return;
                    }

                    if (!confirmNewPassword.matches(passwordRegex)) {
                        session.setAttribute("error_dob", "Mật khẩu phải có ít nhất 6 ký tự, bao gồm ít nhất một chữ cái viết hoa và một chữ số");
                        request.getRequestDispatcher("user?action=myaccount").forward(request, response);
                        return;
                    }

                    if (!newPassword.equals(confirmNewPassword)) {
                        session.setAttribute("error_dob", "Mật khẩu mới và xác nhận mật khẩu không khớp.");
                        response.sendRedirect("user?action=myaccount");
                        return;
                    }

                    userDAO dao = new userDAO();
                    if (!dao.checkPassword(user.getUser_id(), currentPassword)) {
                        session.setAttribute("error_dob", "Mật khẩu hiện tại không chính xác.");
                        response.sendRedirect("user?action=myaccount");
                        return;
                    }

                    // Update the password
                    dao.updatePassword(user.getUser_id(), newPassword);

                    // Inform the user of success
                    session.setAttribute("updateMessage", "Mật khẩu đã được thay đổi thành công!");
                    response.sendRedirect("user?action=myaccount");

                } catch (Exception e) {
                    e.printStackTrace();
                    session.setAttribute("error_message", "Đã xảy ra lỗi khi cập nhật mật khẩu.");
                    response.sendRedirect("user?action=myaccount");
                }
            } else {
                response.sendRedirect("user?action=myaccount");
            }
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
