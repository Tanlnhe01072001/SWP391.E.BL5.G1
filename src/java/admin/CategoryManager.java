package admin;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */


import dal.categoryDAO;
import dal.productDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Category;
import model.User;

/*
    @author BOTMark
*/
public class CategoryManager extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String page = "";
        try {
            //check role
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            String action = request.getParameter("action");
            if (user.getIsAdmin().equalsIgnoreCase("true") || user.getIsStoreStaff().equalsIgnoreCase("true")) {
                //get list category
                if (action == null) {
                    categoryDAO cdao = new categoryDAO();
                    List<Category> category = cdao.getCategory();
                    request.setAttribute("category", category);
                    page = "admin/category.jsp";
                    //action add category
                } else if (action.equalsIgnoreCase("insertcategory")) {
                    categoryDAO c = new categoryDAO();
                    List<Category> categories = c.getCategory();
                    request.setAttribute("CategoryData1", categories);
                    String name = request.getParameter("name");
                    if (name != null && !name.isEmpty()) {
                        productDAO dao = new productDAO();
                        Category d = dao.getCategoryByName(name);
                        //check exists
                        if (d != null) {
                            request.setAttribute("error", name + " already exists.");
                            page = "admin/category.jsp";
                        } else {
                            //add category then return screen categorymanager
                            dao.insertCategory(name);
                            response.sendRedirect("categorymanager");
                            return;
                        }
                    } else {
                        request.setAttribute("error", "Category name cannot be empty.");
                        page = "admin/category.jsp";
                    }
                    //action update category
                } else if (action.equalsIgnoreCase("updatecategory")) {
                    String category_id = request.getParameter("category_id");
                    String category_name = request.getParameter("category_name");

                    if (category_id != null && category_name != null && !category_id.isEmpty() && !category_name.isEmpty()) {
                        try {
                            int categoryId = Integer.parseInt(category_id);
                            categoryDAO dao = new categoryDAO();
                            dao.updateCategory(categoryId, category_name);
                            response.sendRedirect("categorymanager");
                            return;
                        } catch (NumberFormatException e) {
                            request.setAttribute("error", "Invalid category ID format.");
                        } catch (Exception e) {
                            request.setAttribute("error", "An error occurred while updating the category.");
                        }
                    } else {
                        request.setAttribute("error", "Category ID and Name cannot be empty.");
                    }
                    //return back jsp with err mess
                    request.getRequestDispatcher("admin/category.jsp").forward(request, response);
                    //action delete
                } else if (action.equalsIgnoreCase("delete")) {
                    String category_id = request.getParameter("category_id");
                    int id = Integer.parseInt(category_id);
                    categoryDAO dao = new categoryDAO();
                    dao.deleteCategory(id);
                    response.sendRedirect("categorymanager");
                    return;
                }
            }
        } catch (Exception e) {
            page = "404.jsp";
        }
        request.getRequestDispatcher(page).forward(request, response);

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
