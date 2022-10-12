package dao;

import model.Classroom;
import model.Classroom;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClassroomDAO implements IClassroomDAO{
    private String jdbcURL = "jdbc:mysql://localhost:3306/quanlihocvien1";
    private String jdbcStudentname = "root";
    private String jdbcPassword = "123456";
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcStudentname, jdbcPassword);
        } catch (SQLException e) {

            e.printStackTrace();
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        }
        return connection;
    }
    @Override
    public List<Classroom> selectAllClassrooms() {
        List<Classroom> classrooms = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from classroom");) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idClass = rs.getInt("idclass");
                String nameClass = rs.getString("nameclass");

                classrooms.add(new Classroom(idClass,nameClass));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return classrooms;
        }
    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
