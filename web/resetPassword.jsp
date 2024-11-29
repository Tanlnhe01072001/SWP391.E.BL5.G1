<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="no-js" lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Xác Minh || Shopping</title>
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
        <div class="off_canvars_overlay"></div>
        <jsp:include page="menu.jsp"/>         
    </div>
    <div class="customer_login">
        <div class="container">
            <div class="row">
                <div class="forgot" style="width:50%;margin-left: 25% ">
                    <div class="account_form">
                        <h2>Thay Mật Khẩu</h2>
                        <form action="resetPass" method="POST">
                            <p style="color: red; align-content: center;">
                                <b> ${sessionScope.msg}</b>
                            </p>
                            <p>

                                <label>Nhập Code <span>*</span></label>
                                <input name="code" type="text">
                                <label>Nhập PassWord Mới <span>*</span></label>
                                <input name="Pass" type="password">
                                <label>Xác Nhận PassWord Mới <span>*</span></label>
                                <input name="Repass" type="password">
                            </p>

                            <div class="login_submit">
                                <button type="submit">Đặt lại mật khẩu</button>
                            </div>
                        </form>
                    </div>    
                </div>
            </div>
        </div>
    </div>


    <!-- JS
    ============================================ -->


    <!-- JS -->
    <!--map js code here-->
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAdWLY_Y6FL7QGW5vcO3zajUEsrKfQPNzI"></script>
    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
    <script src="https://www.google.com/jsapi"></script>
    <script src="assets/js/map.js"></script>
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
