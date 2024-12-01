<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html class="no-js" lang="vi">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Trang Chủ</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Favicon -->
        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">
        <!-- Plugins CSS -->
        <link rel="stylesheet" href="assets/css/plugins.css">
        <!-- Main Style CSS -->
        <link rel="stylesheet" href="assets/css/style.css">



        <!-- Chart.js Library -->
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>


        <style>
            .product_thumb {
                position: relative;
                overflow: hidden;
                aspect-ratio: 1 / 1;
            }

            .product_thumb img {
                width: 100%;
                height: 100%;
                object-fit: cover;
                object-position: center;
            }

            .single_product {
                margin-bottom: 20px;
            }
            a, a:hover, a:focus {
                text-decoration: none;
            }

            .product_content h3 a,
            .banner_content a,
            .slider_content a {
                text-decoration: none;
            }
        </style>
    </head>

    <body>
        <!-- Main Wrapper Start -->
        <!--Offcanvas menu area start-->
        <c:if test="${not empty sessionScope.orderSuccessMessage}">
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                ${sessionScope.orderSuccessMessage}
                <a href="user?action=myaccount" class="alert-link">Kiểm tra lịch sử đặt hàng của bạn tại đây.</a>
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <c:remove var="orderSuccessMessage" scope="session"/>
        </c:if>
        <div class="off_canvars_overlay"></div>

        <jsp:include page="menu.jsp"/>

        <!--slider area start-->
        <div class="slider_area slider_style home_three_slider owl-carousel">
            <div class="slider_area slider_style home_three_slider owl-carousel">
                <div class="single_slider" style="background-image: url('assets/img/slider/anhbia1.jpg'); background-size: cover; background-position: center; height: 100vh;">
                    <div class="container">
                        <div class="row align-items-center">
                            <div class="col-12">
                                <div class="slider_content content_one" style="text-align: center;">
                                <br />
                                    <a style="margin-top: 470px; display: inline-block; text-decoration: none; background: #000; color: #fff; padding: 10px 20px; border-radius: 5px;" href="product">
                                        Khám Phá Ngay
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <div class="elfsight-app-94027198-7458-4351-8ca9-9bf86706a713" data-elfsight-app-lazy></div>

        <jsp:include page="footer.jsp"/>
        <!--footer area end-->
        <!-- JS
        ============================================ -->

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
                    timer: 1000,
                    timerProgressBar: true,
                    didOpen: (toast) => {
                        toast.addEventListener('mouseenter', Swal.stopTimer)
                        toast.addEventListener('mouseleave', Swal.resumeTimer)
                    }
                });
            }

            // Kiểm tra và hiển thị thông báo khi trang được tải
            document.addEventListener('DOMContentLoaded', function () {
                var loginMessage = "${sessionScope.loginMessage}";
                var logoutMessage = "${sessionScope.logoutMessage}";
                var errorMessage = "${sessionScope.errorMessage}";

                if (loginMessage) {
                    showNotification(loginMessage, true);
            <% session.removeAttribute("loginMessage"); %>
                }
                if (logoutMessage) {

                    showNotification(logoutMessage, true);
            <% session.removeAttribute("logoutMessage"); %>
                } else if (errorMessage) {
                    showNotification(errorMessage, false);
            <% session.removeAttribute("errorMessage"); %>
                }
            });
        </script>
    </body>

</html>
