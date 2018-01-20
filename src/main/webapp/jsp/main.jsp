<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Home page</title>
    <jsp:include page="header.jsp"/>
    <link rel="stylesheet" href="/bootstrap/css/main.css">
    <link rel="stylesheet" href="/bootstrap/css/adminpage.css">
</head>


<body class="bg">
<%--<jsp:useBean id="userBean" class="com.khelenyuk.model.User"></jsp:useBean>--%>
<%--<jsp:useBean id="productBean" class="com.khelenyuk.model.Product"></jsp:useBean>--%>
<%--<jsp:useBean id="menuBean" class="com.khelenyuk.model.Meal"></jsp:useBean>--%>
<%--<jsp:useBean id="localDate" class="java.time.LocalDate"></jsp:useBean>--%>

<%--<c:set var="user" value="${userBean.user}"/>--%>
<%--<c:set var="products" value="${productBean.products}"/>--%>
<%--<c:set var="menu" value="${menuBean.menu}"/>--%>
<%--<c:set var="chosenDateSession" value="${menu.date}"/>--%>


<div class="container bg-container">
    <jsp:include page="headerNavbar.jsp"></jsp:include>

    <br/>
    <%----------- JUMBOTRONs ------------%>

    <div class="container container-fluid table-bordered">
        <div class="row">
            <div class="col-4 jumbotron jumbotron1">
                <div class="hello">Hello, ${user.firstName}!</div>
                <div class="on_track">You are on track today!</div>
                <div class="still_to_go_text"><span class="still_to_go_number">15 kg </span>to your goal weight</div>
                <div class="still_to_go_text"><span class="still_to_go_number">500 calories </span>left to spend today
                </div>
                <div class="still_to_go_text"><span class="still_to_go_number">4 glasses </span> of water left to drink
                </div>
            </div>
            <div class="col jumbotron jumbotron2 text-center">
                <div class="your-daily-summary">Your Daily Summary</div>
                <table class="table text-center borderless">
                    <thead>
                    <tr class="your-daily-summary-numbers your-daily-summary-table">
                        <th class="">1,900</th>
                        <th>-</th>
                        <th>${totalDayCalories}</th>
                        <th>+</th>
                        <th>500</th>
                        <th>=</th>
                        <th class="remaining">1,400</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr class="main-caption">
                        <td>GOAL</td>
                        <td></td>
                        <td>FOOD</td>
                        <td></td>
                        <td>ACTIVITY</td>
                        <td></td>
                        <td>REMAINING</td>
                    </tr>
                    </tbody>
                </table>
                <div>
                    <form name="calendarForm" method="post" action="controller">
                        <input type="hidden" name="command" value="Select_date">
                        <input type="date" name="chosenDate" value="${chosenDateSession}"/>

                        <input type="submit" class="button" value="Select Date">
                    </form>
                </div>
            </div>
        </div>
    </div>


    <div class="container table-bordered">

        <ul class="nav nav-tabs nav-justified">
            <li class="nav-item"><a class="nav-link" role="tab" data-toggle="tab" href="#food">Food</a>
            </li>
            <li class="nav-item"><a class="nav-link active" role="tab" data-toggle="tab" href="#activity">Activity</a>
            </li>
            <li class="nav-item"><a class="nav-link" role="tab" data-toggle="tab" href="#water">Water</a>
            </li>
            <li class="nav-item"><a class="nav-link" role="tab" data-toggle="tab" href="#weight_monitor">Weight
                monitor</a>
            </li>
        </ul>


        <div class="tab-content table-bordered">
            <br/><br/>

            <%---------- FOOD -----------%>
            <div class="tab-pane" id="food" role="tabpanel">


                <%---------- FOOD -----------%>
                <div class="container container-fluid table-bordered">
                    <form action="/controller" method="post">


                        <%---------------   select product  ---------------%>
                        <div class="row ">
                            <%----------------   SELECT PRODUCT   -----------------%>
                            <div class="col">
                                <div class="main-caption">SELECT PRODUCT</div>

                                <%------------  Choose product ------------%>
                                <select class="form-control" name="product_id">
                                    <c:forEach var="product" items="${products}">
                                        <option value="${product.id}">
                                            <c:out value="${product.name}"/>
                                        </option>
                                    </c:forEach>
                                </select>

                                <%------------- Add New product ------------%>
                                <div class="form-inline font12">
                                    <label>Can't find a product?</label>
                                    <button type="submit" class="btn btn-link font12" name="command"
                                            value="TO_ADD_PRODUCT_PAGE">Add new
                                    </button>
                                </div>
                            </div>

                            <%-----------------   ENTER QUANTITY CONSUMED   ------------------%>
                            <div class="col">
                                <%---------- Enter product amount --------------%>
                                <div class="main-caption">ENTER QUANTITY (grams)</div>
                                <input class="form-control" type="number" name="weight" step="1" min="1" max="999"
                                       value="100">

                            </div>

                            <div class="col">
                                <div class="main-caption">SELECT MEAL</div>

                                <%------------  Choose meal type ------------%>
                                <select class="form-control" name="meal_type_id">
                                    <c:forEach var="meal_type" items="${mealTypes}">
                                        <option value="${meal_type.id}">
                                            <c:out value="${meal_type.name}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <%------------  BUTTON: add to diary  ---------%>
                        <div class="text-center">
                            <button type="submit" class="btn btn-success" name="command" value="ADD_MEAL">Add to Food
                                Diary
                            </button>
                        </div>
                    </form>
                </div>
                <br>
                <%-------------  SHOW FOOD DIARY  ------------%>
                <%--meal--%>
                <c:if test="${not empty meals}">
                    <jsp:include page="includeFoodTab.jsp"/>
                </c:if>
            </div>

            <%------------ activity ---------------%>
            <div class="tab-pane active" id="activity" role="tabpanel">
                <jsp:include page="includeAddActivityBlock.jsp"/>
                <c:if test="${not empty activities}">
                    <jsp:include page="includeActivityTable.jsp"/>
                </c:if>
            </div>

            <%------------- WATER ------------%>
            <div class="tab-pane" id="water" role="tabpanel">Water</div>

            <%------------- Weight monitor ------------%>
            <div class="tab-pane" id="weight_monitor" role="tabpanel">Weight monitor</div>
        </div>

    </div>


</body>
</html>

