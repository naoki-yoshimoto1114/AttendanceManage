package com.example.AttendanceManage.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * ログイン機能のテスト
 */
@SpringBootTest
@AutoConfigureMockMvc
class SecurityConfigTest
{
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void accessLoginPage() throws Exception
    {
        mockMvc.perform(get("/login"))
                .andExpect(status().is(200));
    }

    @Test
    public void loginWithValidUser() throws Exception {
        mockMvc.perform(post("/login")
                        .param("userId", "90001")
                        .param("password", "test123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/")); // ログイン成功後のリダイレクト先
    }

    @Test
    @WithMockUser(username = "90001", roles = "USER")
    public void accessViewWithUser() throws Exception
    {
        printUserInfo();
        mockMvc.perform(get("/view"))
                .andExpect(status().is(200));
    }

    @Test
    @WithMockUser(username = "90001", roles = "USER")
    public void accessAdminPageWithUser() throws Exception
    {
        printUserInfo();
        mockMvc.perform(get("/master/users"))
                .andExpect(status().is(403));
    }

    @Test
    @WithMockUser(username = "90002", roles = "ADMIN")
    public void accessAdminPageWithAdmin() throws Exception
    {
        printUserInfo();
        mockMvc.perform(get("/master/users"))
                .andExpect(status().is(200));
    }

    @Test
    public void invalidLogin() throws Exception
    {
        mockMvc.perform(post("/login")
                .param("userId", "99999")
                .param("password", "invalidPassword"))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("/login?error"));
    }

    private void printUserInfo()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName()); // ユーザー名を表示
        System.out.println(authentication.getAuthorities()); // ユーザーの権限を表示
    }

}