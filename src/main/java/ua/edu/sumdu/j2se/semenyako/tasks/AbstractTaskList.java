package ua.edu.sumdu.j2se.semenyako.tasks;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public abstract class AbstractTaskList implements Cloneable, Iterable<Task> {

    protected int countElements = 0;

    public abstract void add(Task task);

    public abstract boolean remove(Task task);

    public int size() {
        return countElements;
    }

    public abstract Task getTask(int index);

    public final AbstractTaskList incoming(LocalDateTime from, LocalDateTime to) {
        AbstractTaskList tasksInAInterval;
        try {
            tasksInAInterval = getClass().getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException
                | InvocationTargetException | NoSuchMethodException e) {
            throw new IllegalStateException();
        }
        getStream()
                .filter(x -> x.isActive())
                .filter(x -> x.nextTimeAfter(from).isBefore(to) && x.nextTimeAfter(from) != null)
                .forEach(x -> tasksInAInterval.add(x));
        return tasksInAInterval;
    }

    public Stream<Task> getStream() {
        Iterator<Task> taskIterator = iterator();
        List<Task> list = new ArrayList<>();
        while (taskIterator.hasNext()) {
            list.add(taskIterator.next());
        }
        return list.stream();
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
