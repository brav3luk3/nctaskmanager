package ua.edu.sumdu.j2se.semenyako.tasks;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayTaskList extends AbstractTaskList implements Cloneable {
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
        if(task.equals(getTask(countElements-1))) {
            taskList[countElements-1] = null;
            matchCheck = true;
        } else {
            for (int i = 0; i < countElements; i++) {
                if (task.equals(getTask(i))) {
                    matchCheck = true;
                }
                if (matchCheck && i < countElements - 1) {
                    taskList[i] = getTask(i + 1);
                }
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

    @Override
    public Object clone() throws CloneNotSupportedException {
        ArrayTaskList clone = (ArrayTaskList) super.clone();
        clone.taskList = new Task[arraySize];
        for (int i = 0; i < size(); i++) {
            clone.taskList[i] = taskList[i];
        }
        return clone;
    }

    @Override
    public Iterator<Task> iterator() {
        return new ArrayListIterator(this);
    }

    public class ArrayListIterator implements Iterator<Task>{

        private ArrayTaskList array;
        private int index;
        private int last;

        public ArrayListIterator(ArrayTaskList array) {
            this.array = array;
            this.index = 0;
            this.last = -1;
        }

        @Override
        public boolean hasNext() {
            return (index < countElements);
        }

        @Override
        public Task next() {
            if (hasNext()) {
                last = index;
                return array.getTask(index++);
            }
            return null;
        }

        @Override
        public void remove()
        {
            if (last < 0) {
                throw new IllegalStateException();
            } else {
                array.remove(array.getTask(last));
                index = last;
            }
        }
    }
}