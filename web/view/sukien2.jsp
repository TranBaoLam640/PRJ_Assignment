<!DOCTYPE html>
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Triển lãm Cung đàn đất nước</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            line-height: 1.6;
        }
        .content {
            padding: 40px 20px;
        }
        .image-placeholder {
            text-align: center;
            margin: 20px 0;
        }
        .image-placeholder img {
            max-width: 100%;
            height: auto;
            border-radius: 10px;
        }
    </style>
</head>
<body>
    <jsp:include page="topnav.jsp" />
    <div class="container mt-5">
        <h1 class="mt-5 text-center">Trường ĐH FPT tôn vinh âm nhạc dân tộc qua triển lãm Cung đàn đất nước</h1>
        <p class="text-center text-muted">Thứ Ba, ngày 11 tháng 03 năm 2025</p>
        
        <div class="content">
            <p>
                Triển lãm nhạc cụ dân tộc – Cung đàn đất nước mùa 5 là nơi trưng bày những nhạc cụ dân tộc quý, đồng thời tạo cơ hội để sinh viên và những người yêu âm nhạc khám phá sâu hơn về không gian văn hóa Cồng chiêng Tây Nguyên – di sản phi vật thể độc đáo của Việt Nam.
            </p>
            
            <div class="image-placeholder">
               <img src="${pageContext.request.contextPath}/images/482062445_615748037948150_2040034552313364487_n.jpeg" alt="Triển lãm cung đàn đất nước"/>
            </div>
            
            <p>
                Từ ngày 13/3 đến 21/3, Trường Đại học FPT sẽ trở thành nơi trưng bày hàng trăm chiếc đàn cổ mang giá trị lịch sử của Việt Nam và nhiều quốc gia Châu Á...
            </p>
            
            <p>
                Bên cạnh khu vực triển lãm nhạc cụ truyền thống và tư liệu về âm nhạc dân gian Việt Nam, “Cung đàn đất nước” mùa 5 mang đến nhiều hoạt động nghệ thuật, trong đó nổi bật là talkshow chuyên đề “Không gian văn hóa cồng chiêng Tây Nguyên”...
            </p>
            
            <p>
                Triển lãm này còn mang đến những tiết mục âm nhạc dân gian kết hợp trình diễn thời trang dân tộc Trong đó, những bộ trang phục truyền thống từ nhiều vùng miền, thể hiện vẻ đẹp tinh tế qua từng đường nét hoa văn và phản ánh sự đa dạng văn hóa của các dân tộc trên khắp Việt Nam. Kết hợp cùng âm nhạc dân gian vừa là hoạt động tôn vinh bản sắc truyền thống, vừa thổi làn gió hiện đại vào các giá trị văn hóa lâu đời.
            </p>
            
            <p>
                Với sự kết hợp hài hòa giữa triển lãm, biểu diễn nghệ thuật và đối thoại chuyên sâu, “Cung đàn đất nước” mùa 5 vừa là sự kiện văn hóa vừa là cầu nối đưa sinh viên đến gần hơn với kho tàng âm nhạc dân tộc.
            </p>
            <p>Triển lãm Cung đàn đất nước là sự kiện thường niên do Trường Đại học FPT tổ chức nhằm tôn vinh và lan tỏa giá trị của âm nhạc truyền thống Việt Nam, đặc biệt là nghệ thuật biểu diễn các nhạc cụ dân tộc. Qua 4 mùa, sự kiện tiếp tục mang đến không gian trải nghiệm văn hóa cho sinh viên mà đồng thời tạo cơ hội giao lưu với các nghệ sĩ, chuyên gia trong lĩnh vực âm nhạc dân gian.
Năm 2024, triển lãm Cung đàn đất nước thu hút hơn 100 sinh viên tham gia, với hai hoạt động chính: triển lãm trưng bày hơn 100 các loại nhạc cụ truyền thống, và hội thảo chuyên đề về đàn bầu. Tại hội thảo, hai nghệ sĩ nổi tiếng là nghệ sĩ Toàn Thắng và nghệ sĩ Hà Chương đã có những chia sẻ sâu sắc về lịch sử, kỹ thuật biểu diễn và sự phát triển của đàn bầu trong nền âm nhạc đương đại.</p>
        </div>
    </div>
    
    <jsp:include page="footer.jsp" />
</body>
</html>
