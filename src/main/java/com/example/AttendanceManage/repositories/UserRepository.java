package com.example.AttendanceManage.repositories;

import com.example.AttendanceManage.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> findAll()
    {
        // 一覧取得
        String sql = "SELECT * FROM users";
        List<User> list = new ArrayList<>();
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);

        for(int i = 0; i < result.size(); i++)
        {
            Map<String, Object> map = result.get(i);

            User user = new User();
            user.setId((int) map.get("id"));
            user.setUser_id((String) map.get("user_id"));
            user.setName((String) map.get("name"));
            user.setEmail((String) map.get("email"));
            user.setPassword((String) map.get("password"));
            user.setTel((String) map.get("tel"));
            user.setRemarks((String) map.get("remarks"));
            user.setRole((int) map.get("role"));

            list.add(user);
        }
        return list;
    }

    public void insert(User user)
    {
        String sql = "INSERT INTO users (user_id, name, email, password, tel, remarks, role)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?)";
        try
        {
            jdbcTemplate.update(sql, user.getUser_id(), user.getName(), null, user.getPassword(), null, null, user.getRole());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public User findById(int id)
    {
        String sql = "SELECT * FROM users WHERE id = ?";
        Map<String, Object> resultMap = jdbcTemplate.queryForMap(sql, id);

        User user = new User();
        user.setId((int) resultMap.get("id"));
        user.setUser_id((String) resultMap.get("user_id"));
        user.setName((String) resultMap.get("name"));
        user.setEmail((String) resultMap.get("email"));
        user.setPassword((String) resultMap.get("password"));
        user.setTel((String) resultMap.get("tel"));
        user.setRemarks((String) resultMap.get("remarks"));
        user.setRole((int) resultMap.get("role"));

        return user;
    }

    public void update(User user)
    {
        String sql = "UPDATE users SET name = ?, user_id = ?, password = ?, role = ? WHERE id = ?";
        try
        {
            jdbcTemplate.update(sql, user.getName(), user.getUser_id(), user.getPassword(), user.getRole(), user.getId());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void delete(User user)
    {
        String sql = "DELETE FROM users WHERE id = ?";
        try
        {
            jdbcTemplate.update(sql, user.getId());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
