package com.example.AttendanceManage.repositories;

import com.example.AttendanceManage.Entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void findAll() {
        List<User> actual = userRepository.findAll();
        User user1 = new User(1, "00001",  "John Doe", "johndoe@example.com", "password1", "123-456-7890", "Test user 1", "ROLE_USER");
        User user2 = new User(2, "00002",  "Jane Smith", "janesmith@example.com", "password2", "987-654-3210", "Test user 2", "ROLE_ADMIN");
        User user3 = new User(3, "00003",  "Alice Johnson", "alicejohnson@example.com", "password3", "555-555-5555", "Test user 3", "ROLE_USER");
        User user4 = new User(4, "00004",  "Bob Brown", "bobbrown@example.com", "password4", "777-888-9999", "Test user 4", "ROLE_ADMIN");
        assertTrue(actual.contains(user1));
        assertTrue(actual.contains(user2));
        assertTrue(actual.contains(user3));
        assertTrue(actual.contains(user4));
    }

    @Test
    void insert() {
        User newUser = new User();
        newUser.setUser_id("00005");
        newUser.setName("new User");
        newUser.setPassword("testpassword");
        newUser.setRole("ROLE_USER");
        userRepository.insert(newUser);

        User findUser = userRepository.findById(findIdForUserId(newUser.getUser_id()));

        assertEquals(newUser.getUser_id(), findUser.getUser_id());
        assertEquals(newUser.getName(), findUser.getName());
        assertNull(findUser.getEmail());
        assertEquals(newUser.getPassword(), findUser.getPassword());
        assertNull(findUser.getTel());
        assertNull(findUser.getRemarks());
        assertEquals(newUser.getRole(), findUser.getRole());

        String sql = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(sql, findUser.getId());
    }

    @Test
    void findById() {
        User actual1 = userRepository.findById(1);
        User actual2 = userRepository.findById(2);
        User actual3 = userRepository.findById(3);
        User actual4 = userRepository.findById(4);
        User user1 = new User(1, "00001",  "John Doe", "johndoe@example.com", "password1", "123-456-7890", "Test user 1", "ROLE_USER");
        User user2 = new User(2, "00002",  "Jane Smith", "janesmith@example.com", "password2", "987-654-3210", "Test user 2", "ROLE_ADMIN");
        User user3 = new User(3, "00003",  "Alice Johnson", "alicejohnson@example.com", "password3", "555-555-5555", "Test user 3", "ROLE_USER");
        User user4 = new User(4, "00004",  "Bob Brown", "bobbrown@example.com", "password4", "777-888-9999", "Test user 4", "ROLE_ADMIN");
        assertEquals(actual1, user1);
        assertEquals(actual2, user2);
        assertEquals(actual3, user3);
        assertEquals(actual4, user4);
        assertNotEquals(actual1, user2);
    }

    @Test
    void update() {
        User user = new User();
        user.setUser_id("00006");
        user.setName("new User");
        user.setPassword("testpassword");
        user.setRole("ROLE_USER");
        userRepository.insert(user);

        User updateUser = new User();
        updateUser.setId(findIdForUserId(user.getUser_id()));
        updateUser.setUser_id("00007");
        updateUser.setName("update user");
        updateUser.setPassword("updatePassword");
        user.setRole("ROLE_USER");
        userRepository.update(updateUser);

        User actual = userRepository.findById(updateUser.getId());

        assertEquals(actual, updateUser);
        assertNotEquals(actual, user);

        String sql = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(sql, updateUser.getId());
    }

    @Test
    void delete() {
        User user = new User();
        user.setUser_id("00008");
        user.setName("new delete user");
        user.setPassword("testpassword");
        user.setRole("ROLE_USER");
        userRepository.insert(user);
        int id = findIdForUserId(user.getUser_id());
        List<User> beforeList = userRepository.findAll();
        System.out.println(beforeList);

        User user1 = new User(1, "00001",  "John Doe", "johndoe@example.com", "password1", "123-456-7890", "Test user 1", "ROLE_USER");
        User user2 = new User(2, "00002",  "Jane Smith", "janesmith@example.com", "password2", "987-654-3210", "Test user 2", "ROLE_ADMIN");
        User user3 = new User(3, "00003",  "Alice Johnson", "alicejohnson@example.com", "password3", "555-555-5555", "Test user 3", "ROLE_USER");
        User user4 = new User(4, "00004",  "Bob Brown", "bobbrown@example.com", "password4", "777-888-9999", "Test user 4", "ROLE_ADMIN");
        User deleteUser = new User(id, "00008",  "new delete user", null, "testpassword", null, null, "ROLE_USER");
        assertTrue(beforeList.contains(user1));
        assertTrue(beforeList.contains(user2));
        assertTrue(beforeList.contains(user3));
        assertTrue(beforeList.contains(user4));
        assertTrue(beforeList.contains(deleteUser));

        userRepository.delete(deleteUser);
        List<User> actualList = userRepository.findAll();
        System.out.println(actualList);

        assertTrue(actualList.contains(user1));
        assertTrue(actualList.contains(user2));
        assertTrue(actualList.contains(user3));
        assertTrue(actualList.contains(user4));
        assertFalse(actualList.contains(deleteUser));

        assertNotEquals(beforeList, actualList);
    }

    public int findIdForUserId(String user_id) {
        String sql = "SELECT id FROM users WHERE user_id = ?";
        Integer id = 0;
        try {
            id = jdbcTemplate.queryForObject(sql, Integer.class, user_id);
            if (id != null) {
                return id;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return 0;
    }
}