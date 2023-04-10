package ua.edu.sumdu.j2se.semenyako.tasks;

public class LinkedTaskList {

    private Node first;
    private Node last;
    private int countElements = 0;

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

    public int size() {
        return countElements;
    }

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

    private class Node {
        private Task task;
        private Node next;

        public Node(Task task) {
            this.task = task;
            next = null;
        }
    }
}
