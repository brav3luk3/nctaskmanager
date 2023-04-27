package ua.edu.sumdu.j2se.semenyako.tasks;

import java.util.Iterator;

public class LinkedTaskList extends AbstractTaskList implements Cloneable {

    private Node first;
    private Node last;

    @Override
    public void add(Task task) {
        Node newNode = new Node(task);
        if (first == null) {
            first = newNode;
            last = first;
        } else {
            last.next = newNode;
            last = newNode;
        }
        countElements++;
    }

    @Override
    public boolean remove(Task task) {
        Node currentNode = first;
        if (task.equals(first.task) && first == last) {
            first = null;
            last = null;
            countElements--;
            return true;
        }
        if (task.equals(first.task)) {
            first = first.next;
            countElements--;
            return true;
        }
        while (currentNode.next != null) {
            if (task.equals(currentNode.next.task)) {
                if (currentNode.next.next == null) {
                    last = currentNode;
                }
                currentNode.next = currentNode.next.next;
                countElements--;
                return true;
            } else {
                currentNode = currentNode.next;
            }
        }
        return false;
    }

    @Override
    public Task getTask(int index) {
        Node currentNode = first;
        if (index >= size() || index < 0) {
            throw new IndexOutOfBoundsException("Index must be < size and >= 0.");
        }
        for (int i = 0; i < size(); i++) {
            if (i == index) {
                return currentNode.task;
            } else {
                currentNode = currentNode.next;
            }
        }
        throw new IndexOutOfBoundsException("Index must be < size and >= 0.");
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < countElements; i++) {
            result.append(getTask(i));
        }
        return result.toString();
    }

    @Override
    public Iterator<Task> iterator() {
        return new LinkedTaskListIterator(this);
    }

    private class Node {
        private Task task;
        private Node next;

        private Node(Task task) {
            this.task = task;
            next = null;
        }
    }

    private class LinkedTaskListIterator implements Iterator<Task> {

        private Node current;
        private Node lastElement;
        private LinkedTaskList list;

        private LinkedTaskListIterator(LinkedTaskList tasks) {
            this.list = tasks;
            this.current = tasks.first;
            this.lastElement = null;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Task next() {
            if (hasNext()) {
                lastElement = current;
                current = current.next;
                return lastElement.task;
            }
            return null;
        }

        @Override
        public void remove() {
            if (lastElement == null) {
                throw new IllegalStateException();
            } else {
                list.remove(lastElement.task);
            }
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        LinkedTaskList clone = new LinkedTaskList();

        for (int i = 0; i < countElements; i++) {
            clone.add(getTask(i));
        }

        return clone;
    }

}
