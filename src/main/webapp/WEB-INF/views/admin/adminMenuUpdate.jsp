<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String ctxPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Dashboard">
    <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
    <title>Admin Menu page</title>
    <!-- Favicons -->
    <link href="<%=ctxPath%>/resources/images/favi.jpg" rel="icon">
    <!-- Bootstrap core CSS -->
    <link href="<%=ctxPath%>/resources/js/admin/lib/bootstrap/css/bootstrap.css" rel="stylesheet">
    <!--external css-->
    <link href="<%=ctxPath%>/resources/js/admin/lib/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="<%=ctxPath%>/resources/css/admin/css/zabuto_calendar.css">
    <link rel="stylesheet" type="text/css" href="<%=ctxPath%>/resources/js/admin/lib/gritter/css/jquery.gritter.css" />
    <!-- Custom styles for this template -->
    <link href="<%=ctxPath%>/resources/css/admin/css/style.css" rel="stylesheet">
    <link href="<%=ctxPath%>/resources/css/admin/css/style-responsive.css" rel="stylesheet">
    <script src="<%=ctxPath%>/resources/js/admin/lib/chart-master/Chart.js"></script>
    <!-- FONT -->
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<%=ctxPath%>/resources/css/tableUtil.css">
    <link rel="stylesheet" type="text/css" href="<%=ctxPath%>/resources/css/adminMenuUpdate.css">
    <!--===============================================================================================-->
</head>

