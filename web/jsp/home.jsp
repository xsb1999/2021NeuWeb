<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>home page</title>
    <link href="../css/home.css" rel="stylesheet"></link>
    <script src="../js/jquery.js"></script>
    <script src="../js/ajax.js"></script>
</head>
<body>

<%@include file="common/navigation.jsp"%>

<div>
    <div class="bear">
        <img src="../resource/pics/bear.jpg" alt="This is my favourite animal!">
    </div>
    <div>
        <h1 style="text-align: center;">Andy Xu</h1>
    </div>
</div>
<br>
<h2 style="text-align: center;color: rgb(253, 11, 11); font-family: Arial, Helvetica, sans-serif;">Basic Introduction</h2>
<div style="text-align: center;">
    <b>My name is Andy Xu, I come from Beijing, China. I am now studying in NEU.</b>
</div>
<br>
<h3 style="text-align: center;color: rgb(253, 11, 11); font-family: Arial, Helvetica, sans-serif;"><i>My interests</i></h3>
<div style="text-align: center;">
    Music, basketball, football and delicious food.
</div>
<h3 style="text-align: center;color: rgb(253, 11, 11); font-family: Arial, Helvetica, sans-serif;"><i>My experiences</i></h3>
<div style="text-align: center;">
    I improved PSO algorithm in a machine learning project last year.<br>
    I realized and compared three different types of RRT algorithm as the homework of a class called <i>Intelligent Driving Technologies.</i><br>
    I also made a small online hospital system called HIS, it's a project we had to finish.
</div>
<br>
<div style="text-align: center;">
    <a href="./project.jsp">Click here to see the detail of my RRT algorithm.</a>
</div>
<br>

<h3 style="text-align: center;color: rgb(253, 11, 11); font-family: Arial, Helvetica, sans-serif;"><i>My skills</i></h3>
<div style="text-align: center;">
    <ul>
        <li style="text-align: center;">
            Basic programming language
            <ul class="change">
                <li>Java</li>
                <li>Python</li>
                <li>C</li>
            </ul>
        </li>
    </ul>
    <ul>
        <li style="text-align: center;">
            Computer Science Knowledge
            <ul class="change">
                <li>Data Stracture</li>
                <li>Operating System</li>
                <li>Computer network</li>
                <li>Database</li>
            </ul>
        </li>
    </ul>
    <ul>
        <li style="text-align: center;">
            Other Related Knowledge
            <ul class="change">
                <li>Machine Learning</li>
                <li>Deep Learning</li>
            </ul>
        </li>
    </ul>
</div>

<h3 style="text-align: center;color: rgb(253, 11, 11); font-family: Arial, Helvetica, sans-serif;"><i>My career goals</i></h3>
<div style="text-align: center;">
    I want to be a great programmer working in a big IT firm in the future.
</div>

<br><br>


<!-- My projects -->
<h2 style="text-align: center;color: rgb(64, 5, 158); font-family: Arial, Helvetica, sans-serif;"><i>My projects</i></h2>

<div style="text-align: center;">
    <input type="button" id="btn" value="Click for details" onclick="f1()">
</div>
<br>
<table class="projects_table" width="80%" align="center">
    <tr>
        <td><b>Title</b></td>
        <td><b>Summary</b></td>
        <td><b>Description</b></td>
        <td><b>Type</b></td>
        <td><b>Language</b></td>
    </tr>
    <tbody id="content">
    </tbody>
</table>

<br><br><br><br>

<%@include file="common/footer.jsp"%>

</body>
</html>
