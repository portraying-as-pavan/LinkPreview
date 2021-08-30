<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!doctype html>
<html lang="en">
<head>

    <!-- Get Required BootStrap and Jquery CDN -->

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    <script src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>

    <title>Enter Url</title>

</head>


<body>

<div class="container-fluid mt-2  " >
    <div class="row">
        <div class="col-12 col-md-5">


            <div class="form-group">
                <label >Link </label>
                <input type="text" class="form-control" id="url" placeholder="Enter URL here" />
            </div>

            <div class="mb-2">
                <button class="btn btn-sm btn-dark" value="preview" onclick="preview()">Get Link Details</button>
            </div>

            <div class="form-group" id="link-preview"></div>
        </div>


        <div class="col-md-7 d-none d-md-block"></div>


    </div>
</div>

<script >
    function preview() {
        var url = $('#url').val();
        if (url) {
            $.get('/preview/view?url=' + url, function(data, status) {
                $('#link-preview').html(data);

                var myWindow = window.open('', 'preview', 'width=200,height=100');
                var doc = myWindow.document;
                doc.open();
                doc.write(data);
                doc.close();

            });
        }
    }


</script>

</body>

</html>