package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.Driver;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DatabaseUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/budget_tracker_db";
    private static final String USERNAME = "adrien";
    private static final String PASSWORD = "Motdep@sse123";

    private DatabaseUtil() {}

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Exception lors de la fermeture de la connexion");
                e.printStackTrace();
            }
        }
    }

    public static void addExpense(Expense expense) {
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur lors du chargement du pilote JDBC MySQL.");
            e.printStackTrace();
        }

        
        try {
        	
            connection = getConnection();
            
            // Préparer la requête SQL pour insérer une nouvelle dépense
            String sql = "INSERT INTO expenses (description, amount, category, date) VALUES (?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, expense.getDescription());
            statement.setDouble(2,  expense.getAmount());
            statement.setString(3, expense.getCategory());
            statement.setDate(4,  java.sql.Date.valueOf(expense.getDate()));
            
            // Exécuter la requête SQL
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Exception lors de l'obtention de la connexion");
            e.printStackTrace();
        } finally {
            // Fermer la connexion 
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.out.println("Exception lors de la fermeture de la déclaration");
                    e.printStackTrace();
                }   
            }
            closeConnection(connection);
        }
    }

    public static List<Expense> getAllExpenses(){
        List<Expense> expenses = new ArrayList<>();
        Connection connection = null; 
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur lors du chargement du pilote JDBC MySQL.");
            e.printStackTrace();
        }
        try {
            // Établir la connexion 
            connection = getConnection();
            
            // Préparer la requête SQL pour récupérer toutes les dépenses
            String sql = "SELECT * FROM expenses";
            statement = connection.prepareStatement(sql);
            
            // Exécuter la requête SQL
            resultSet = statement.executeQuery();
            
            // Parcourir les résultats et créer une dépense
            while (resultSet.next()) {
                Expense expense = new Expense();
                expense.setId(resultSet.getInt("id"));
                expense.setDescription(resultSet.getString("description"));
                expense.setAmount(resultSet.getDouble("amount"));
                expense.setCategory(resultSet.getString("category"));
                expense.setDate(resultSet.getDate("date").toLocalDate());
                expenses.add(expense);
            }
        
        } catch (SQLException e) {
            System.out.println("Exception lors de la récupération de toutes les dépenses");
            e.printStackTrace();
        } finally {
            closeConnection(connection);
            
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e){
                    System.out.println("Exception lors de la fermeture de la déclaration 2");
                    e.printStackTrace();
                }
            }
            
            if(resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    System.out.println("Exception lors de la fermeture du ResultSet");
                    e.printStackTrace();
                }
            }
        }
        return expenses;
    }
    
    public static void resetTables() {
        Connection connection = null;
        PreparedStatement preparedStatementExpenses = null;
        PreparedStatement preparedStatementInitialBudget = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur lors du chargement du pilote JDBC MySQL.");
            e.printStackTrace();
        }
        try {
            connection = getConnection();

            preparedStatementExpenses = connection.prepareStatement("TRUNCATE TABLE expenses");
            preparedStatementInitialBudget = connection.prepareStatement("TRUNCATE TABLE initial_budget");

     
            preparedStatementExpenses.executeUpdate();
            preparedStatementInitialBudget.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la réinitialisation des tables.");
            e.printStackTrace();
        } finally {
        
            closeConnection(connection);
            if (preparedStatementExpenses != null) {
                try {
                    preparedStatementExpenses.close();
                } catch (SQLException e) {
                    System.out.println("Erreur lors de la fermeture du PreparedStatement pour la table expenses.");
                    e.printStackTrace();
                }
            }
            if (preparedStatementInitialBudget != null) {
                try {
                    preparedStatementInitialBudget.close();
                } catch (SQLException e) {
                    System.out.println("Erreur lors de la fermeture du PreparedStatement pour la table initial_budget.");
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static void addInitialBudget(double initialBudget) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur lors du chargement du pilote JDBC MySQL.");
            e.printStackTrace();
        }

        try {
            connection = getConnection();

            String checkSql = "SELECT COUNT(*) FROM initial_budget";
            statement = connection.prepareStatement(checkSql);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int rowCount = resultSet.getInt(1);
            resultSet.close();
            
 
            if (rowCount == 0) {
                String insertSql = "INSERT INTO initial_budget (initial_budget) VALUES (?)";
                statement = connection.prepareStatement(insertSql);
                statement.setDouble(1, initialBudget);
                statement.executeUpdate();
            } else {
              
                String updateSql = "UPDATE initial_budget SET initial_budget = ?";
                statement = connection.prepareStatement(updateSql);
                statement.setDouble(1, initialBudget);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Exception lors de l'ajout ou de la mise à jour du budget initial");
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    
    public static double getInitialBudget() {
    	Connection connection = null; 
    	PreparedStatement statement = null; 
    	ResultSet resultSet = null;
    	double initialBudget = 0.0;
    	
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur lors du chargement du pilote JDBC MySQL.");
            e.printStackTrace();
        }
        
        try {
        	connection = getConnection(); 
        	String sql = "SELECT initial_budget FROM initial_budget";
        	statement = connection.prepareStatement(sql);
        	resultSet = statement.executeQuery();
        	if(resultSet.next()) {
        		initialBudget = resultSet.getDouble("initial_budget");
        	}
        }	catch (SQLException e) {
        	System.out.println("Exception lors de la récupération du budget initial");
        	e.printStackTrace();
        }	finally {
        	closeConnection(connection);
        }
        return initialBudget;
    }
    
    
    public static double getTotalExpenses() {
    	double totalExpenses = 0.0;
    	Connection connection = null;
    	PreparedStatement statement = null;
    	ResultSet resultSet = null;
    	
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur lors du chargement du pilote JDBC MySQL.");
            e.printStackTrace();
        }
    	
        try {
            connection = getConnection();
            String sql = "SELECT SUM(amount) AS total FROM expenses";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                totalExpenses = resultSet.getDouble("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }

        return totalExpenses;
    }
    	
    

    
    public static Map<String, Double> getTotalExpensesByCategoryAndMonth(int month, int year) {
        Map<String, Double> expensesByCategory = new HashMap<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur lors du chargement du pilote JDBC MySQL.");
            e.printStackTrace();
        }
        
        
        try {
            connection = getConnection();
            String sql = "SELECT category, SUM(amount) AS total_amount FROM expenses WHERE MONTH(date) = ? AND YEAR(date) = ? GROUP BY category";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, month);
            statement.setInt(2, year);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String category = resultSet.getString("category");
                double totalAmount = resultSet.getDouble("total_amount");
                expensesByCategory.put(category, totalAmount);
            }
        } catch (SQLException e) {
            System.out.println("Exception lors du calcul du montant total des dépenses par catégorie et par mois");
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return expensesByCategory;
    }
    
}
