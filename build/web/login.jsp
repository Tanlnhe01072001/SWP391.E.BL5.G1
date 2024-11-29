<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="no-js" lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Đăng nhập</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Favicon -->
        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">
        <!-- CSS -->
        <!-- Plugins CSS -->
        <link rel="stylesheet" href="assets/css/plugins.css">
        <!-- Main Style CSS -->
        <link rel="stylesheet" href="assets/css/style.css">
        <style>
            /* Ensures that body takes up full height and has no margin */
            body, html {
                height: 100%;
                margin: 0;
            }

            /* Flexbox for centering */
            .customer_login {
                display: flex;
                align-items: center;
                justify-content: center;
                min-height: 43%;
            }

            .account_form {

                padding: 20px;
                border-radius: 8px;
                box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
                width: 100%;
                max-width: 500px;
            }

            .account_form h2 {
                text-align: center;
                margin-bottom: 20px;
            }

            .login_submit {
                text-align: center;
            }

            .login_submit a,
            .login_submit label {
                display: block;
                margin-top: 10px;
            }

            .login_submit p a {
                display: inline-block;
            }

            .error-message {
                color: red;
                text-align: center;
                margin-bottom: 10px;
            }
            .login_submit label {
                display: flex;
                align-items: center; /* Vertically center checkbox with label */
            }
        </style>
    </head>
    <body>
        <div class="off_canvars_overlay"></div>
        <jsp:include page="menu.jsp"/>
        <!--breadcrumbs area start-->
        <div class="breadcrumbs_area other_bread">
            <div class="container">   
                <div class="row">
                    <div class="col-12">
                        <div class="breadcrumb_content">
                            <ul>
                                <li><a href="home">Home</a></li>
                                <li>/</li>
                                <li>Đăng nhập</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>         
        </div>
        <!--breadcrumbs area end-->

        <!-- customer login start -->
        <div class="customer_login">
            <div class="account_form">
                <h2>Đăng nhập</h2>
                <c:set var="cookie" value="${pageContext.request.cookies}"/>
                <form action="user?action=checkLogin" method="POST">
                    <p class="error-message">
                        ${requestScope.error}
                    </p>
                    <p>   
                        <label>Email <span></span></label>
                        <input name="user_email" type="email" value="${cookie.email.value}">
                    </p>
                    <p>   
                        <label>Mật khẩu <span></span></label>
                        <input name="user_pass" type="password" value="${cookie.pass.value}">
                    </p>   
                    <div class="login_submit">

                        <label for="remember">
                            <input style="margin-left: 150px" ${(cookie.rem.value eq 'ON') ? "checked" : ""} id="remember" name="remember" value="ON" type="checkbox">
                            Nhớ tài khoản
                        </label>
                        <p ><a href="forgotpass.jsp">Quên mật khẩu </a></p>
                        <button style="margin-left: -100px" type="submit">Đăng nhập</button>

                        <p><a style="margin-left: 100px" href="register.jsp">Đăng kí ngay</a></p>


                    </div> 
                </form>
            </div>
        </div>
        <!-- customer login end -->

        <!--footer area start-->
        <jsp:include page="footer.jsp"/>
        <!--footer area end-->

        <!-- JS -->
        
        
        <!-- Plugins JS -->
        <script src="assets/js/plugins.js"></script>
        <!-- Main JS -->
        <script src="assets/js/main.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script>
            function showNotification(message, isSuccess) {
                Swal.fire({
                    title: isSuccess ? 'Thành công!' : 'Lỗi!',
                    text: message,
                    icon: isSuccess ? 'success' : 'error',
                    toast: true,
                    position: 'top-end',
                    showConfirmButton: false,
                    timer: 5000,
                    timerProgressBar: true,
                    didOpen: (toast) => {
                        toast.addEventListener('mouseenter', Swal.stopTimer)
                        toast.addEventListener('mouseleave', Swal.resumeTimer)
                    }
                });
            }

            // Kiểm tra và hiển thị thông báo khi trang được tải
            document.addEventListener('DOMContentLoaded', function () {
                var error_match = "${sessionScope.error_match}";
                var Recaptcha = "${sessionScope.Recaptcha}";
                var error_rePass = "${sessionScope.error_rePass}";
                var msg = "${sessionScope.msg}";
                var signupMessage = "${sessionScope.signupMessage}";
                var error_exist = "${sessionScope.error_exist}";
                var error_ban = "${sessionScope.error_ban}";

                if (error_match) {
                    showNotification(error_match, false);
            <% session.removeAttribute("error_match"); %>
                }
                if (Recaptcha) {
                    showNotification(Recaptcha, false);
            <% session.removeAttribute("Recaptcha"); %>
                } else if (error_rePass) {
                    showNotification(error_rePass, false);
            <% session.removeAttribute("error_rePass"); %>
                } else if (msg) {
                    showNotification(msg, false);
            <% session.removeAttribute("msg"); %>
                } else if (error_exist) {
                    showNotification(error_exist, false);
            <% session.removeAttribute("error_exist"); %>
                } else if (error_ban) {
                    showNotification(error_ban, false);
            <% session.removeAttribute("error_ban"); %>
                } else if (signupMessage) {
                    showNotification(signupMessage, true);
            <% session.removeAttribute("signupMessage"); %>
                }
            });
        </script>
    </body>
</html>
