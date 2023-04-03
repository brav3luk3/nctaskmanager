package ua.edu.sumdu.j2se.semenyako.tasks;

import java.util.Arrays;

public class LinkedTaskList {
    public Task[] taskList;
    public int arraySize = 10;
    public int countElements = 0;


    public LinkedTaskList() {
        taskList = new Task[arraySize];
    }

    public void add(Task task) {
        if (countElements == arraySize) {
            resize();
        }
        taskList[countElements] = task;
        countElements++;
    }

    private void resize() {
        arraySize = arraySize * 2;
        Task[] temp = new Task[arraySize];
        for (int i = 0; i < countElements; i++) {
            temp[i] = getTask(i);
        }
        taskList = temp;
    }

    public boolean remove(Task task) {
        boolean matchCheck = false;
        for (int i = 0; i < countElements; i++) {
            if(task.equals(getTask(i))) {
                matchCheck = true;
            }
            if (matchCheck && i < countElements - 1) {
                taskList[i] = getTask(i + 1);
            }
        }
        if (matchCheck) {
            countElements--;
            return true;
        } else {
            return false;
        }
    }

    public int size() {
        return countElements;
    }

    public Task getTask(int index) throws IndexOutOfBoundsException {
        if (index < size() && index >= 0) {
            return taskList[index];
        } else {
            throw new IndexOutOfBoundsException("Index must be < size and >= 0.");
        }
    }

    @Override
    public String toString() {
        return "ArrayTaskList{" +
                "tasklist=" + Arrays.toString(taskList) +
                ", listsize=" + arraySize +
                '}';
    }

    public LinkedTaskList incoming(int from, int to) {
        LinkedTaskList tasksInAInterval = new LinkedTaskList();
        for (int i = 0; i < countElements; i++) {
            if (!getTask(i).isActive()) {
                continue;
            }
            if (to > getTask(i).nextTimeAfter(from) && getTask(i).nextTimeAfter(from) != -1) {
                tasksInAInterval.add(getTask(i));
            }
        }
        return tasksInAInterval;
    }
}
