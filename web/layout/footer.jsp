<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Online Shopping Footer</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <style>
            footer {
                font-family: 'Arial', sans-serif;
            }

            footer h5 {
                font-weight: bold;
                border-bottom: 2px solid #fff;
                padding-bottom: 10px;
                margin-bottom: 20px;
            }

            footer a {
                text-decoration: none;
                transition: color 0.3s ease;
            }

            footer a:hover {
                color: #ffc107 !important;
            }

            .social-icon {
                font-size: 1.2rem;
            }

            @media (max-width: 768px) {
                footer .col-md-3 {
                    text-align: center;
                }
            }
        </style>
    </head>
    <body>
        <div class="map-area">                         
            <iframe id="googleMap" src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3131.9208139151415!2d105.51924235129374!3d21.01256992523427!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3135abc60e7d3f19%3A0x2be9d7d0b5abcbf4!2zVHLGsOG7nW5nIMSQ4bqhaSBo4buNYyBGUFQgSMOgIE7hu5lp!5e0!3m2!1svi!2s!4v1718041269304!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
        </div>
        <footer class="bg-dark text-light py-5">
            <div class="container">
                <div class="row">
                    <!-- Company Info -->
                    <div class="col-md-3 mb-4">
                        <h5 class="text-uppercase mb-4">Cửa hàng quần áo Shopping</h5>
                        <p>Mã Số Thuế: 0106486365</p>
                        <p>Văn phòng: Alpha - Đại học FPT Hà Nội</p>
                    </div>

                    <!-- Newsletter -->
                    <div class="col-md-3 mb-4">
                        <h5 class="text-uppercase mb-4">Bản tin</h5>
                        <form>
                            <div class="input-group mb-3">
                                <input type="email" class="form-control" placeholder="Nhập email của bạn" aria-label="Email" aria-describedby="button-addon2">
                                <button class="btn btn-outline-light" type="submit" id="button-addon2">Đăng ký</button>
                            </div>
                        </form>
                    </div>

                    <!-- Company Links -->
                    <div class="col-md-3 mb-4">
                        <h5 class="text-uppercase mb-4">Công ty</h5>
                        <ul class="list-unstyled">
                            <li><a href="about.jsp" class="text-light">Giới thiệu về You&Me</a></li>
                            <li><a href="https://the31.vn/" class="text-light">THE 31</a></li>
                            <li><a href="https://jobs.routine.vn/" class="text-light">Tuyển dụng</a></li>
                            <li><a href="/tin-thoi-trang" class="text-light">Tin thời trang</a></li>
                            <li><a href="https://routine.vn/franchise" class="text-light">Hợp tác nhượng quyền</a></li>
                            <li><a href="/thong-tin-lien-he" class="text-light">Liên hệ</a></li>
                        </ul>
                    </div>

                    <!-- Social Media -->
                    <div class="col-md-3 mb-4">
                        <h5 class="text-uppercase mb-4">Kết nối với chúng tôi</h5>
                        <div class="d-flex flex-wrap">
                            <a href="https://www.facebook.com/routinevn" class="btn btn-outline-light me-2 mb-2" target="_blank" rel="nofollow noopener">
                                <i class="fab fa-facebook-f"></i>
                            </a>
                            <a href="https://www.instagram.com/routine_official/" class="btn btn-outline-light me-2 mb-2" target="_blank" rel="nofollow noopener">
                                <i class="fab fa-instagram"></i>
                            </a>
                            <a href="https://zalo.me/routinevn" class="btn btn-outline-light me-2 mb-2" target="_blank" rel="nofollow noopener">
                                Zalo
                            </a>
                            <a href="https://www.youtube.com/@routinevn" class="btn btn-outline-light me-2 mb-2" target="_blank" rel="nofollow noopener">
                                <i class="fab fa-youtube"></i>
                            </a>
                            <a href="https://www.tiktok.com/@routine_vietnam" class="btn btn-outline-light me-2 mb-2" target="_blank" rel="nofollow noopener">
                                <i class="fab fa-tiktok"></i>
                            </a>
                        </div>
                    </div>
                </div>

                <hr class="my-4">

                <!-- Bottom Footer -->
                <div class="row align-items-center">
                    <div class="col-md-6 text-center text-md-start">
                        <p>&copy; 2024 Shopping Vietnam. All rights reserved.</p>
                    </div>
                    <div class="col-md-6 text-center text-md-end">
                        <img src="path_to_certification_image.png" alt="Chứng nhận đã khai báo với Bộ Công Thương" width="153" height="58">
                    </div>
                </div>
            </div>
        </footer>

    </body>
</html>