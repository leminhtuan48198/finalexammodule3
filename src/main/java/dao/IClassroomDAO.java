package dao;

import model.Classroom;

import java.sql.SQLException;
import java.util.List;

public interface IClassroomDAO {
    public List<Classroom> selectAllClassrooms();
}