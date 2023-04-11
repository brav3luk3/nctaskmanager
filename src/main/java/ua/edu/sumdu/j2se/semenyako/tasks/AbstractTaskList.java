package ua.edu.sumdu.j2se.semenyako.tasks;

import java.lang.reflect.InvocationTargetException;

abstract public class AbstractTaskList {

    protected int countElements = 0;

    public abstract void add(Task task);

    public abstract boolean remove(Task task);

    public int size() {
        return countElements;
    }

    public abstract Task getTask(int index);

    public AbstractTaskList incoming(int from, int to) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        AbstractTaskList tasksInAInterval = getClass().getConstructor().newInstance();
        for (int i = 0; i < size(); i++) {
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
