


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.Expense;
import com.DatabaseUtil;


public class ViewExpensesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ViewExpensesServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		List<Expense> expenses = DatabaseUtil.getAllExpenses();
		request.setAttribute("expenses", expenses);
		
		
		
		//calcul total depenses par catégorie/mois
		LocalDate currentDate = LocalDate.now();
		int currentMonth = currentDate.getMonthValue();
		int currentYear = currentDate.getYear();
		Map<String, Double> totalExpensesByCategoryAndMonth = DatabaseUtil.getTotalExpensesByCategoryAndMonth(currentMonth, currentYear);
		request.setAttribute("totalExpensesByCategoryAndMonth", totalExpensesByCategoryAndMonth);
		
		
		request.getRequestDispatcher("viewExpenses.jsp").forward(request,response);
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}

