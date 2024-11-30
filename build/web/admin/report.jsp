<%-- 
    Document   : report
    Created on : Jun 11, 2024, 1:09:56 AM
    Author     : admin
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="javax.mail.*, javax.mail.internet.*, java.util.Properties" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danh sách danh mục sản phẩm | Quản trị Admin</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Main CSS-->
        <link rel="stylesheet" type="text/css" href="admin/css/main.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
        <!-- or -->
        <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">

        <!-- Font-icon css-->
        <link rel="stylesheet" type="text/css"
              href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
    </head>
    <style>
        button.close {
            padding: 0;
            background-color: transparent;
            border: 0;
            -webkit-appearance: none;
        }

        .modal-open {
            overflow: hidden;
        }

        .modal {
            position: fixed;
            top: 0;
            right: 0;
            bottom: 0;
            left: 0;
            z-index: 1050;
            display: none;
            overflow: hidden;
            outline: 0;
        }

        .modal-open .modal {
            overflow-x: hidden;
            overflow-y: auto;
        }

        .modal-dialog {
            position: relative;
            width: auto;
            margin: 0.5rem;
            pointer-events: none;
        }

        .modal.fade .modal-dialog {
            -webkit-transition: -webkit-transform 0.3s ease-out;
            transition: -webkit-transform 0.3s ease-out;
            -o-transition: transform 0.3s ease-out;
            transition: transform 0.3s ease-out;
            transition: transform 0.3s ease-out, -webkit-transform 0.3s ease-out;
            -webkit-transform: translate(0, -25%);
            -ms-transform: translate(0, -25%);
            transform: translate(0, -25%);
        }

        @media screen and (prefers-reduced-motion: reduce) {
            .modal.fade .modal-dialog {
                -webkit-transition: none;
                -o-transition: none;
                transition: none;
            }
        }

        .modal.show .modal-dialog {
            -webkit-transform: translate(0, 0);
            -ms-transform: translate(0, 0);
            transform: translate(0, 0);
        }

        .modal-dialog-centered {
            display: -webkit-box;
            display: -ms-flexbox;
            display: flex;
            -webkit-box-align: center;
            -ms-flex-align: center;
            align-items: center;
            min-height: calc(100% - (0.5rem * 2));
        }

        .modal-content {
            position: relative;
            display: -webkit-box;
            display: -ms-flexbox;
            display: flex;
            -webkit-box-orient: vertical;
            -webkit-box-direction: normal;
            -ms-flex-direction: column;
            flex-direction: column;
            width: 100%;
            pointer-events: auto;
            background-color: #FFF;
            background-clip: padding-box;
            border: 1px solid rgba(0, 0, 0, 0.2);
            border-radius: 0.3rem;
            outline: 0;
        }

        .modal-backdrop {
            position: fixed;
            top: 0;
            right: 0;
            bottom: 0;
            left: 0;
            z-index: 0;
            background-color: #000;
        }

        .modal-backdrop.fade {
            opacity: 0;
        }

        .modal-backdrop.show {
            opacity: 0.5;
        }

        .modal-header {
            display: -webkit-box;
            display: -ms-flexbox;
            display: flex;
            -webkit-box-align: start;
            -ms-flex-align: start;
            align-items: flex-start;
            -webkit-box-pack: justify;
            -ms-flex-pack: justify;
            justify-content: space-between;
            padding: 1rem;
            border-radius: .357rem;
        }

        .modal-header .close {
            padding: 8px;
            margin: 0px 0px 0px auto;
        }

        .modal-title {
            margin-bottom: 0;
            line-height: 1.5;
        }

        .modal-body {
            position: relative;
            -webkit-box-flex: 1;
            -ms-flex: 1 1 auto;
            flex: 1 1 auto;
            padding: 1rem;
        }

        .modal-footer {
            display: -webkit-box;
            display: -ms-flexbox;
            display: flex;
            -webkit-box-align: center;
            -ms-flex-align: center;
            align-items: center;
            -webkit-box-pack: end;
            -ms-flex-pack: end;
            justify-content: flex-end;
            padding: 1rem;
            border-radius: .357rem;
        }

        .modal-footer > :not(:first-child) {
            margin-left: .25rem;
        }

        .modal-footer > :not(:last-child) {
            margin-right: .25rem;
        }

        .modal-scrollbar-measure {
            position: absolute;
            top: -9999px;
            width: 50px;
            height: 50px;
            overflow: scroll;
        }

        @media (min-width: 576px) {
            .modal-dialog {
                max-width: 500px;
                margin: 1.75rem auto;
            }
            .modal-dialog-centered {
                min-height: calc(100% - (1.75rem * 2));
            }
            .modal-sm {
                max-width: 300px;
            }
        }

        @media (min-width: 992px) {
            .modal-lg {
                max-width: 800px;
            }
        }


    </style>
    <body onload="time()" class="app sidebar-mini rtl">
        <!-- Navbar-->
        <header class="app-header">
            <!-- Sidebar toggle button--><a class="app-sidebar__toggle" href="#" data-toggle="sidebar"
                                            aria-label="Hide Sidebar"></a>
            <!-- Navbar Right Menu-->
            <ul class="app-nav">


                <!-- User Menu-->
                <li><a class="app-nav__item" href="dashboard"><i class='bx bx-log-out bx-rotate-180'></i> </a>

                </li>
            </ul>
        </header>
        <!-- Sidebar menu-->
        <div class="app-sidebar__overlay" data-toggle="sidebar"></div>
        <aside class="app-sidebar">
            <div class="app-sidebar__user"><img class="app-sidebar__user-avatar" src="admin/images/user.png" width="50px"
                                                alt="User Image">
                <div>
                    <p class="app-sidebar__user-name"><b>${sessionScope.user.user_name}</b></p>
                    <p class="app-sidebar__user-designation">Chào mừng bạn trở lại</p>
                </div>
            </div>
            <hr>
            <ul class="app-menu">
                <li><a class="app-menu__item" href="dashboard"><i class='app-menu__icon bx bx-tachometer'></i><span class="app-menu__label">Bảng thống kê</span></a></li>
                <li><a class="app-menu__item" href="categorymanager"><i class='app-menu__icon bx bxs-category'></i><span class="app-menu__label">Quản lý danh mục</span></a></li>
                <li><a class="app-menu__item" href="productmanager"><i class='app-menu__icon bx bx-purchase-tag-alt'></i><span class="app-menu__label">Quản lý sản phẩm</span></a></li>
                <li><a class="app-menu__item" href="ordermanager"><i class='app-menu__icon bx bx-task'></i><span class="app-menu__label">Quản lý đơn hàng</span></a></li>
                <li><a class="app-menu__item" href="blogmanager"><i class='app-menu__icon bx bx-task'></i><span class="app-menu__label">Quản lý blog</span></a></li>

                <!-- Conditionally Display Menu Items -->
                <c:if test="${sessionScope.user.isAdmin}">
                    <li><a class="app-menu__item" href="customermanager"><i class='app-menu__icon bx bx-user-voice'></i><span class="app-menu__label">Quản lý khách hàng</span></a></li>
                    <li><a class="app-menu__item" href="reportmanager"><i class='app-menu__icon bx bx-receipt'></i><span class="app-menu__label">Quản lý phản hồi</span></a></li>
                    <li><a class="app-menu__item" href="aboutmanager"><i class='app-menu__icon bx bx-receipt'></i><span class="app-menu__label">Quản lý trang giới thiệu</span></a></li>
                    <li><a class="app-menu__item" href="commentmanager"><i class='app-menu__icon bx bx-receipt'></i><span class="app-menu__label">Quản lý bình luận</span></a></li>
                    <li><a class="app-menu__item" href="couponmanager"><i class='app-menu__icon bx bx-receipt'></i><span class="app-menu__label">Quản lý coupon</span></a></li>
                    <li><a class="app-menu__item" href="blogmanager"><i class='app-menu__icon bx bx-task'></i><span class="app-menu__label">Quản lý blog</span></a></li>

                </c:if>
            </ul>
        </aside>
        <main class="app-content">
            <div class="app-title">
                <ul class="app-breadcrumb breadcrumb side">
                    <li class="breadcrumb-item active"><a href="#"><b>Danh sách phản hồi</b></a></li>
                </ul>
                <div id="clock"></div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="tile">
                        <div class="tile-body">
                            <div class="row element-button"
                                 <div class="col-sm-2">
                                <a class="btn btn-delete btn-sm print-file" type="button" title="In" onclick="myApp.printTable()"><i
                                        class="fas fa-print"></i> In dữ liệu</a>
                            </div>                           
                        </div>
                        <table class="table table-hover table-bordered js-copytextarea" cellpadding="0" cellspacing="0" border="0"
                               id="sampleTable">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Email khách hàng</th>
                                    <th>Tiêu đề phản hồi</th>
                                    <th>Nội dung phản hồi</th>
                                    <th>Chức năng</th>

                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${sessionScope.Reports}" var="u">
                                    <tr>
                                        <td>${u.id_report}</td>
                                        <td>${u.user_email}</td>
                                        <td>${u.subject_report}</td>
                                        <td>${u.content_report}</td>
                                        <td>
                                            <div style="width:100%; display: flex">
                                                <button class="btn btn-primary btn-sm edit" style="margin-right: 10px" type="button" data-toggle="modal" data-target="#Modal${u.id_report}" class="edit-icon" title="Chỉnh sửa">
                                                    <i class="fas fa-edit"></i></button>
                                                <button class="btn btn-primary btn-sm trash" type="button" title="xóa" value="${u.id_report}">
                                                    <i class="fas fa-trash-alt"></i></button></td>   
                                        </div>

                                    </tr>

                                <div class="modal fade" id="Modal${u.id_report}" tabindex="-1" role="dialog" aria-hidden="true">
                                    <div class="modal-dialog modal-dialog-centered" role="document">
                                        <div class="modal-content">
                                            <div class="modal-body" style="padding:0 1rem">
                                                <form id="contact-form" action="reportmanager" method="POST">
                                                    <input type="hidden" name="action" value="updatereport">
                                                    <div class="row">   
                                                        <div class="form-group col-md-12">
                                                            <span class="thong-tin-thanh-toan">
                                                                <h5>Mã phản hồi # ${u.id_report}</h5>
                                                            </span>
                                                        </div>
                                                    </div>
                                                    <div class="row" style="padding: 0 1rem; width:100%">
                                                        Phản hồi người dùng &nbsp; <b><i> ${u.user_email} </i></b>
                                                        <p> về nội dung <b>${u.subject_report}</b> </p>
                                                        <textarea name="report-send" style="margin-top: 10px; width:100%" rows="4" cols="50" placeholder="Trả lời phản hồi"></textarea>
                                                        <input style="font-weight: bolder" name="user_email" readonly type="text" hidden="true" value="Trả lời phản hồi: ${u.subject_report}">
                                                        <input style="font-weight: bolder" name="subject_report" readonly type="text" hidden="true" value="${u.user_email}">

                                                    </div>
                                                    <div style="margin-top: 10px; text-align: center">
                                                        <button class="btn btn-save"  onclick="handleSubmit()" type="submit">Gửi</button>
                                                        <a class="btn btn-cancel" data-dismiss="modal" href="#">Hủy bỏ</a>
                                                    </div>

                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