<body>
    <section id="container">
        <!-- **********************************************************************************************************************************************************
        TOP BAR CONTENT & NOTIFICATIONS
        *********************************************************************************************************************************************************** -->
        <!--header start-->
        <header class="header black-bg">
            <div class="sidebar-toggle-box">
                <div class="fa fa-bars tooltips" data-placement="right" data-original-title="Toggle Navigation"></div>
            </div>
            <!--logo start-->
            <a href="<%=ctxPath%>/admin/adminMain.do" class="logo"><b>JABA</b></a>
            <!--logo end-->
            <div class="top-menu">
                <ul class="nav pull-right top-menu">
                    <li><button class="logout" id="logoutBtn">Logout</button></li>
                </ul>
            </div>
        </header>
        <!--header end-->
        <!-- **********************************************************************************************************************************************************
        MAIN SIDEBAR MENU
        *********************************************************************************************************************************************************** -->
        <!--sidebar start-->
        <aside>
            <div id="sidebar" class="nav-collapse ">
                <!-- sidebar menu start-->
                <ul class="sidebar-menu" id="nav-accordion">
                    <p class="centered"><img src="<%=ctxPath %>/resources/images/clogo1.png" width="80"></p>
                    <h5 class="centered">admin</h5>
                    <li class="mt">
                        <a href="<%=ctxPath%>/admin/adminMain.do">
                            <i class="fa fa-dashboard"></i>
                            <span>Main</span>
                        </a>
                    </li>
                    <li class="sub-menu">
                        <a href="javascript:;">
                            <i class="fa fa-desktop"></i>
                            <span>Client</span>
                        </a>
                        <ul class="sub">
                            <li><a href="<%=ctxPath%>/admin/adminClient.do">전체 회원 정보</a></li>
                            <li><a href="<%=ctxPath%>/admin/adminClient.do">회원 관리</a></li>
                        </ul>
                    </li>
                    <li class="sub-menu">
                        <a class="active" href="javascript:;">
                            <i class="fa fa-cogs"></i>
                            <span>Biz Store</span>
                        </a>
                        <ul class="sub">
                            <li><a href="<%=ctxPath%>/admin/store/selectAdminStore.do">매장 정보</a></li>
                            <li><a href="<%=ctxPath%>/admin/store/selectAdminStore.do">매장 선택</a></li>
                            <li><a href="<%=ctxPath%>/admin/store/selectAdminStore.do">매장 관리</a></li>
                        </ul>
                    </li>
                    <li class="sub-menu">
                        <a href="javascript:;">
                            <i class="fa fa-book"></i>
                            <span>Q & A</span>
                        </a>
                        <ul class="sub">
                            <li><a href="<%=ctxPath%>/admin/qna/qnaList.do">Q&A 관리</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="https://www.google.com/maps">
                            <i class="fa fa-map-marker"></i>
                            <span>Google Maps </span>
                        </a>
                    </li>
                </ul>
                <!-- sidebar menu end-->
            </div>
        </aside>
        <!--sidebar end-->
        <!-- **********************************************************************************************************************************************************
        MAIN CONTENT
        *********************************************************************************************************************************************************** -->
        <!--Qna List-->
        <section id="main-content">
            <section class="wrapper">
                <div class="col-lg-9 main-chart">
                    <!--CUSTOM CHART START -->
                    <div class="border-head">
                        <h3>MENU 수정</h3>
                    </div>
                    <div class="qna-custom-bar-chart">
                        <div class="limiter">
                            <div class="container-table100">
                                <div class="wrap-table100">
                                    <div class="table">
                                        <div class="trow theader" id="theader">
                                            <div class="cell">CATEGORY</div>
                                            <div class="cell">NAME</div>
                                            <div class="cell">PRICE</div>
                                            <div class="cell">DESCRIPTION</div>
                                        </div>
                                        <div class="trow">
                                            <div class="cell" data-title="CATEGORY">
                                                <input type="text" name="menu_category" id="menu_category" value="${menuDetail.menu_category }" />
                                            </div>
                                            <div class="cell" data-title="NAME">
                                                <input type="text" name="menu_name" id="menu_name" value="${menuDetail.menu_name }" />
                                            </div>
                                            <div class="cell" data-title="PRICE">
                                                <input type="text" name="menu_price" id="menu_price" value="${menuDetail.menu_price }" />
                                            </div>
                                            <div class="cell" data-title="DESCRIPTION">
                                                <input type="text" name="menu_description" id="menu_description" value="${menuDetail.menu_description }" />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="btnContainer" style="padding: 10px">
                                    <input type="button" value="완료" id="updateMenuBtn">
                                    <input type="button" value="삭제" id="deleteMenuBtn">
                                    <input type="button" value="돌아가기" onclick="javascript:location.href='<%=ctxPath%>/admin/store/adminMenuDetail.do?menu_id=${menuDetail.menu_id }'" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </section>
    </section>
    <!-- js placed at the end of the document so the pages load faster -->
    <script src="<%=ctxPath%>/resources/js/admin/lib/jquery/jquery.min.js"></script>
    <script src="<%=ctxPath%>/resources/js/admin/lib/bootstrap/js/bootstrap.js"></script>
    <script class="include" type="text/javascript" src="<%=ctxPath%>/resources/js/admin/lib/jquery.dcjqaccordion.2.7.js"></script>
    <script src="<%=ctxPath%>/resources/js/admin/lib/jquery.scrollTo.min.js"></script>
    <script src="<%=ctxPath%>/resources/js/admin/lib/jquery.nicescroll.js" type="text/javascript"></script>
    <script src="<%=ctxPath%>/resources/js/admin/lib/jquery.sparkline.js"></script>
    <!--common script for all pages-->
    <script src="<%=ctxPath%>/resources/js/admin/lib/common-scripts.js"></script>
    <script type="text/javascript" src="<%=ctxPath%>/resources/js/admin/lib/gritter/js/jquery.gritter.js"></script>
    <script type="text/javascript" src="<%=ctxPath%>/resources/js/admin/lib/gritter-conf.js"></script>
    <!--script for this page-->
    <script src="<%=ctxPath%>/resources/js/admin/lib/sparkline-chart.js"></script>
    <script src="<%=ctxPath%>/resources/js/admin/lib/zabuto_calendar.js"></script>
    <script type="application/javascript">
        $(document).ready(function() {
            $("#date-popover").popover({
                html: true,
                trigger: "manual"
            });
            $("#date-popover").hide();
            $("#date-popover").click(function(e) {
                $(this).hide();
            });

            function myNavFunction(id) {
                $("#date-popover").hide();
                var nav = $("#" + id).data("navigation");
                var to = $("#" + id).data("to");
                console.log('nav ' + nav + ' to: ' + to.month + '/' + to.year);
            }
        });
    </script>
    <script type="text/javascript" id="rendered-js">
        // 로그아웃 버튼 눌렀을때 버튼 hide 와 로그아웃 동작
        $("#logoutBtn").on("click", function() {
            $.ajax({
                url: "<%=ctxPath%>/client/clientLogout.do",
                data: {},
                success: function() {
                    $("#logoutBtn").hide();
                    location.href = "../../";
                }
            });
        });
    </script>
    <script type="text/javascript">
        //상태 변경시 ajax
        $("#updateMenuBtn").on("click", function() {
            var menu_id = "${menuDetail.menu_id}";
            var menu_name = $("#menu_name").val();
            var menu_price = $("#menu_price").val();
            var menu_img = $("#menu_img").val();
            var menu_description = $("#menu_description").val();
            var menu_category = $("#menu_category").val();

            console.log(menu_name);
            if (confirm("현재 메뉴의 정보를 변경하시겠습니까?")) {
                $.ajax({
                    url: "<%=ctxPath%>/admin/store/updateMenu.do",
                    method: "POST",
                    data: {

                        menu_name: menu_name,
                        menu_price: menu_price,
                        menu_img: menu_img,
                        menu_description: menu_description,
                        menu_category: menu_category
                    },
                    success: function() {

                        console.log("메뉴 변경 성공!");
                        location.href = "<%=ctxPath%>/admin/store/adminMenuDetail.do?menu_id=" + menu_id;
                    }
                });
            }
        });

        // 메뉴 삭제
        $("#deleteMenuBtn").on("click", function() {
            var menu_id = "${menuDetail.menu_id}";
            var store_name = "${storeDetail.store_name}";

            if (confirm("현재 메뉴의 정보를 삭제하시겠습니까?")) {
                $.ajax({
                    url: "<%=ctxPath%>/admin/store/menuDelete.do",
                    method: "POST",
                    data: {},
                    success: function() {
                        console.log("메뉴 삭제 성공!");
                        location.href = "<%=ctxPath%>/admin/store/adminStoreDetail.do?store_name=" + store_name;
                    }
                });
            }
        });
    </script>
</body>
</html>