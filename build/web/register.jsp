<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="no-js" lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Đăng nhập  |  Shopping</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Favicon -->
        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">
        <!-- CSS -->
        <link rel="stylesheet" href="assets/css/plugins.css">
        <link rel="stylesheet" href="assets/css/style.css">
    </head>

    <style>
        /* Ensure the form is centered vertically and horizontally */
        .customer_login {
            display: flex;
            align-items: center;
            justify-content: center;
            min-height: 42%; /* Full height of the viewport */
        }

        .account_form {
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 500px; /* Adjust the width as needed */
        }

        .account_form h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        .login_submit {
            text-align: center;
        }

        .message {
            text-align: center;
            margin-top: 20px;
        }

        .message a {
            color: #007bff; /* Optional: Color for the link */
            text-decoration: none;
        }

        .message a:hover {
            text-decoration: underline;
        }
    </style>

    <body>
        <div class="off_canvars_overlay"></div>
        <jsp:include page="menu.jsp"/>

        <!-- breadcrumbs area start -->
        <div class="breadcrumbs_area other_bread">
            <div class="container">   
                <div class="row">
                    <div class="col-12">
                        <div class="breadcrumb_content">
                            <ul>
                                <li><a href="home">home</a></li>
                                <li>/</li>
                                <li>Đăng kí</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>         
        </div>
        <!-- breadcrumbs area end -->

        <!-- customer login start -->
        <div class="customer_login">
            <div class="account_form register">
                <h2>Đăng ký</h2>
                <form action="user?action=signup" method="POST">
                    <p>   
                        <label>Full Name<span></span></label>
                        <input type="text" name="user_name" placeholder="Nhập full name của bạn" required>
                    </p>
                    <p>   
                        <label>Email <span></span></label>
                        <input type="email" name="user_email" placeholder="Nhập email của bạn" required>
                    </p>  

                    <p>   
                        <label>Mật khẩu <span></span></label>
                        <input type="password" name="user_pass" placeholder="Nhập mật khẩu dạng:a-zA-Z0-9..." required>
                    </p>
                    <p>   
                        <label>Nhập lại mật khẩu <span></span></label>
                        <input type="password" name="re_pass" placeholder="Nhập lại mật khẩu">
                          <% session.removeAttribute("error_match"); %>
               
<!--                    showNotification(msg, false);
                        error_match-->
                    </p>

                    <div class="login_submit">
                        <button type="submit">Đăng Ký</button>
                    </div>

                    <p class="message">Already registered? <a href="user?action=login">Login</a></p>
                </form>
            </div>    
        </div>
        <!-- customer login end -->

        <jsp:include page="footer.jsp"/>

        <script src="assets/js/map.js"></script>
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
        <script src="https://www.google.com/jsapi"></script>
        <script src="assets/js/map.js"></script>
        <script>
            function onSubmit() {
                var response = grecaptcha.getResponse();
                document.getElementById('g-recaptcha-response').value = response;
            }
        </script>

        <script src="assets/js/plugins.js"></script>
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
                        toast.addEventListener('mouseenter', Swal.stopTimer);
                        toast.addEventListener('mouseleave', Swal.resumeTimer);
                    }
                });
            }

            document.addEventListener('DOMContentLoaded', function () {
                var error_match = "${sessionScope.error_match}";
                var Recaptcha = "${sessionScope.Recaptcha}";
                var error_rePass = "${sessionScope.error_rePass}";
                var msg = "${sessionScope.msg}";
                var signupMessage = "${sessionScope.signupMessage}";
                var error_exist = "${sessionScope.error_exist}";
                var error_ban = "${sessionScope.error_ban}";

                if (msg) {
                    showNotification(msg, false);
                } else if (error_rePass) {
                    showNotification(error_rePass, false);
                } else if (error_exist) {
                    showNotification(error_exist, false);
                } else if (error_ban) {
                    showNotification(error_ban, false);
                } else if (signupMessage) {
                    showNotification(signupMessage, true);
                }
            <% session.removeAttribute("error_rePass"); %>
            <% session.removeAttribute("msg"); %>
            <% session.removeAttribute("error_exist"); %>
            <% session.removeAttribute("error_ban"); %>
            <% session.removeAttribute("signupMessage"); %>
            });
        </script>
    </body>
</html>
