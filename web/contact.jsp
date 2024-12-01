<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!doctype html>
<html class="no-js" lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Liên hệ  |  2001Store</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Favicon -->
        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">

        <!-- CSS 
        ========================= -->


        <!-- Plugins CSS -->
        <link rel="stylesheet" href="assets/css/plugins.css">

        <!-- Main Style CSS -->
        <link rel="stylesheet" href="assets/css/style.css">

    </head>

    <body>

        <!--Offcanvas menu area start-->
        <div class="off_canvars_overlay"></div>
        <jsp:include page="menu.jsp"/>
        <!--breadcrumbs area start-->
        <div class="breadcrumbs_area other_bread">
            <div class="container">   
                <div class="row">
                    <div class="col-12">
                        <div class="breadcrumb_content">
                            <ul>
                                <li><a href="home">Trang chủ</a></li>
                                <li>/</li>
                                <li>Liên hệ</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>         
        </div>
        <!--breadcrumbs area end-->


        <!--contact area start-->
        <div class="contact_area">
            <div class="container">   
                <div class="row">
                    <div class="col-lg-6 col-md-12">
                        <div class="contact_message content">
                            <h3>Liên hệ</h3>
                            <ul>
                                <li><i class="fa fa-fax"></i>  Đại học FPT</li>
                                <li><i class="fa fa-envelope-o"> </i> <a href="mailto:dungnthe130159@fpt.edu.vn@fpt.edu.vn">dungnthe130159@fpt.edu.vn</a></li>
                                <li><i class="fa fa-phone"></i> 0979986201</li>                        
                            </ul>             
                        </div> 
                    </div>
                    <div class="col-lg-6 col-md-12">
                        <div class="contact_message form">
                            <h3>Gửi thông tin phản hồi</h3> 
                            <form action="contact" method="post">
                                <p style="color: red; align-content: center;">
                                    ${requestScope.msgc}
                                </p>
                              
                                <p>      
                                    <label>  Địa chỉ email</label>
                                    <input style="font-weight: bolder" name="user_email" type="text" value="${user.user_email}">
                                </p>
                                <p>          
                                    <label>  Tiêu đề</label>
                                    <input name="subject_report" placeholder="Nhập tiêu đề ..." required type="text">
                                </p>    
                                <div class="contact_textarea">
                                    <label>  Nội dung</label>
                                    <input placeholder="Nhập nội dung của phản hồi ..." name="content_report" required/>  
                                </div>
                                <input hidden name="user_id" require type="text" value="${user.user_id}">
                                <br>
                                <c:if test="${!user.isAdmin == 'True' && !user.isStoreStaff=='True'}">
                                    <button onclick="handleSubmit()" type="submit">Gửi</button>
                                </c:if>
                            </form>

                ${msgc}
                 ${requestScope.msgc}

                        </div> 
                    </div>
                </div>
            </div>    
        </div>
        <!--contact area end-->
        <!--contact map start-->
        <!--contact map end-->

        <jsp:include page="footer.jsp"/>

        <!-- JS
        ============================================ -->

        <!-- Plugins JS -->
        <script src="assets/js/plugins.js"></script>

        <!-- Main JS -->
        <script src="assets/js/main.js"></script>
        <script>
            function handleSubmit(e) {
//              var email = e.parameter.Email;
//              var subject = e.parameter.Subject;
//              var message = e.parameter.Message;
//              const email = document.querySelector('#user_email').value;
//              const subject = document.querySelector('#subject_report').value;
//              const message = document.querySelector('#content_report').value;

              const form = document.getElementById('contact-form');
              const formData = new FormData(form);

        // Lấy dữ liệu từ FormData
                const email = formData.get('user_email');
                const subject = formData.get('subject_report');
                const message = formData.get('content_report');
                // Tạo nội dung email
                var emailContent = 'Email: ' + email + '\n\n' + 'Subject: ' + subject + '\n\n' + 'Message: ' + message;
                // Gửi email
                MailApp.sendEmail({
                    to: 'dungnthe130159@fpt.edu.vn', // Thay bằng địa chỉ email của bạn
                    subject: subject,
                    body: emailContent
                });
                // Trả về phản hồi
                return ContentService.createTextOutput('Success');
            }

        </script>
    </body>

</html>