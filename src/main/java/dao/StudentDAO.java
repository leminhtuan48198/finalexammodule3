package dao;

import model.Student;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class StudentDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/quanlihocvien1";
    private String jdbcStudentname = "root";
    private String jdbcPassword = "123456";

    private static final String INSERT_StudentS_SQL = "INSERT INTO Students (name, email, country) VALUES (?, ?, ?);";
    private static final String SELECT_Student_BY_ID = "select * from view_student_classroom where id =?";
    private static final String SELECT_ALL_StudentS = "select * from view_student_classroom";
    private static final String DELETE_StudentS_SQL = "delete from student where id = ?;";
    private static final String UPDATE_StudentS_SQL = "update student set name = ?, dateOfBirth=?, address=?, phoneNumber=?,email= ?, idclass=? where id = ?;";

    public StudentDAO() {
    }

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

    public void insertStudent(Student student) throws SQLException {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into student (name, dateOfBirth,address,phoneNumber,email,idClass) values (?,?,?,?,?,?)")) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setDate(2, Date.valueOf(student.getDateOfBirth()));
            preparedStatement.setString(3, student.getAddress());
            preparedStatement.setString(4, student.getPhoneNumber());
            preparedStatement.setString(5, student.getEmail());
            preparedStatement.setInt(6, student.getIdClass());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Student selectStudent(int id) {
        Student student = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from view_student_classroom where id =?");) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                LocalDate dateOfBirth = rs.getDate("dateOfBirth").toLocalDate();
                String address = rs.getString("address");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");
                int idClass = rs.getInt("idclass");
                String nameClass = rs.getString("nameclass");
                student = new Student( name,dateOfBirth,address,phoneNumber, email,idClass,nameClass);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return student;
    }

    public List<Student> selectAllStudents() {
        List<Student> students = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from view_student_classroom");) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                LocalDate dateOfBirth = rs.getDate("dateOfBirth").toLocalDate();
                String address = rs.getString("address");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");
                int idClass = rs.getInt("idclass");
                String nameClass = rs.getString("nameclass");
                students.add(new Student(id, name,dateOfBirth,address,phoneNumber, email,idClass,nameClass));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return students;
    }

    public boolean deleteStudent(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("delete from student where id = ?;");) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateStudent(Student student) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("update student set name = ?, dateOfBirth=?, address=?, phoneNumber=?,email= ?, idclass=? where id = ?;");) {
            statement.setString(1, student.getName());
            statement.setDate(2, Date.valueOf(student.getDateOfBirth()));
            statement.setString(3, student.getAddress());
            statement.setString(4, student.getPhoneNumber());
            statement.setString(5, student.getEmail());
            statement.setInt(6, student.getIdClass());
            statement.setInt(7, student.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
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
    public List<Student> selectStudentByName(String nameStudent) {
        List<Student> students = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from view_student_classroom where name like ?");) {
            preparedStatement.setString(1,"%"+nameStudent +"%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                LocalDate dateOfBirth = rs.getDate("dateOfBirth").toLocalDate();
                String address = rs.getString("address");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");
                int idClass = rs.getInt("idclass");
                String nameClass = rs.getString("nameclass");
                students.add(new Student(id, name,dateOfBirth,address,phoneNumber, email,idClass,nameClass));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return students;
    }
}

