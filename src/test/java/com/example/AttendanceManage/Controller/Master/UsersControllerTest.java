package com.example.AttendanceManage.Controller.Master;

import com.example.AttendanceManage.Entity.User;
import com.example.AttendanceManage.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UsersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    void index() throws Exception
    {
        mockMvc.perform(get("/master/users"))
                .andExpect(status().is(200))
                .andExpect(content().string(containsString("ユーザ一覧")))
                .andExpect(content().string(containsString("John Doe")))
                .andExpect(content().string(containsString("00004")))
                .andExpect(content().string(not(containsString("テストユーザ3"))));
    }

    @Test
    void add() throws Exception
    {
        mockMvc.perform(get("/master/user/add"))
                .andExpect(status().is(200))
                .andExpect(content().string(containsString("ユーザ新規登録")));
    }

    @Test
    void create() throws Exception
    {
        mockMvc.perform(post("/master/user/create"))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("/master/users"));
    }

    @Test
    void edit() throws Exception
    {
        int id = 1;
        mockMvc.perform(get("/master/user/edit/" + id))
                .andExpect(status().is(200))
                .andExpect(content().string(containsString("ユーザ編集")))
                .andExpect(content().string(containsString("John Doe")))
                .andExpect(view().name("master/user_edit"));
    }

    @Test
    void update() throws Exception
    {
        int id = 1;
        mockMvc.perform(post("/master/user/update/" + id))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("/master/users"));
    }

    @Test
    void delete() throws Exception
    {
        mockMvc.perform(post("/master/user/delete"))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("/master/users"));
    }

    public User createUser(User user)
    {
        user.setUser_id("00010");
        user.setName("test user");
        user.setPassword("testPassword");
        user.setRole(0);
        return user;
    }

    public void deleteUser(User user)
    {
        userRepository.delete(user);
    }
}