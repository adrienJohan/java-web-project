<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
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
            max-width: 600px;
            margin: 100px auto;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 30px;
            text-align: center;
        }

        h1 {
            color: #8CCD94;
        }

        p {
            margin-bottom: 20px;
        }

        .btn {
            background-color: #8CCD94;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            display: inline-block;
            transition: background-color 0.3s;
            margin-bottom:10px;
        }

        .btn:hover {
            background-color: #006400;
        }
    </style>
</head>
<body>
<div class="container">
	<h1>Budget Tracker</h1>
	<p> <a href="setInitialBudget.jsp" class="btn"> Set Initial Budget</a></p>
	<p> <a href = "addExpense.jsp" class="btn"> Add an expense</a></p>
	<p><a href = "ViewExpensesServlet" class="btn">View expenses</a></p>
	<p><a href="ResetServlet"  class="btn" onclick="return confirmReset();"> Reset</a> </p>
</div>

    <script>
        function confirmReset() {
            return confirm("Are you sure you want to reset all data? This action cannot be undone.");
        }
    </script>
</body>
</html>