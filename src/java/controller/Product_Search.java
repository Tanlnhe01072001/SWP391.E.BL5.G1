/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.albumDAO;
import dal.commentRatingDAO;
import dal.productDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import model.Album;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "Product_Search", urlPatterns = {"/search"})
public class Product_Search extends HttpServlet {

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
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("listByCategory")) {
            String category_id = request.getParameter("category_id");
            int category_id1 = Integer.parseInt(category_id);
            HttpSession session = request.getSession();
            session.setAttribute("selectedCategoryId", category_id1);
            productDAO c = new productDAO();
            List<model.Product> productList = c.getProductByCategory(category_id1);
            List<Category> category = c.getCategory();
            int page, numperpage = 9;
            int size = productList.size();
            int num = (size % 9 == 0 ? (size / 9) : ((size / 9)) + 1);//so trang
            String xpage = request.getParameter("page");
            if (xpage == null) {
                page = 1;
            } else {
                page = Integer.parseInt(xpage);
            }
            int start, end;
            start = (page - 1) * numperpage;
            end = Math.min(page * numperpage, size);
            List<model.Product> product = c.getListByPage(productList, start, end);
            request.setAttribute("page", page);
            request.setAttribute("num", num);
            request.setAttribute("CategoryData", category);
            request.setAttribute("ProductData", product);
            request.getRequestDispatcher("shop_category.jsp").forward(request, response);
        }
        if (action.equalsIgnoreCase("productdetail")) {
            boolean user_comment = false;
            if (request.getParameter("comment") != null) {
                user_comment = true;
            }
            String product_id = request.getParameter("product_id");
            productDAO c = new productDAO();
            List<model.Size> sizeList = c.getSizeByID(product_id);
            List<model.Color> colorList = c.getColorByID(product_id);
            model.Product product = c.getProductByID(product_id);
            int category_id = product.getCate().getCategory_id();
            List<model.Product> productByCategory = c.getProductByCategory(category_id);
            PrintWriter out = response.getWriter();
            
            int user_id = 0;
            albumDAO a = new albumDAO();
            List<Album> album = null;
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                model.User user = (model.User) session.getAttribute("user");
                user_id = user.getUser_id();
                album = a.getList(user_id);
            }
            album = a.getList(user_id);
            commentRatingDAO crDAO = new commentRatingDAO();
            List<model.Comment> comments = null;
            List<model.Comment> comments1 = null;
            List<model.Comment> comments2 = null;
            List<model.Comment> comments3 = null;
            List<model.Comment> comments4 = null;
            List<model.Comment> comments5 = null;

            int ratingCmt = 6;
            boolean haveCmt = false;
            if (request.getParameter("comment_filter") != null) {
                String comment_filter = request.getParameter("comment_filter");
                ratingCmt = Integer.parseInt(comment_filter);
                comments = crDAO.getCommentsByRating(product_id, ratingCmt);
                comments1 = crDAO.getCommentsByRating1(product_id);
                comments2 = crDAO.getCommentsByRating2(product_id);
                comments3 = crDAO.getCommentsByRating3(product_id);
                comments4 = crDAO.getCommentsByRating4(product_id);
                comments5 = crDAO.getCommentsByRating5(product_id);
                haveCmt = true;

            } else {
                comments = crDAO.getCommentsByProductId(product_id);
                if (comments.size() > 0) {
                    haveCmt = true;
                }
                comments1 = crDAO.getCommentsByRating1(product_id);
                comments2 = crDAO.getCommentsByRating2(product_id);
                comments3 = crDAO.getCommentsByRating3(product_id);
                comments4 = crDAO.getCommentsByRating4(product_id);
                comments5 = crDAO.getCommentsByRating5(product_id);
            }
            int numberOfComments = comments.size();
            int numberOfComments1 = comments1.size();
            int numberOfComments2 = comments2.size();
            int numberOfComments3 = comments3.size();
            int numberOfComments4 = comments4.size();
            int numberOfComments5 = comments5.size();

            if (comments1.size() > 0) {
                numberOfComments1 = comments1.size();
                request.setAttribute("numberOfComments1", numberOfComments1);
            }
            if (comments2.size() > 0) {
                numberOfComments2 = comments2.size();
                request.setAttribute("numberOfComments2", numberOfComments2);
            }
            if (comments3.size() > 0) {
                numberOfComments3 = comments3.size();
                request.setAttribute("numberOfComments3", numberOfComments3);
            }
            if (comments4.size() > 0) {
                numberOfComments4 = comments4.size();
                request.setAttribute("numberOfComments4", numberOfComments4);
            }
            if (comments5.size() > 0) {
                numberOfComments5 = comments5.size();
                request.setAttribute("numberOfComments5", numberOfComments5);
            }
            List<model.Comment> commentss = crDAO.getCommentsByProductId(product_id);
            double averageRating = crDAO.getAverageRatingForProduct(product_id);
            request.setAttribute("ProductData", product);
            request.setAttribute("SizeData", sizeList);
            request.setAttribute("ColorData", colorList);
            request.setAttribute("ProductByCategory", productByCategory);
            request.setAttribute("comments", commentss);
            request.setAttribute("numberOfComments", numberOfComments);
            request.setAttribute("averageRating", averageRating);
            request.setAttribute("user_comment", user_comment);
            request.setAttribute("comment_filter", ratingCmt);
            request.setAttribute("haveCmt", haveCmt);
            request.setAttribute("AlbumData", album);
            request.getRequestDispatcher("product-details.jsp").forward(request, response);
        } else if (action.equalsIgnoreCase("addComment")) {
            String productId = request.getParameter("product_id");
            String userId = request.getParameter("user_id");  // Retrieve userId from session
            String userName = request.getParameter("user_name");  // Retrieve userId from session
            int rating = Integer.parseInt(request.getParameter("rating"));
            String commentText = request.getParameter("comment");

//             Call DAO method to add rating

            commentRatingDAO dao = new commentRatingDAO();
            if (dao.hasUserCommented(productId, userId)) {
                HttpSession session = request.getSession();
                session.setAttribute("errorMessage", "Bạn đã đánh giá và bình luận cho sản phẩm này rồi.");
            } else {
                dao.addComment(productId, userId, commentText, rating, userName);
                HttpSession session = request.getSession();
                session.setAttribute("successMessage", "Hãy tiến hành mua sản phẩm để được đánh giá và bình luận");
            }
            response.sendRedirect("search?action=productdetail&product_id=" + productId);
        } //update comment
        else if (action.equalsIgnoreCase("updatecmt")) {
            String productId = request.getParameter("idproduct");
            String comment = request.getParameter("comment-update");
            int id = Integer.parseInt(productId);
            if (comment != null || !comment.isEmpty()) {
                commentRatingDAO crDAO = new commentRatingDAO();
                crDAO.updateComment(id, comment);
                response.sendRedirect("search?action=productdetail&product_id=" + productId);
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("errorMessage", "Không được để trống nội dung comment");
            }

            return;

        } //delete cmt
        else if (action.equalsIgnoreCase("delete")) {
            String cmt_id = request.getParameter("cmt_id");
            int id = Integer.parseInt(cmt_id);
            commentRatingDAO crDAO = new commentRatingDAO();
            crDAO.deleteComment(id);
            response.sendRedirect("product");
            return;
        } else if (action.equalsIgnoreCase("addProductAlbum")) {
            HttpSession session = request.getSession();
            model.User user = (model.User) session.getAttribute("user");
            int user_id = user.getUser_id();
            albumDAO c = new albumDAO();
            String product_name = request.getParameter("product_name");
            String product_price = request.getParameter("product_price");
            float price = Float.parseFloat(product_price);
            String album_ID = request.getParameter("album_id");
            int _id = Integer.parseInt(album_ID);
            String product_img = request.getParameter("product_img");
            String product_id = request.getParameter("product_id");
            c.addProductAlbum(_id, product_id, product_name, price, product_img);
                request.getRequestDispatcher("product-detail.jsp").forward(request, response);
            return;
        }

        if (action.equals("sort")) {
            String type = request.getParameter("type");
            if (type.equals("low")) {
                productDAO c = new productDAO();
                List<model.Product> productList = c.getProductLow();
                List<Category> category = c.getCategory();
                int page, numperpage = 9;
                int size = productList.size();
                int num = (size % 9 == 0 ? (size / 9) : ((size / 9)) + 1);//so trang
                String xpage = request.getParameter("page");
                if (xpage == null) {
                    page = 1;
                } else {
                    page = Integer.parseInt(xpage);
                }
                int start, end;
                start = (page - 1) * numperpage;
                end = Math.min(page * numperpage, size);
                List<model.Product> product = c.getListByPage(productList, start, end);
                request.setAttribute("page", page);
                request.setAttribute("num", num);
                request.setAttribute("CategoryData", category);
                request.setAttribute("ProductData", product);
                request.getRequestDispatcher("shop_category.jsp").forward(request, response);
            }
            if (type.equals("high")) {
                productDAO c = new productDAO();
                List<model.Product> productList = c.getProductHigh();
                List<Category> category = c.getCategory();
                int page, numperpage = 9;
                int size = productList.size();
                int num = (size % 9 == 0 ? (size / 9) : ((size / 9)) + 1);//so trang
                String xpage = request.getParameter("page");
                if (xpage == null) {
                    page = 1;
                } else {
                    page = Integer.parseInt(xpage);
                }
                int start, end;
                start = (page - 1) * numperpage;
                end = Math.min(page * numperpage, size);
                List<model.Product> product = c.getListByPage(productList, start, end);
                request.setAttribute("page", page);
                request.setAttribute("num", num);
                request.setAttribute("CategoryData", category);
                request.setAttribute("ProductData", product);
                request.getRequestDispatcher("shop_category.jsp").forward(request, response);
            }
            if (type.equals("a-z")) {
                productDAO c = new productDAO();
                List<model.Product> productList = c.getProductAZ();
                List<Category> category = c.getCategory();
                int page, numperpage = 9;
                int size = productList.size();
                int num = (size % 9 == 0 ? (size / 9) : ((size / 9)) + 1);//so trang
                String xpage = request.getParameter("page");
                if (xpage == null) {
                    page = 1;
                } else {
                    page = Integer.parseInt(xpage);
                }
                int start, end;
                start = (page - 1) * numperpage;
                end = Math.min(page * numperpage, size);
                List<model.Product> product = c.getListByPage(productList, start, end);
                request.setAttribute("page", page);
                request.setAttribute("num", num);
                request.setAttribute("CategoryData", category);
                request.setAttribute("ProductData", product);
                request.getRequestDispatcher("shop_category.jsp").forward(request, response);
            }
        }

        if (action.equals("search")) {
            String text = request.getParameter("text");
            productDAO c = new productDAO();
            List<model.Product> productList = c.SearchAll(text);
            List<Category> category = c.getCategory();
            int page, numperpage = 9;
            int size = productList.size();
            int num = (size % 9 == 0 ? (size / 9) : ((size / 9)) + 1);//so trang
            String xpage = request.getParameter("page");
            if (xpage == null) {
                page = 1;
            } else {
                page = Integer.parseInt(xpage);
            }
            int start, end;
            start = (page - 1) * numperpage;
            end = Math.min(page * numperpage, size);
            List<model.Product> product = c.getListByPage(productList, start, end);
            request.setAttribute("page", page);
            request.setAttribute("num", num);
            request.setAttribute("CategoryData", category);
            request.setAttribute("ProductData", product);
            request.getRequestDispatcher("shop_category.jsp").forward(request, response);
        }

        //SearchByPrice
        if (action.equalsIgnoreCase("searchByPrice")) {
            productDAO dao = new productDAO();
            List<Category> category = dao.getCategory();
            String[] choose = request.getParameterValues("price");
            List<model.Product> list1 = dao.getProductByPrice(0, 50000);
            List<model.Product> list2 = dao.getProductByPrice(50000, 200000);
            List<model.Product> list3 = dao.getProductByPrice(200000, 500000);
            List<model.Product> list4 = dao.getProductByPrice(500000, 1000000);
            List<model.Product> list5 = dao.getProductByPrice(1000000);
            List<model.Product> list0 = dao.getProduct();
            List<model.Product> listc = new ArrayList<>();

            // Combine selected price range lists
            if (choose.length == 5) {
                listc.addAll(list1);
                listc.addAll(list2);
                listc.addAll(list3);
                listc.addAll(list4);
                listc.addAll(list5);
            } else if (choose.length == 2) {
                if (!choose[0].equals("0") && !choose[1].equals("0")) {
                    listc.addAll(list2);
                    listc.addAll(list3);
                }
                if (!choose[0].equals("1") && !choose[1].equals("1")) {
                    listc.addAll(list1);
                    listc.addAll(list3);
                }
                if (!choose[0].equals("2") && !choose[1].equals("2")) {
                    listc.addAll(list1);
                    listc.addAll(list2);
                }
            } else if (choose.length == 1) {
                switch (choose[0]) {
                    case "0":
                        listc.addAll(list1);
                        break;
                    case "1":
                        listc.addAll(list2);
                        break;
                    case "2":
                        listc.addAll(list3);
                        break;
                    case "3":
                        listc.addAll(list4);
                        break;
                    case "4":
                        listc.addAll(list5);
                        break;
                    default:
                        listc.addAll(list0);
                        break;
                }
            } else {
                listc.addAll(list0);
            }

            // Pagination logic
            int page, numperpage = 9;
            int size = listc.size();
            int num = (size % 9 == 0 ? (size / 9) : ((size / 9)) + 1);// Number of pages
            String xpage = request.getParameter("page");
            if (xpage == null) {
                page = 1; // Default page
            } else {
                page = Integer.parseInt(xpage);
            }
            int start = (page - 1) * numperpage;
            int end = Math.min(page * numperpage, size);
            List<model.Product> product = listc.subList(start, end); // Sublist for current page

            request.setAttribute("page", page);
            request.setAttribute("num", num);
            request.setAttribute("CategoryData", category);
            request.setAttribute("ProductData", product);
            request.getRequestDispatcher("shop_category.jsp").forward(request, response);
        }
        if (action.equalsIgnoreCase("SearchByColor")) {
            productDAO dao = new productDAO();
            List<Category> category = dao.getCategory();
            String[] choose = request.getParameterValues("colors");
            List<model.Product> list1 = dao.getProductByColor("Red");
            List<model.Product> list2 = dao.getProductByColor("Blue");
            List<model.Product> list3 = dao.getProductByColor("White");
            List<model.Product> list4 = dao.getProductByColor("Black");
            List<model.Product> list0 = dao.getProduct();
            List<model.Product> listp = new ArrayList<>();

            // Combine selected color lists
            if (choose == null || choose.length == 0 || choose.length == 4) {
                listp.addAll(list0); // Default to all products if no colors selected
            } else {
                for (String color : choose) {
                    switch (color) {
                        case "0":
                            listp.addAll(list1);
                            break;
                        case "1":
                            listp.addAll(list2);
                            break;
                        case "2":
                            listp.addAll(list3);
                            break;
                        case "3":
                            listp.addAll(list4);
                            break;
                    }
                }
            }

            // Pagination logic
            int page, numperpage = 9;
            int size = listp.size();
            int num = (size % 9 == 0 ? (size / 9) : ((size / 9)) + 1);// Number of pages
            String xpage = request.getParameter("page");
            if (xpage == null) {
                page = 1; // Default page
            } else {
                page = Integer.parseInt(xpage);
            }
            int start = (page - 1) * numperpage;
            int end = Math.min(page * numperpage, size);
            List<model.Product> product = listp.subList(start, end); // Sublist for current page

            request.setAttribute("page", page);
            request.setAttribute("num", num);
            request.setAttribute("CategoryData", category);
            request.setAttribute("ProductData", product);
            request.getRequestDispatcher("shop_category.jsp").forward(request, response);
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
