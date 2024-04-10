<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add</title>
<style>
body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
             background-image: url('26381843066962.jpg');
   			 background-size: cover;
        }

        .container {
            max-width: 500px;
            margin: 50px auto;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 30px;
        }

        h1 {
            color: #8CCD94;
            text-align: center;
        }

        label {
            display: block;
            margin-bottom: 10px;
        }

        input[type="text"], input[type="number"], select {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .btn {
            background-color: #8CCD94;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            display: block;
            width: 20%;
            margin: auto;
            text-align: center;
        }

        .btn:hover {
            background-color: #006400;
        }
    </style>
</head>
<body>
	<div class="container">
	<h1>Add an expense</h1>
	<form action="AddExpenseServlet"  method="post">
        <label for="description">Description :</label>
        <input type="text" id="description" name="description"><br>
        <label for="amount">Amount(Ariary) :</label>
        <input type="number" id="amount" name="amount" step="0.01"><br>
        <label for="category">Category :</label>
        <input type="text" id="category" name="category"><br>
        <button type="submit" class="btn">Add</button>
	</form>
	<a href="index.jsp">return  to home</a>
	</div>
</body>
</html>