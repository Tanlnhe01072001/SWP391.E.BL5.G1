/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin;

import dal.blogDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import model.Blog;
import model.User;
//import org.apache.commons.fileupload.FileItem;
//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author hieupham
 */
@WebServlet(name = "BlogManager", urlPatterns = {"/blogmanager"})
@MultipartConfig
public class BlogManager extends HttpServlet {

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
            out.println("<title>Servlet BlogManager</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BlogManager at " + request.getContextPath() + "</h1>");
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

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("user?action=login");
            return;
        }
        if ((user.getIsAdmin() == null || !Boolean.parseBoolean(user.getIsAdmin()) && (user.getIsStoreStaff() == null || !Boolean.parseBoolean(user.getIsStoreStaff())))) {    //Nếu ko phải admin
            if (user.getIsStoreStaff() != null && Boolean.parseBoolean(user.getIsStoreStaff())) {  //Nếu là Store Staff
                response.sendRedirect("dashboard");
                return;
            }
            //Role còn lại: Client
            response.sendRedirect("home");
            return;
        }

        String page = "blog.jsp";   
        String action = request.getParameter("action");

        if ( action != null && !action.isBlank()) {
            switch (action) {
                case "insert" -> {
                    page = "blogadd.jsp";
                    request.getRequestDispatcher(page).forward(request, response);
                    return;
                }
                case "delete" -> {
                    String blog_id = request.getParameter("blog_id");
                    int id = Integer.parseInt(blog_id);
                    blogDAO dao = new blogDAO();
                    dao.deleteBlog(id);
                    response.sendRedirect("blogmanager");
                    return;
                }
                default -> {
                }
            }
        }

        List<Blog> blogs = new blogDAO().getBlog();
        request.setAttribute("blogs", blogs);
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

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("user?action=login");
            return;
        }
        if (user.getIsAdmin() == null || !Boolean.parseBoolean(user.getIsAdmin()) && user.getIsStoreStaff()== null || !Boolean.parseBoolean(user.getIsStoreStaff()) ) {    //Nếu ko phải admin
            
            //Role còn lại: Client
            response.sendRedirect("home");
            return;
        }
        String page = "blog.jsp";
        String action = request.getParameter("action");

        if (null != action && !action.isBlank()) {
            switch (action) {
                case "insertblog" -> {
                    String title = request.getParameter("title");
                    String summary = request.getParameter("summary");
                    String content = request.getParameter("content");
                    String images = "images/" + request.getParameter("images");

                    new blogDAO().insertBlog(new Blog(0, title, summary, content, new Date(), null, user, images));
                    response.sendRedirect("blogmanager");
                    return;
                }
                case "update" -> {
                    int blog_id = Integer.parseInt(request.getParameter("blog_id"));
                    request.setAttribute("blog", new blogDAO().getBlogByID(blog_id));
                    request.setAttribute("isUpdate", true);
                    page = "blogadd.jsp";
                    request.getRequestDispatcher(page).forward(request, response);
                    return;
                }
                case "updateblog" -> {
                    String title = request.getParameter("title");
                    String summary = request.getParameter("summary");
                    String content = request.getParameter("content");
                    String images = "images/" + request.getParameter("images");

                    int blog_id = Integer.parseInt(request.getParameter("blog_id"));
                    Blog blog = new blogDAO().getBlogByID(blog_id);
                    blog.setTitle(title);
                    blog.setSummary(summary);
                    blog.setContent(content);
                    blog.setUpdated_at(new Date());
                    blog.setImages(images);
                    new blogDAO().updateBlog(blog);
                    response.sendRedirect("blogmanager");
                    return;
                }
                default -> {
                }
            }

        }

        List<Blog> blogs = new blogDAO().getBlog();
        request.setAttribute("blogs", blogs);
        request.getRequestDispatcher(page).forward(request, response);
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
