package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Task;

public class TasksDao extends Database {

    public List<Task> getAllTasks() {
        Connection connection = openConnection();
        List<Task> taskList = new ArrayList<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM tasks");

            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                boolean isDone = rs.getBoolean(3);
                taskList.add(new Task(id, name, isDone));
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        closeConnection(connection);
        return taskList;
    }

    public void addNewTask(Task task) {
        Connection connection = openConnection();

        try {
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO tasks(name, is_done) VALUES (?, ?)");
            pstmt.setString(1, task.getName());
            pstmt.setBoolean(2, task.isDone());
            pstmt.execute();
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        closeConnection(connection);
    }

    public void updateTask(Task newTask) {
        Connection connection = openConnection();

        try {
            PreparedStatement pstmt = connection.prepareStatement("update tasks set name = ?, is_done = ? where id = ?");
            pstmt.setString(1, newTask.getName());
            pstmt.setBoolean(2, newTask.isDone());
            pstmt.setInt(3, newTask.getId());
            pstmt.execute();
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        closeConnection(connection);
    }

    public void deleteTaskById(int taskId) {
        Connection connection = openConnection();

        try {
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM tasks WHERE id = ?");
            pstmt.setInt(1, taskId);
            pstmt.execute();
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        closeConnection(connection);
    }

}
