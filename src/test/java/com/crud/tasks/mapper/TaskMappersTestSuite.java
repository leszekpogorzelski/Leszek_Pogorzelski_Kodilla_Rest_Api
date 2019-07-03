package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMappersTestSuite {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L,"XYZ","XYZ");
        //When
        Task task = taskMapper.mapToTask(taskDto);

        //Then
        Assert.assertEquals(java.util.Optional.of(1L), java.util.Optional.of(task.getId()));
    }

    @Test
    public void testMapToTaskDto() {
        //Given
        Task task = new Task (1L,"XYZ","XYZ");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        //Then
        Assert.assertEquals(java.util.Optional.of(1L), java.util.Optional.of(taskDto.getId()));
    }

    @Test
    public void testMapToTaskDtoList() {
        //Given
        Task task = new Task (1L,"XYZ","XYZ");
        Task task1 = new Task (2L,"ZYX","ZYX");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);
        taskList.add(task1);
        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);

        //Then
        Assert.assertEquals(2, taskDtoList.size());
    }
}
