package ua.edu.sumdu.j2se.semenyako.tasks;

import java.util.Arrays;

public class ArrayTaskList extends AbstractTaskList {
    private Task[] taskList;
    private int arraySize = 10;

    public ArrayTaskList() {
        taskList = new Task[arraySize];
    }

    @Override
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

    @Override
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

    @Override
    public Task getTask(int index) {
        if (index >= size() || index < 0) {
            throw new IndexOutOfBoundsException("Index must be < size and >= 0.");
        }
        return taskList[index];
    }

    @Override
    public String toString() {
        return "ArrayTaskList{" +
                "tasklist=" + Arrays.toString(taskList) +
                ", listsize=" + arraySize +
                '}';
    }
}