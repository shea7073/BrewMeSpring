package com.CS622.BrewMe.controller;

import com.CS622.BrewMe.repository.AleRepository;
import com.CS622.BrewMe.security.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.DataSource;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@Import(SecurityConfig.class)
@WebMvcTest(FileIOController.class)
class FileIOControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private DataSource dataSource;

    @MockBean
    private AleRepository aleRepository;

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnUploadTemplate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/uploadData"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("upload"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldUploadAndRedirect() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", MediaType.TEXT_PLAIN_VALUE, "test".getBytes());
        this.mockMvc.perform(MockMvcRequestBuilders.multipart("/uploadData")
                .file(file)
                .param("file", String.valueOf(file))
                .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnExportMenu() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/exportMenu"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("exportMenu"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnExportedData() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/exportData").contentType(MediaType.APPLICATION_OCTET_STREAM))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


}

