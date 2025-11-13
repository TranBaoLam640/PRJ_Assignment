<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="model.Users" %>

<%
    HttpSession sess = request.getSession();
    String username = (String) sess.getAttribute("username");
%>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý CLB Sinh viên</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>

    <jsp:include page="topnav.jsp"/>

    <!-- Banner -->
    <section class="banner">
        <div class="banner-overlay">
            <h1>Câu lạc bộ sinh viên Trường đại học FPT</h1>
        </div>
    </section>

    <!-- Danh sách CLB -->
   <div class="container mt-4">
    <div class="row">
        <div class="col-md-3">
            <div class="club-card">
                <img src="${pageContext.request.contextPath}/images/481198455_1209867824473954_2055890745914236479_n.jpg" alt=""/>
                <h3>FPT Debate Club</h3>
                <a href="SignUpCLB?clubName=FPT Debate Club" class="btn btn-primary">Tham gia</a>
            </div>
        </div>
        <div class="col-md-3">
            <div class="club-card">
                <img src="${pageContext.request.contextPath}/images/308534732_468653611944882_8520618178820523664_n.jpg" alt=""/>
                <h3>FPT Cinema Club</h3>
                <a href="SignUpCLB?clubName=FPT Cinema Club" class="btn btn-primary">Tham gia</a>
            </div>
        </div>
        <div class="col-md-3">
            <div class="club-card">
                <img src="${pageContext.request.contextPath}/images/475918557_1024689949693828_729143456773018299_n.jpg" alt=""/>
                <h3>FPT Chinese Club</h3>
                <a href="SignUpCLB?clubName=FPT Chinese Club" class="btn btn-primary">Tham gia</a>
            </div>
        </div>
        <div class="col-md-3">
            <div class="club-card">
                <img src="${pageContext.request.contextPath}/images/327255598_632328895316637_6415047431246972880_n.png" alt=""/>
                <h3>FPT Student Council</h3>
                <a href="SignUpCLB?clubName=FPT Student Council" class="btn btn-primary">Tham gia</a>
            </div>
        </div>
        <div class="col-md-3">
            <div class="club-card">
                <img src="${pageContext.request.contextPath}/images/481245210_1271230464550774_6830923312676516722_n.jpg" alt=""/>
                <h3>FPT Melody Club</h3>
                <a href="SignUpCLB?clubName=FPT Melody Club" class="btn btn-primary">Tham gia</a>
            </div>
        </div>
        <div class="col-md-3">
            <div class="club-card">
                <img src="${pageContext.request.contextPath}/images/474623663_1024352286376293_4823173041078258660_n.jpg" alt=""/>
                <h3>FPT StreetWorkout Club</h3>
                <a href="SignUpCLB?clubName=FPT StreetWorkout Club" class="btn btn-primary">Tham gia</a>
            </div>
        </div>
        <div class="col-md-3">
            <div class="club-card">
                <img src="${pageContext.request.contextPath}/images/305496933_514422220685414_5609223152128856149_n.jpg" alt=""/>
                <h3>FPT Vovinam Club</h3>
                <a href="SignUpCLB?clubName=FPT Vovinam Club" class="btn btn-primary">Tham gia</a>
            </div>
        </div>
        <div class="col-md-3">
            <div class="club-card">
                <img src="${pageContext.request.contextPath}/images/475217187_1148160440642955_4237997588952778342_n.jpg" alt=""/>
                <h3>FPT Badminton Club</h3>
                <a href="SignUpCLB?clubName=FPT Badminton Club" class="btn btn-primary">Tham gia</a>
            </div>
        </div>
    </div>
</div>

<!-- Tin tức & Sự kiện -->
<section class="news-section">
    <div class="container">
        <h2 class="section-title text-center">SỰ KIỆN</h2>
        <div class="row">
            <div class="col-md-4">
                <div class="news-card">
                    <img src="${pageContext.request.contextPath}/images/poster-1229x1536.jpeg" alt=""/>
                    <div class="news-content">
                        <h3>
                            <a href="view/sukien1.jsp" class="text-decoration-none text-dark">
                                FPT AI-Conic 2025: sân chơi sáng tạo nội dung bằng AI
                            </a>
                        </h3>

                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="news-card">
                    <img src="${pageContext.request.contextPath}/images/482062445_615748037948150_2040034552313364487_n.jpeg" alt=""/>
                    <div class="news-content">
                        <h3>
                            <a href="view/sukien2.jsp" class="text-decoration-none text-dark">
                                Triển lãm Cung đàn đất nước
                            </a>
                            
                            
                        </h3>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="news-card">
                    <img src="${pageContext.request.contextPath}/images/Poster_Nghe-si-chung-png.jpeg" alt=""/>
                    <div class="news-content">
                                           <h3>
                            <a href="view/sukien3.jsp" class="text-decoration-none text-dark">
                                3 "Anh trai Say Hi" góp mặt tại FPTU Camp
                            </a>
                            
                            
                        </h3>          
                            
                      
                    </div>
                </div>
            </div>
        </div>
        
    </div>
</section>

<!-- Footer -->
<jsp:include page="footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script> <!<!-- Bắt buộc phải có  -->

</body>
</html>