<!DOCTYPE html>
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FPTU Camp mùa 5</title>
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
            padding: 20px;
        }
        .image-placeholder {
            text-align: center;
            margin: 20px 0;
        }
    </style>
</head>
<body>
    <jsp:include page="topnav.jsp" />
    <h1 class="mt-5">FPTU Camp mùa 5</h1>
    <div class="content">
        <p>
            FPTU Camp có sự góp mặt của dàn nghệ sĩ khách mời bước ra từ chương trình “Anh trai Say Hi”: Pháp Kiều, Dương Domic và Quang Hùng MasterD.
        </p>
        <p>
            Chuỗi hoạt động trải nghiệm dành cho học sinh THPT – FPTU Camp mùa 5 sẽ diễn ra ngày 30/3 tại Hà Nội...
        </p>
        <div class="image-placeholder">
            <img src="${pageContext.request.contextPath}/images/Poster_Nghe-si-chung-png.jpeg" alt=""/>
        </div>
        <p>
            Góp mặt tại sự kiện có 3 nghệ sĩ đang được giới trẻ yêu mến: Dương Domic, Pháp Kiều và Quang Hùng MasterD...
        </p>
        <p> Các anh trai sẽ mang đến không gian âm nhạc sôi động cho sân khấu FPTU Camp với những bản hit đang làm mưa làm gió suốt thời gian qua.

            Sự kiện còn có phần chia sẻ của khách mời là các diễn giả nổi tiếng, giúp học sinh có cơ hội học hỏi thêm kiến thức để sẵn sàng trở thành thủ lĩnh tương lai. Ngoài ra, các bạn sẽ được trải nghiệm văn hóa quốc tế tại 30 gian hàng đại diện cho các quốc gia, chơi trò chơi, thưởng thức ẩm thực, giao lưu văn hóa và kết nối cộng đồng. 

            FPTU Camp là chuỗi hoạt động thường niên dành cho các lớp trưởng, bí thư trường THPT trên toàn quốc do Trường Đại học FPT tổ chức. Tại đây, các bạn học sinh được tham gia rèn luyện, sáng tạo, gặp gỡ các nhà lãnh đạo để học hỏi, phát triển bản thân. 

            Tại FPTU Camp mùa 4 diễn ra đầu năm nay, các bạn trẻ được trải nghiệm nhiều hoạt động hấp dẫn như trang trí nón lá, làm cá chép, sáng tạo lồng đèn và trang trí lì xì, để rèn luyện sự khéo léo và phát huy tinh thần sáng tạo. Bên cạnh đó, các trò chơi dân gian như cướp cờ, ném còn, đua cà kheo và thả diều mang đến không gian sôi động, vừa giải trí vừa giúp các trại sinh nâng cao kỹ năng làm việc nhóm, rèn luyện sự khéo léo và tìm hiểu sâu hơn về các giá trị văn hóa truyền thống của dân tộc.</p>
    </div>
    <jsp:include page="footer.jsp" />
</body>
</html>
