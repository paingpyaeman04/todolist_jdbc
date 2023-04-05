import java.util.List;

import database.TasksDao;
import models.Task;

public class App {
    public static void main(String[] args) throws Exception {
        TasksDao tasksDao = new TasksDao();

        // Adding a new task
        tasksDao.addNewTask(new Task("New task"));
        
        // Updating the existing task
        // Task updatedTask = new Task(3, "Updated task", true);
        // tasksDao.updateTask(updatedTask);

        // Deleting a task by id
        // tasksDao.deleteTaskById(3);

        // Retrieving all the tasks
        List<Task> allTasks = tasksDao.getAllTasks();
        allTasks.forEach(task -> {
            System.out.println(task.getId() + " : "  + task.getName() + " : " + task.isDone());
        });
    }
}
