package ua.edu.sumdu.j2se.semenyako.tasks;

import java.util.Arrays;

public class ArrayTaskList {
    public Task[] taskList;
    public int arraySize = 10;
    public int countElements = 0;


    public ArrayTaskList() {
        taskList = new Task[arraySize];
    }

    public void add(Task task) {
        if (countElements == 0) {
            taskList[0] = task;
        }
        else {
            if (countElements == arraySize) {
                arraySize = arraySize*2;
                resize();
            }
            taskList[countElements] = task;
        }
        countElements++;
    }

    private void resize() {
        Task[] temp = new Task[arraySize];
        for (int i = 0; i < countElements; i++) {
            temp[i] = taskList[i];
        }
        taskList = temp;
    }

    public boolean remove(Task task) {
        boolean matchCheck = false;
        for (int i = 0; i < countElements; i++) {
            if(task.equals(taskList[i])) {
                matchCheck = true;
            }
            if (matchCheck && i < countElements-1) {
                taskList[i] = taskList[i+1];
            }
        }
        if(matchCheck) {
            countElements--;
            resize();
            return true;
        }
        else
            return false;
    }

    public int size() {
        return countElements;
    }

    public Task getTask(int index) {
        return taskList[index];
    }

    @Override
    public String toString() {
        return "ArrayTaskList{" +
                "tasklist=" + Arrays.toString(taskList) +
                ", listsize=" + arraySize +
                '}';
    }

    public ArrayTaskList incoming(int from, int to) {
        ArrayTaskList tasksInAInterval = new ArrayTaskList();
        for (int i = 0; i < countElements; i++) {
            if (!taskList[i].isActive()) {
                continue;
            }
            if (to > taskList[i].nextTimeAfter(from) && taskList[i].nextTimeAfter(from) != -1) {
                tasksInAInterval.add(taskList[i]);
            }
        }
        return tasksInAInterval;
    }
}