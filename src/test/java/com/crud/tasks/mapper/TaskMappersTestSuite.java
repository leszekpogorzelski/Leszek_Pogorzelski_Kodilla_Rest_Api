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
        Assert.assertEquals(new Long(1) , new Long(task.getId()));
    }

    @Test
    public void testMapToEmptyTask() {
        //Given
        TaskDto taskDto = new TaskDto();
        //When
        Task task = taskMapper.mapToTask(taskDto);

        //Then
        Assert.assertNotNull(task);

    }

    @Test
    public void testMapToTaskDto() {
        //Given
        Task task = new Task (1L,"XYZ","XYZ");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        //Then
        Assert.assertEquals(new Long(1), new Long(taskDto.getId()));
    }

    @Test
    public void testMapToEmptyTaskDto() {
        //Given
        Task task = new Task();
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        //Then
        Assert.assertNotNull(taskDto);
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
