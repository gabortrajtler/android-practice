package com.codingwithmitch.todolist.util;

import com.codingwithmitch.todolist.models.Task;

import java.util.ArrayList;
import java.util.List;

public class DataSource {

    public static List<Task> createTaskList() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Vidd le a szemetet", true, 3));
        tasks.add(new Task("Walk the dog", false, 2));
        tasks.add(new Task("Cover the shelf of the projector with a soft thing", false, 1));
        tasks.add(new Task("Load the dishwasher", true, 0));
        tasks.add(new Task("Készíts vacsorát", false, 5));
        return tasks;
    }
}