<div class="elfsight-app-94027198-7458-4351-8ca9-9bf86706a713" data-elfsight-app-lazy></div>
        </div>
    </main>



    <!-- Essential javascripts for application to work-->
    <script src="admin/js/jquery-3.2.1.min.js"></script>
    <script src="admin/js/popper.min.js"></script>
    <script src="admin/js/bootstrap.min.js"></script>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="admin/js/main.js"></script>
    <!-- The javascript plugin to display page loading on top-->
    <script src="admin/js/plugins/pace.min.js"></script>
    <!-- Page specific javascripts-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
    <!-- Data table plugin-->
    <script type="text/javascript" src="admin/js/plugins/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="admin/js/plugins/dataTables.bootstrap.min.js"></script>
    <script type="text/javascript">$('#sampleTable').DataTable();</script>
<script src="https://static.elfsight.com/platform/platform.js" data-use-service-core defer></script>

    <script>

        jQuery(document).ready(function () {
        // Event delegation for delete buttons
        jQuery(document).on('click', '.trash', function () {
        var reportId = $(this).attr("value");
        swal({
        title: "Cảnh báo",
                text: "Bạn có chắc chắn là muốn xóa phản hồi này?",
                buttons: ["Hủy bỏ", "Đồng ý"],
        }).then((willDelete) => {
        if (willDelete) {
        window.location = "reportmanager?action=delete&id_report=" + reportId;
        swal("Đã xóa thành công!", {
        icon: "success",
        });
        }
        });
        });
        });
        //Thời Gian
        function time() {
        var today = new Date();
        var weekday = new Array(7);
        weekday[0] = "Chủ Nhật";
        weekday[1] = "Thứ Hai";
        weekday[2] = "Thứ Ba";
        weekday[3] = "Thứ Tư";
        weekday[4] = "Thứ Năm";
        weekday[5] = "Thứ Sáu";
        weekday[6] = "Thứ Bảy";
        var day = weekday[today.getDay()];
        var dd = today.getDate();
        var mm = today.getMonth() + 1;
        var yyyy = today.getFullYear();
        var h = today.getHours();
        var m = today.getMinutes();
        var s = today.getSeconds();
        m = checkTime(m);
        s = checkTime(s);
        nowTime = h + " giờ " + m + " phút " + s + " giây";
        if (dd < 10) {
        dd = '0' + dd
        }
        if (mm < 10) {
        mm = '0' + mm
        }
        today = day + ', ' + dd + '/' + mm + '/' + yyyy;
        tmp = '<span class="date"> ' + today + ' - ' + nowTime +
                '</span>';
        document.getElementById("clock").innerHTML = tmp;
        clocktime = setTimeout("time()", "1000", "Javascript");
        
        function checkTime(i) {
        if (i < 10) {
        i = "0" + i;
        }
        return i;
        }
        }
        //In dữ liệu
        var myApp = new function () {
        this.printTable = function () {
        var tab = document.getElementById('sampleTable');
        var win = window.open('', '', 'height=700,width=700');
        win.document.write(tab.outerHTML);
        win.document.close();
        win.print();
        }
        }

        function handleSubmit(e) {
        const form = document.getElementById('contact-form');
        const formData = new FormData(form);
//
        // Lấy dữ liệu từ FormData
        const email = formData.get('user_email');
        const subject = formData.get('subject_report');
        const message = formData.get('report-send');
        // Tạo nội dung email
        var emailContent = 'Email: ' + email + '\n\n' + 'Subject: ' + subject + '\n\n' + 'Message: ' + message;
//
//        String apiKey = "912a01c7fbebca63277792ce4dcb2c1d-2b91eb47-fa7120ea"; // API Key
//            String domain = "fpt.edu.vn"; // domain
//            String url = "https://api.mailgun.net/v3/" + domain + "/messages";
//
//            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
//                HttpPost httpPost = new HttpPost(url);
//                StringEntity params = new StringEntity("from=dungnthe130159@" + domain + "&to=longmeo23699@gmail.com"+ "&subject=" + subject + "&text=" + message);
//                httpPost.setEntity(params);
//                httpPost.setHeader("Authorization", "Basic " + java.util.Base64.getEncoder().encodeToString(("api:" + apiKey).getBytes()));
//                httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
//
//                try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
//                    if (response.getCode() == 200) {
//                        out.println("<p>Email sent successfully!</p>"); 
//                    } else {
//                        out.println("<p>Failed to send email. Status code: " + response.getCode() + "</p>");
//                    }
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//                out.println("<p>Failed to send email: " + e.getMessage() + "</p>");
//            }
        // Hiển thị thông báo thành công
        alert("Gửi mail thành công");
        // Xóa dữ liệu trong form
        form.reset();
        // Trả về phản hồi
//            google.script.run.sendEmailFunction();
        }

        function sendEmailFunction(){

        }

    </script>
</body>
</html>
