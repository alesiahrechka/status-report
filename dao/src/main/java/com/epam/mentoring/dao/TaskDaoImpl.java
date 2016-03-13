package com.epam.mentoring.dao;

import com.epam.mentoring.domain.Task;
import com.epam.mentoring.domain.TaskStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * Created by alesya on 27.02.16.
 */

@Component
public class TaskDaoImpl implements TaskDao {

    private static final Logger LOGGER = LogManager.getLogger();

    public static final String TASK_KEY = "task_key";
    public static final String DESCRIPTION = "description";
    public static final String TASK_STATUS = "status";
    public static final String DURATION = "duration";

    public String getAllTasksSql = "select * from task";
    public String getTasksByStatusSql = "select * from task where status in ( ? ) ";
    public String getTasksByKeySql = "select * from task where task = ? ";
    public String addTaskSql = "INSERT INTO TASK (task_key, description, duration, status) VALUES (:task_key, :description, :duration, :status)";
    public String updateTaskSql = "UPDATE TASK SET description = :description, duration = :duration, status =:status WHERE task_key = :task_key";


    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    @PostConstruct
    public void init() {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Task> getAllTasks() {
        return jdbcTemplate.query(getAllTasksSql, new TaskMapper());
    }

    @Override
    public List<Task> getTasksByStatus(Set<TaskStatus> statuses) {
        Assert.notNull(statuses, "status is null");
        String statusesStr = getStringStatuses(statuses);

        //TODO: prepared statement doesn't work correctly
//        return jdbcTemplate.query(getTasksByStatusSql, new TaskMapper(), statusesStr);

        String getTasksByStatusQuery = String
                .format("select * from task where status in ( %s )", statusesStr);
        return jdbcTemplate.query(getTasksByStatusQuery, new TaskMapper());
    }

    @Override
    public Task getTask(String key) {
        String getTasksSqlQuery = String
                .format("select * from task where task_key = '%s'", key);
        return jdbcTemplate.queryForObject(getTasksSqlQuery, new TaskMapper());
    }

    @Override
    public void removeTask(String key) {
        String removeTasksSqlQuery = String
                .format("delete from task where task_key = '%s' ", key);
        jdbcTemplate.update(removeTasksSqlQuery);

    }

    @Override
    public void addTask(Task task) {
        Assert.notNull(task);
        Assert.notNull(task.getKey());
        Assert.notNull(task.getDescription());
        Assert.notNull(task.getDuration());
        Assert.notNull(task.getTaskStatus());


        //TODO: prepared statement doesn't work correctly
//        Map<String, Object> parameters = new HashMap(4);
//        parameters.put(TASK_KEY, task.getKey() );
//        parameters.put(DESCRIPTION, task.getDescription());
//        parameters.put(TASK_STATUS, task.getTaskStatus());
//        parameters.put(DURATION, task.getDuration() );
//        namedJdbcTemplate.update(addTaskSql, new MapSqlParameterSource(parameters));

        String addTasksSqlQuery = String
                .format("INSERT INTO TASK (task_key, description, duration, status) VALUES ('%s', '%s', %s, '%s')",
                        task.getKey(), task.getDescription(), task.getDuration(), task.getTaskStatus().name());
        jdbcTemplate.execute(addTasksSqlQuery);


    }

    @Override
    public void updateTask(Task task) {

        Assert.notNull(task);
        Assert.notNull(task.getKey());
        Assert.notNull(task.getDescription());
        Assert.notNull(task.getDuration());
        Assert.notNull(task.getTaskStatus());

        //TODO: prepared statement doesn't work correctly
        /*
        Map<String, Object> parameters = new HashMap(4);
        parameters.put(TASK_KEY, task.getKey() );
        parameters.put(DESCRIPTION, task.getDescription());
        parameters.put(TASK_STATUS, task.getTaskStatus());
        parameters.put(DURATION, task.getDuration() );
        namedJdbcTemplate.update(updateTaskSql, new MapSqlParameterSource(parameters));
        */

        String updateTasksSqlQuery = String
                .format("UPDATE TASK SET description = '%s', duration = %s, status ='%s' WHERE task_key = '%s'",
                        task.getDescription(), task.getDuration(), task.getTaskStatus().name(), task.getKey());
        jdbcTemplate.execute(updateTasksSqlQuery);

    }

    public class TaskMapper implements RowMapper<Task> {
        public Task mapRow(ResultSet rs, int i) throws SQLException {

            String key = rs.getString(TASK_KEY);
            String description = rs.getString(DESCRIPTION);
            Double duration = rs.getDouble(DURATION);
            TaskStatus taskStatus = TaskStatus.valueOf(rs.getString(TASK_STATUS));
            Task task = new Task(key, description, duration, taskStatus);
            return task;
        }
    }

    public String getStringStatuses(Set<TaskStatus> statuses) {
        if (statuses.size() == 0) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        for (TaskStatus taskStatus : statuses) {
            result.append("'");
            result.append(taskStatus.name());
            result.append("'");
            result.append(",");
        }
        result.deleteCharAt(result.lastIndexOf(","));
        return result.toString();
    }
}