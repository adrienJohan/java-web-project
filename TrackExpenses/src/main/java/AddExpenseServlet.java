

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import com.Expense;
import com.DatabaseUtil;



public class AddExpenseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public AddExpenseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String description  = request.getParameter("description");
		double amount = Double.parseDouble(request.getParameter("amount"));
		String category = request.getParameter("category");
		LocalDate date = LocalDate.now();
		
		Expense expense = new Expense();
		
		expense.setDescription(description);
		expense.setAmount(amount);
		expense.setCategory(category);
		expense.setDate(date);
		
		DatabaseUtil.addExpense(expense);
		
		response.sendRedirect("index.jsp");
	}

}


