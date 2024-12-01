/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import dal.productDAO;
import model.Category;
import model.Color;
import model.Product;
import model.Size;
import model.User;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Objects;
import model.Product_Active;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 11
)
public class ProductManager extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");
        String page = "";
        try {
            if (action == null || action.equals("")) {
                productDAO c                = new productDAO();
                List<Product> product       = c.getProduct();
                List<Size> size             = c.getSize();
                List<Color> color           = c.getColor();
                List<Category> category     = c.getCategory();
                List<Product_Active> active = c.getActive();
                request.setAttribute("ActiveData", active);
                request.setAttribute("CategoryData", category);
                request.setAttribute("ProductData", product);
                request.setAttribute("SizeData", size);
                request.setAttribute("ColorData", color);
                page = "product.jsp";
            } else if (action.equalsIgnoreCase("insert")) {
                productDAO c = new productDAO();
                List<Category> category = c.getCategory();
                request.setAttribute("CategoryData", category);
                page = "productinsert.jsp";
            } else if (action.equalsIgnoreCase("insertproduct")) {
                String product_id = request.getParameter("product_id");
                String category_id = request.getParameter("category_id");
                String product_name = request.getParameter("product_name");
                String product_price = request.getParameter("price");
                String product_size = request.getParameter("size");
                String product_color = request.getParameter("color");
                String product_quantity = request.getParameter("quantity");
                String product_img = "images/" + request.getParameter("product_img");
                String product_describe = request.getParameter("describe");
                String active = request.getParameter("permission");
                int quantity = Integer.parseInt(product_quantity);
                Float price = Float.valueOf(product_price);
                int cid = Integer.parseInt(category_id);
                productDAO dao = new productDAO();
                Category cate = new Category(cid);
                String[] size_rw = product_size.split("\\s*,\\s*");
                String[] color_rw = product_color.split("\\s*,\\s*");
                List<Size> list = new ArrayList<>();
                for (String s : size_rw) {
                    list.add(new Size(product_id, s));
                }

                List<Color> list2 = new ArrayList<>();
                for (String c : color_rw) {
                    list2.add(new Color(product_id, c));
                }

                Product product = new Product();
                Product_Active Pa = new Product_Active(product_id, active);
                product.setCate(cate);
                product.setProduct_id(product_id);
                product.setProduct_name(product_name);
                product.setProduct_price(price);
                product.setProduct_describe(product_describe);
                product.setQuantity(quantity);
                product.setImg(product_img);
                product.setSize(list);
                product.setColor(list2);
                product.setActive(Pa);
                dao.insertProduct(product);
                response.sendRedirect("productmanager");
                return;
            } else if (action.equalsIgnoreCase("deleteproduct")) {
                String product_id = request.getParameter("product_id");
                productDAO dao = new productDAO();
                dao.ProductDelete(product_id);
                response.sendRedirect("productmanager");
                return;
            } else if (action.equalsIgnoreCase("updateproduct")) {
                String product_id       = request.getParameter("product_id");
                String category_id      = request.getParameter("category_id");
                String product_name     = request.getParameter("product_name");
                String product_price    = request.getParameter("product_price");
                String product_size     = request.getParameter("product_size");
                String product_color    = request.getParameter("product_color");
                String product_quantity = request.getParameter("product_quantity");
                String product_img      = "images/" + request.getParameter("product_img");
                String product_describe = request.getParameter("product_describe");
                String active           = request.getParameter("permission");
                //lỗi do các căp key bị null
                int quantity            = Integer.parseInt(product_quantity);
                if(quantity < 0) quantity = 1;
                if (product_price != null) {
                    product_price = product_price.replaceAll(",", ""); // Remove commas
                }
                Float price = Float.valueOf(product_price);
                int cid = Integer.parseInt(category_id);
                productDAO dao = new productDAO();
                Category cate = new Category(cid);
                String[] size_rw = product_size.split("\\s*,\\s*");
                String[] color_rw = product_color.split("\\s*,\\s*");
                //size
                List<Size> list = new ArrayList<>();

                for (String s : size_rw) {
                    list.add(new Size(product_id, s));
                }
                //color
                List<Color> list2 = new ArrayList<>();
                for (String c : color_rw) {
                    list2.add(new Color(product_id, c));
                }
                Product_Active activep =new Product_Active(product_id, active);
                Product product = new Product();
                product.setCate(cate);
                product.setProduct_id(product_id);
                product.setProduct_name(product_name);
                product.setProduct_price(price);
                product.setProduct_describe(product_describe);
                product.setQuantity(quantity);
                product.setImg(product_img);
                product.setSize(list);
                product.setColor(list2);      
                product.setActive(activep);
                dao.updateProduct(product, cid, list2, list);
                response.sendRedirect("productmanager");
                return;
            }else if (action.equalsIgnoreCase("insertByExcel")) {
                Part filePart = request.getPart("file");
                InputStream fileContent = filePart.getInputStream();
                Workbook workbook = new XSSFWorkbook(fileContent);
                Sheet sheet = workbook.getSheetAt(0);
                int totalRow = sheet.getLastRowNum();
                for (Row row : sheet) {
                    if (row.getRowNum() > 3) {
                        handleExcelData(row);
                    }
                }
                response.sendRedirect("productmanager");
                return;
            }
        } catch (ServletException | IOException | NumberFormatException e) {
            page = "404.jsp";
        }
        RequestDispatcher dd = request.getRequestDispatcher(page);
        dd.forward(request, response);

    }
    private void handleExcelData(Row row) {
        String product_id       = row.getCell(0).getStringCellValue();
        int category_id         = (int) row.getCell(1).getNumericCellValue();
        String product_name     = row.getCell(2).getStringCellValue();
        float product_price     = (float) row.getCell(3).getNumericCellValue();
        String product_size     = row.getCell(4).getStringCellValue();
        String product_color    = row.getCell(5).getStringCellValue();
        int product_quantity    = (int) row.getCell(6).getNumericCellValue();
        String product_img      = "images/" + row.getCell(7).getStringCellValue();
        String product_describe = row.getCell(8).getStringCellValue();
        String active           = "True";
        productDAO dao          = new productDAO();
        Category cate           = new Category(category_id);
        String[] size_rw        = product_size.split("\\s*,\\s*");
        String[] color_rw       = product_color.split("\\s*,\\s*");
        List<Size> list = new ArrayList<>();
        for (String s : size_rw) {
            list.add(new Size(product_id, s));
        }
        List<Color> list2 = new ArrayList<>();
        for (String c : color_rw) {
            list2.add(new Color(product_id, c));
        }
        Product product     = new Product();
        Product_Active Pa   = new Product_Active(product_id, active);
        product.setCate(cate);
        product.setProduct_id(product_id);
        product.setProduct_name(product_name);
        product.setProduct_price(product_price);
        product.setProduct_describe(product_describe);
        product.setQuantity(product_quantity);
        product.setImg(product_img);
        product.setSize(list);
        product.setColor(list2);
        product.setActive(Pa);
        //insert product by page
        dao.insertProduct(product);
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public int BUFFER_SIZE = 1024 * 1000;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("dowloadTemplate".equals(action)) {
            String path = getServletContext().getRealPath("") + "excel_template" + File.separator + "sample-xlsx-file.xlsx";
            System.out.println(path);
            File file = new File(path);
            OutputStream os = null;
            FileInputStream fis = null;
            response.setHeader("Content-Disposition", String.format("attachment;filename=\"%s\"", file.getName()));
            response.setContentType("application/octet-stream");
            if (file.exists()) {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                byte[] bf = new byte[BUFFER_SIZE];
                int byteRead = -1;
                while ((byteRead = fis.read(bf)) != -1) {
                    os.write(bf, 0, byteRead);
                }
            }
        } else {
            processRequest(request, response);
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
