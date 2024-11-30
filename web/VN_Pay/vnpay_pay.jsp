<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Tạo mới đơn hàng</title>
        <!-- Bootstrap core CSS -->
        <link href="assets/bootstrap.min.css" rel="stylesheet"/>
        <!-- Custom styles for this template -->
        <link href="assets/jumbotron-narrow.css" rel="stylesheet">      
        <script src="assets/jquery-1.11.3.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <style>
            body {
                background-color: #f8f9fa;
            }
            .card {
                transition: all 0.3s ease-in-out;
            }
            .card:hover {
                transform: translateY(-5px);
                box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            }
            .form-check-input:checked {
                background-color: #0d6efd;
                border-color: #0d6efd;
            }
            body {
                background-color: #f8f9fa;
                font-family: 'Arial', sans-serif;
            }
            .card {
                transition: all 0.3s ease-in-out;
                border: none;
                border-radius: 15px;
            }
            .card:hover {
                transform: translateY(-5px);
                box-shadow: 0 10px 20px rgba(0,0,0,0.1);
            }
            .form-check-input:checked {
                background-color: #0d6efd;
                border-color: #0d6efd;
            }
            .btn-primary {
                background-color: #0d6efd;
                border-color: #0d6efd;
                padding: 10px 30px;
                font-size: 1.1em;
            }
            .btn-primary:hover {
                background-color: #0b5ed7;
                border-color: #0b5ed7;
            }
            .text-primary {
                color: #0d6efd !important;
            }
            .header-gradient {
                background: linear-gradient(45deg, #0d6efd, #fd7e14);
                -webkit-background-clip: text;
                background-clip: text;
                color: transparent;
            }

            .header-subtitle {
                color: #6c757d;
            }
            body {
                background: linear-gradient(135deg, #e6f2ff 0%, #fff5e6 100%);
                font-family: 'Arial', sans-serif;
                min-height: 100vh;
            }

            .container {
                background-color: rgba(255, 255, 255, 0.8);
                border-radius: 20px;
                box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
                padding: 40px;
            }

            .header-gradient {
                background: linear-gradient(45deg, #0d6efd, #fd7e14);
                -webkit-background-clip: text;
                background-clip: text;
                color: transparent;
            }

            .header-subtitle {
                color: #6c757d;
            }

            .card {
                background-color: rgba(255, 255, 255, 0.9);
            }
            .header-bg {
                background: linear-gradient(135deg, #0d6efd, #fd7e14);
                padding: 30px;
                border-radius: 15px;
                margin-bottom: 40px;
                box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
            }

            .header-title {
                color: white;
                text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.2);
            }

            .header-subtitle {
                color: rgba(255, 255, 255, 0.9);
            }
        </style>
    </head>

    <body>

        <div class="container py-5">
            <header class="text-center mb-5 header-bg">
                <h1 class="display-4 mb-3 header-title">
                    <i class="fas fa-store me-3"></i>
                    Shop You and Me
                </h1>
                <h2 class="h3 header-subtitle">
                    VNPAY Payment Gateway
                </h2>
            </header>

            <div class="row justify-content-center">
                <div class="col-lg-8">
                    <div class="card shadow">
                        <div class="card-body p-5">
                            <h2 class="card-title text-center mb-4">Tạo mới đơn hàng</h2>
                            <form action="ajaxServlet" id="frmCreateOrder" method="post">
                                <div class="mb-4">
                                    <label for="billId" class="form-label">Mã đơn hàng</label>
                                    <input type="text" class="form-control" id="billId" value="${requestScope.billId}" name="billId" readonly>
                                </div>
                                <div class="mb-4">
                                    <label for="amount" class="form-label">Số tiền</label>
                                    <input type="number" class="form-control" id="amount" name="amount" value="${requestScope.total}" readonly>
                                </div>

                                <h4 class="mb-3">Chọn phương thức thanh toán</h4>
                                <div class="mb-4">
                                    <div class="form-check mb-2">
                                        <input class="form-check-input" type="radio" name="bankCode" id="vnpayqr" value="" checked>
                                        <label class="form-check-label" for="vnpayqr">Cổng thanh toán VNPAYQR</label>
                                    </div>
                                    <div class="form-check mb-2">
                                        <input class="form-check-input" type="radio" name="bankCode" id="vnpayqr_app" value="VNPAYQR">
                                        <label class="form-check-label" for="vnpayqr_app">Thanh toán bằng ứng dụng hỗ trợ VNPAYQR</label>
                                    </div>
                                    <div class="form-check mb-2">
                                        <input class="form-check-input" type="radio" name="bankCode" id="vnbank" value="VNBANK">
                                        <label class="form-check-label" for="vnbank">Thanh toán qua thẻ ATM/Tài khoản nội địa</label>
                                    </div>
                                    <div class="form-check mb-2">
                                        <input class="form-check-input" type="radio" name="bankCode" id="intcard" value="INTCARD">
                                        <label class="form-check-label" for="intcard">Thanh toán qua thẻ quốc tế</label>
                                    </div>
                                </div>

                                <h4 class="mb-3">Chọn ngôn ngữ giao diện thanh toán</h4>
                                <div class="mb-4">
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="language" id="lang_vn" value="vn" checked>
                                        <label class="form-check-label" for="lang_vn">Tiếng Việt</label>
                                    </div>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="language" id="lang_en" value="en">
                                        <label class="form-check-label" for="lang_en">Tiếng Anh</label>
                                    </div>
                                </div>

                                <div class="text-center">
                                    <button type="submit" class="btn btn-primary btn-lg">
                                        <i class="fas fa-credit-card me-2"></i>Thanh toán
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <footer class="text-center mt-5">
                <p>&copy; VNPAY 2020-2024</p>
            </footer>
        </div>


        <link href="https://pay.vnpay.vn/lib/vnpay/vnpay.css" rel="stylesheet" />
        <script src="https://pay.vnpay.vn/lib/vnpay/vnpay.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <script type="text/javascript">
            $("#frmCreateOrder").submit(function () {
                var postData = $("#frmCreateOrder").serialize();
                var submitUrl = $("#frmCreateOrder").attr("action");
                $.ajax({
                    type: "POST",
                    url: submitUrl,
                    data: postData,
                    dataType: 'JSON',
                    success: function (x) {
                        if (x.code === '00') {
                            if (window.vnpay) {
                                vnpay.open({width: 768, height: 600, url: x.data});
                            } else {
                                location.href = x.data;
                            }
                            return false;
                        } else {
                            alert(x.Message);
                        }
                    }
                });
                return false;
            });
        </script>       
    </body>
</html>