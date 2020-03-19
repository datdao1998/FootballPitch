<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 12/3/2019
  Time: 9:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>About Us</title>
    <link rel="stylesheet" type="text/css" href="css/about-us.css">
</head>
<body style="margin: 0px">
<%@include file="resource/layout/header.jsp" %>
<div style="background-image: url('imgs/about-us.jpg'); width: 100vw; height: 75vh; background-size: 99.4vw 75vh; margin-top: 7vh">
    <div style="width: 100vw; height: 44vh">
        <img id="about-us" src="imgs/about-us-team.jpg">
        <div id="about-us-about-txt"> ABOUT</div>
        <div id="about-us-us-txt"> US </div>
        <button id="about-us-previous" onclick="changePrevious()"> << Previous</button>
        <button id="about-us-next" onclick="changeNext()"> Next >></button>
        <div style="width: 35vw;height: 40vh; margin-right: 3vw; margin-top: 3vh; float: right">
            <img id="about-us-pitch" src="imgs/about-us-pitch.jpg">
        </div>

    </div>


    <div style="width: 50vw; height: 31vh " id="about-us-area">
              &nbsp; <br><br>&nbsp; &nbsp; Established since 2009, LaTanDat Football Pitch had 10 years of <br> experience in football pitch management.
           We always want to bring <br>satisfaction to customers, by building a lot of utilities for customer,<br>like booking field,
           using accompanied service, promotion,... Specially,<br> in 2019, we was voted as the one of the three best football field
           management <br>systems in Viet Nam.
    </div>

    <div id = "about-us-description" style="width: 40vw; height: 24vh ; margin-left: 57vw ; margin-top: 2vh"> &nbsp;       LaTanDat_Football Pitch is a combination of football pitches, which includes <br>7-a-side pitches and 11-a-side pitches.
        Specifically, we have total 15 football <br>pitches, with eight 7-a-side pitches and seven 11-a-side pitches. We always <br> want  to bring the most reasonable price to make you
        satisfied. </div>

</div>

<script>
    function changePrevious() {
        var image = document.getElementById("about-us-pitch");
        image.src = "imgs/about-us-pitch.jpg";
        var description = document.getElementById("about-us-description");
        description.innerText = "   LaTanDat_FootballPitch is a combination of football pitches, which includes \n7-a-side pitches and 11-a-side pitches." +
            "        Specifically, we have total 15 football \n pitches, with eight 7-a-side pitches and seven 11-a-side pitches. We always \n want  to bring the most reasonable price to make you" +
            "        satisfied."
    }

    function changeNext() {
       var image = document.getElementById("about-us-pitch");
       image.src = "imgs/about-us-service.jpg";
       var description = document.getElementById("about-us-description");
       description.innerText = "    LaTanDat_FootballPitch also have lots of services for customer, from \n renting football shirts or ball to serving energy drink if you want. \n Servants are very friendly and" +
           "conscientious, and they are always \n ready to please customers. Everything is comfortable for customers \n to have the best experience in playing football. "
    }
</script>


<%@include file="resource/layout/footer.jsp" %>


</body>
</html>


