
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.DatabaseUtil;

public class SetInitialBudgetServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SetInitialBudgetServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Rediriger vers la page pour définir le budget initial
        response.sendRedirect("setInitialBudget.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les paramètres du formulaire

        double initialBudget = Double.parseDouble(request.getParameter("initialBudget"));

        // Ajouter le budget initial à la base de données
        DatabaseUtil.addInitialBudget(initialBudget);

        // Rediriger vers la page principale
        response.sendRedirect("index.jsp");
    }
}
