<%@page import="java.net.URLEncoder"%>
<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="VnPayCommon.Config"%>
<%@ page import="java.text.SimpleDateFormat, java.util.Date, java.text.ParseException" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page import="model.*" %>
<%@ page import="dal.*" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="">
        <meta name="author" content="">
        <title  style="font-size: 30px; width: 40%">KẾT QUẢ THANH TOÁN</title>
        <!-- Bootstrap core CSS -->
        <link href="assets/bootstrap.min.css" rel="stylesheet"/>
        <!-- Custom styles for this template -->
        <link href="assets/jumbotron-narrow.css" rel="stylesheet"> 
        <script src="assets/jquery-1.11.3.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <style>
            label{
                font-size: 20px;
            }
            .transaction-status {
      font-size: 24px;
      margin-bottom: 15px;
    }
    .transaction-details {
      margin-bottom: 20px;
    }
    .transaction-details dt {
      font-weight: bold;
    }
        .blue-orange-header {
    background: linear-gradient(to right, rgba(0, 0, 255, 0.7), rgba(255, 165, 0, 0.7)); /* Lighter blue and orange with 0.7 opacity */
    color: white;
    padding: 20px;}
    .rounded-header {
      background: linear-gradient(to right, blue, orange);
      color: white;
      padding: 20px;
      border-radius: 15px;
      margin-bottom: 20px;
    }
     body {
                background: linear-gradient(135deg, #e6f2ff 0%, #fff5e6 100%);
                font-family: 'Arial', sans-serif;
                min-height: 100vh;
            }
        </style>
    </head>
    <body>
        <%
            //Begin process return from VNPAY
            Map fields = new HashMap();
            for (Enumeration params = request.getParameterNames(); params.hasMoreElements();) {
                String fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
                String fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
                if ((fieldValue != null) && (fieldValue.length() > 0)) {
                    fields.put(fieldName, fieldValue);
                }
            }
            String vnp_SecureHash = request.getParameter("vnp_SecureHash");
            if (fields.containsKey("vnp_SecureHashType")) {
                fields.remove("vnp_SecureHashType");
            }
            if (fields.containsKey("vnp_SecureHash")) {
                fields.remove("vnp_SecureHash");
            }
            String signValue = Config.hashAllFields(fields);
        %>
        <!--Begin display -->
        <div class="container mt-5">
            <div class="row">
        <header class="blue-orange-header col-md-12 rounded-header">
            <h1 class="text-center">Kết quả thanh toán</h1>
          </header>
            <div class="table-responsive">
                <div class="form-group">
                    <label style="font-size: 20px" >Mã giao dịch thanh toán:</label>
                    <label><%=request.getParameter("vnp_TxnRef").split("R")[0]%></label>
                </div>    
                <div class="form-group">
                    <label >Số tiền:</label>
                    <label><%=request.getParameter("vnp_Amount")%></label>
                </div>  
                <div class="form-group">
                    <label >Mô tả giao dịch:</label>
                    <label><%=request.getParameter("vnp_OrderInfo")%></label>
                </div> 
                <div class="form-group">
                    <label >Mã lỗi thanh toán:</label>
                    <label><%=request.getParameter("vnp_ResponseCode")%></label>
                </div> 
                <div class="form-group">
                    <label >Mã giao dịch tại CTT VNPAY-QR:</label>
                    <label><%=request.getParameter("vnp_TransactionNo")%></label>
                </div> 
                <div class="form-group">
                    <label >Mã ngân hàng thanh toán:</label>
                    <label><%=request.getParameter("vnp_BankCode")%></label>
                </div> 
                <div class="form-group">
                    <label >Thời gian thanh toán:</label>
                    <label><%
                        String originalTimestamp = request.getParameter("vnp_PayDate");
                        String formattedTimestamp = "";
                         SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                        SimpleDateFormat newFormat = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss");
                        Date date = originalFormat.parse(originalTimestamp);
                    formattedTimestamp = newFormat.format(date);
                    out.print(formattedTimestamp);
                        %></label>
                </div> 
                <div class="form-group">
                    <label >Tình trạng giao dịch:</label>
                    <label>
                        <%
                            if (signValue.equals(vnp_SecureHash)) {
                                if ("00".equals(request.getParameter("vnp_TransactionStatus"))) {
                                    out.println("Payment success!");
                                    try {
                                        CheckBill bill = (CheckBill) request.getSession().getAttribute("pendingBill");
                                        billDAO dao = new billDAO();
                                        if (bill != null) {
                                            dao.addOrder(bill.getUser(), bill.getCart(), bill.getPayment(), bill.getAddress(), Integer.parseInt(bill.getPhone()));
                                            request.getSession().removeAttribute("cart");
                                            request.getSession().removeAttribute("pendingBill");
                                            request.getSession().setAttribute("size", 0);
                                            // Log successful transaction
                                            System.out.println("Transaction successful for order: " + request.getParameter("vnp_TxnRef").split("R")[0]);
                                        } else {
                                            
                                            // Log error
                                           
                                        }
                                    } catch (Exception e) {
                                        out.print("Error processing order");
                                        // Log error
                                        System.out.println("Error processing order: " + e.getMessage());
                                    }
                                } else {
                                    out.print("Không thành công");
                                    // Log failed transaction
                                    System.out.println("Transaction failed: " + request.getParameter("vnp_TransactionStatus"));
                                }
                            } else {
                                out.print("invalid signature");
                                // Log security issue
                                System.out.println("Invalid signature for transaction: " + request.getParameter("vnp_TxnRef"));
                            }
                        %>
                    </label>
                    
                </div> 
            </div>
            <a  href="http://localhost:8080/SWP391.E.BL5-G1/home">Cảm ơn quý khách đã mua hàng</a>
            <p>
                &nbsp;
            </p>
            <footer class="footer">
                <p>&copy; VNPAY</p>
            </footer>
        </div> 
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</html>