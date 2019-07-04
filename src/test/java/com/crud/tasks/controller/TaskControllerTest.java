package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService service;

    @MockBean
    private TaskMapper taskMapper;



    @Test
    public void testGetTasks() throws Exception {
       //Given
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L, "Test Task", "Test content"));
        tasks.add(new Task(2L, "Test Task2", "Test content2"));
       when(service.getAllTasks()).thenReturn(tasks);
       when(taskMapper.mapToTaskDtoList(anyList())).thenCallRealMethod();
       //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(new Integer(1))))
                .andExpect(jsonPath("$[0].title", is("Test Task")))
                .andExpect(jsonPath("$[0].content", is("Test content")))
                .andExpect(jsonPath("$[1].id", is(new Integer(2))))
                .andExpect(jsonPath("$[1].title", is("Test Task2")))
                .andExpect(jsonPath("$[1].content", is("Test content2")));
    }

    @Test
    public void testGetTask() throws Exception {
    //Given
        Task task = new Task(1L, "Test Task", "Test content");
        when(service.getTask(anyLong())).thenReturn(Optional.of(task));
        when(taskMapper.mapToTaskDto(ArgumentMatchers.any(Task.class))).thenCallRealMethod();
    //When & Then
        mockMvc.perform(get("/v1/task/getTask?taskId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(new Integer(1))))
                .andExpect(jsonPath("title", is("Test Task")))
                .andExpect(jsonPath("content", is("Test content")));
    }

  @Test
    public void testCreateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Test task", "Test content");
        Task task = new Task(1L, "Test task", "Test content");
        Gson gson = new Gson();
        String jsonString = gson.toJson(taskDto);

        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(service.saveTask(ArgumentMatchers.any(Task.class))).thenReturn(task);
        when(taskMapper.mapToTaskDto(ArgumentMatchers.any(Task.class))).thenReturn(taskDto);
        //When & Then
        mockMvc.perform(post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(new Integer(1))))
                .andExpect(jsonPath("title", is("Test Task")))
                .andExpect(jsonPath("content", is("Test content")));

      //{taskMapper.mapToTaskDto(service.saveTask(taskMapper.mapToTask(taskDto)));
  }



     /* @Test
    public void testUpdateTask() {
    }

    @Test
    public void testDeleteTask() {
    }*/
}