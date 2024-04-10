<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Expenses list</title>
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
            max-width: 800px;
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

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #8CCD94;
            color: #fff;
        }

        tr:hover {
            background-color: #f2f2f2;
        }
    </style>

</head>
<body>
<div class="container">
	<h1>Data list</h1>
	<table border = "1">
		<tr>
			<th>Description</th>
            <th>Amount</th>
            <th>Category</th>
            <th>Date</th>
		</tr>
		
		<%@ page import="java.util.List" %>
		<%@ page import="com.Expense" %>
		<%@ page import="com.DatabaseUtil" %>
		
		<% List<Expense> expenses = (List<Expense>) request.getAttribute("expenses"); %>
		<% if (expenses != null) { %>
			<%
				for (Expense expense : expenses) {
					%>
					<tr>
							<td><%= expense.getDescription() %> </td>
							<td><%= expense.getAmount() %> </td>
							<td><%= expense.getCategory() %> </td>
							<td><%= expense.getDate() %> </td>
					</tr>	
					<%
				}
				%>
		<% } %> 
			
			
	</table>
	<%@ include file="totalExpensesByCategoryAndMonth.jsp" %>
	
	<% double initialBudget = DatabaseUtil.getInitialBudget();
       double totalExpenses = DatabaseUtil.getTotalExpenses();
       double remainingBudget = initialBudget - totalExpenses;
       
       String formattedRemainingBudget = String.format("%.2f", remainingBudget);
    %>
    <div>
        <h2>Remaining Budget</h2>
        <% if (remainingBudget >= 0) { %>
            <p>you have  <%= formattedRemainingBudget %>  Ariary remaining</p>
        <% } else { %>
            <p>You have exceeded your initial budget by  <%= Math.abs(remainingBudget) %> Ariary.</p>
        <% } %>
    </div>
	<a href="index.jsp"> return  to home</a>
	
®</div>
	
	
	
</body>
</html>