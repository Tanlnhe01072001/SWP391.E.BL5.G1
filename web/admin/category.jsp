<%-- 
    Document   : category
    Created on : Jun 9, 2024, 3:37:12 PM
    Author     : ZZ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danh sách danh mục sản phẩm | Quản trị Admin</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Main CSS-->
        <link rel="stylesheet" type="text/css" href="admin/main.css">

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
        or 
        <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">

        <link rel="stylesheet" type="text/css"
              href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
    </head>
    <body class="app sidebar-mini rtl">
        <!-- Sidebar menu-->
        <div class="app-sidebar__overlay" data-toggle="sidebar"></div>
        <aside class="app-sidebar">
            <div class="app-sidebar__user"><img class="app-sidebar__user-avatar" src="admin/images/user.png" width="50px"
                                                alt="User Image">
                <div>
                    <p class="app-sidebar__user-name"><b>${sessionScope.user.user_name}</b></p>
                    <p class="app-sidebar__user-designation"> Welcome </p>
                </div>
            </div>
            <hr>
            <ul class="app-menu">
                <li><a class="app-menu__item" href="categorymanager"><i class='app-menu__icon bx bxs-category'></i><span class="app-menu__label">Quản lý danh mục</span></a></li>
                <li><a class="app-menu__item" href="productmanager"><i class='app-menu__icon bx bx-purchase-tag-alt'></i><span class="app-menu__label">Quản lý sản phẩm</span></a></li>
                <!-- Conditionally Display Menu Items -->
                <c:if test="${sessionScope.user.isAdmin}">
                    <li><a class="app-menu__item" href="customermanager"><i class='app-menu__icon bx bx-user-voice'></i><span class="app-menu__label">Quản lý khách hàng</span></a></li>
                </c:if>
            </ul>
        </aside>
        <main class="app-content">
            <div class="row">
                <div class="col-md-12">
                    <div class="tile">
                        <div class="tile-body">
                            <div>
                                <a class="btn btn-add btn-sm" data-toggle="modal" data-target="#adddanhmuc"><i
                                        class="fas fa-folder-plus"></i> Thêm danh mục</a>
                            </div>
                        </div>
                        <table class="table table-hover table-bordered" id="sampleTable">
                            <thead>
                                <tr>
                                    <th>ID danh mục</th>
                                    <th>Tên danh mục</th>
                                    <th>Chức năng</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${category}" var="c">
                                    <tr>
                                        <td>${c.category_id}</td>
                                        <td>${c.category_name}</td>
                                        <td>
                                            <%-- button sua xoa danh muc --%> 
                                            <button class="btn btn-primary btn-sm edit" type="button" data-toggle="modal" data-target="#ModalEditCategory${c.category_id}" title="Sửa"><i class="fas fa-edit"></i></button>
                                            <button class="btn btn-primary btn-sm trash" type="button" title="xóa" value="${c.category_id}">
                                                <i class="fas fa-trash"></i></button></td>

                                    </tr>
                                    <%-- sua danh muc --%> 
                                <div class="modal fade" id="ModalEditCategory${c.category_id}" tabindex="-1" role="dialog" aria-hidden="true">
                                    <div class="modal-dialog modal-dialog-centered" role="document">
                                        <div class="modal-content">
                                            <div class="modal-body">
                                                <form action="categorymanager" method="POST">
                                                    <input type="hidden" name="action" value="updatecategory">
                                                    <div class="row">
                                                        <div class="form-group col-md-12">
                                                            <span class="thong-tin-thanh-toan">
                                                                <h5>Chỉnh sửa danh mục</h5>
                                                            </span>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="form-group col-md-12">
                                                            <label class="control-label">ID</label>
                                                            <input class="form-control" type="text" readonly name="category_id" value="${c.category_id}">
                                                        </div>
                                                        <div class="form-group col-md-12">
                                                            <label class="control-label">Tên danh mục</label>
                                                            <input class="form-control" type="text" name="category_name" value="${c.category_name}">
                                                        </div>
                                                    </div>
                                                    <button class="btn btn-save" type="submit">Lưu lại</button>
                                                    <a class="btn btn-cancel" data-dismiss="modal" href="#">Hủy bỏ</a>
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
        </main>



                    <%-- add category --%>
    <div class="modal fade" id="adddanhmuc" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
         data-backdrop="static" data-keyboard="false">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">

                <div class="modal-body">
                    <div class="row">
                        <div class="form-group  col-md-12">
                            <span class="thong-tin-thanh-toan">
                                <h5>Thêm mới danh mục </h5>
                            </span>
                        </div>

                        <div class="form-group col-md-12" >

                            <h2 style="color: red; padding-left: 10px">
                                ${error}</h2>
                            <label class="control-label">Nhập tên danh mục mới</label>
                            <form action="categorymanager?action=insertcategory" method="post"> 
                                <input class="form-control" type="text" name="name" required>
                                <br>
                                <button class="btn btn-save" type="submit">Lưu lại</button>
                                <a class="btn btn-cancel" data-dismiss="modal" href="#">Hủy bỏ</a>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

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
    <script>
        
        jQuery(document).ready(function () {
            // Event delegation for delete buttons
            jQuery(document).on('click', '.trash', function () {
                var categoryId = $(this).attr("value");
                swal({
                    title: "Cảnh báo",
                    text: "Bạn có chắc chắn là muốn xóa danh mục này?",
                    buttons: ["Hủy bỏ", "Đồng ý"],
                }).then((willDelete) => {
                    if (willDelete) {
                        window.location = "categorymanager?action=delete&category_id=" + categoryId;
                        swal("Đã xóa thành công!", {
                            icon: "success",
                        });
                    }
                });
            });
        });
    </script>
</body>

</html>
