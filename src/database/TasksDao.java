package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Task;

/**
 * This class serves as the blueprint for the data acess object for the tasks
 * table in the database. The four basic operations: CREATE, READ, UPATE, and
 * DELETE are supported.
 * 
 * @author Paing Pyae Man
 */
public class TasksDao extends Database {

    /**
     * Get all the records in the tasks table.
     * 
     * @return a list of task object
     */
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

    /**
     * Add a new record to the tasks table.
     * 
     * @param task the task to be inserted to the database
     */
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

    /**
     * Update the existing task in the database. The newTask provided must have a
     * valid id, which associates with a row in the tasks table.
     * 
     * @param newTask the updated task with the desired changes
     */
    public void updateTask(Task newTask) {
        Connection connection = openConnection();

        try {
            PreparedStatement pstmt = connection
                    .prepareStatement("update tasks set name = ?, is_done = ? where id = ?");
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

    /**
     * Delete an existing task in the database by id. The id must be associated with
     * an existing task in the database.
     * 
     * @param taskId id of the task to be deleted
     */
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
