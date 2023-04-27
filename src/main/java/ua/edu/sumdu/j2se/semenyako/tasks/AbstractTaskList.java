package ua.edu.sumdu.j2se.semenyako.tasks;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

abstract class AbstractTaskList implements Cloneable, Iterable<Task> {

    protected int countElements = 0;

    public abstract void add(Task task);

    public abstract boolean remove(Task task);

    public int size() {
        return countElements;
    }

    public abstract Task getTask(int index);

    public AbstractTaskList incoming(int from, int to) {
        AbstractTaskList tasksInAInterval;
        try {
            tasksInAInterval = getClass().getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException
                | InvocationTargetException | NoSuchMethodException e) {
            return null;
        }
        for (int i = 0; i < size(); i++) {
                if (!getTask(i).isActive()) {
                    continue;
                }
                if (to > getTask(i).nextTimeAfter(from)
                        && getTask(i).nextTimeAfter(from) != -1) {
                    tasksInAInterval.add(getTask(i));
                }
        }
        return tasksInAInterval;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractTaskList that = (AbstractTaskList) o;
        if (this.size() != that.size()) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            if (!this.getTask(i).equals(that.getTask(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(countElements);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
