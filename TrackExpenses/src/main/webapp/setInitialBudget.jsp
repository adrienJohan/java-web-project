<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Set Initial Budget</title>
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

input[type="number"], button {
    width: 100%;
    padding: 10px;
    margin-bottom: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
}

button {
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

button:hover {
    background-color: #0056b3;
}
    
    </style>
</head>
<body>
<div class="container">
    <h1>Set Initial Budget</h1>
    <form action="SetInitialBudgetServlet" method="post">
        <label for="initialBudget">Initial Budget(Ariary):</label>
        <input type="number" id="initialBudget" name="initialBudget" step="0.01" required><br>
        <button type="submit">Set Budget</button>
    </form>
    <a href="index.jsp">return  to home</a>
    </div>
  ®
</body>
</html>
