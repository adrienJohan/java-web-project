<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>

<%-- Affichage du total des dépenses par catégorie et par mois --%>
<div>
    <h2>Total Expenses by Category</h2>
    <%
        Map<String, Double> totalExpensesByCategoryAndMonth = (Map<String, Double>)request.getAttribute("totalExpensesByCategoryAndMonth");
        if (totalExpensesByCategoryAndMonth != null && !totalExpensesByCategoryAndMonth.isEmpty()) {
            for (Map.Entry<String, Double> entry : totalExpensesByCategoryAndMonth.entrySet()) {
                String category = entry.getKey();
                Double totalExpense = entry.getValue();
                %>
                <p><strong><%= category %>:</strong> <%= totalExpense %> ariary</p>
                <%
            }
        } else {
            out.println("No expenses available.");
        }
    %>
</div>
