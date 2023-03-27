package ua.edu.sumdu.j2se.semenyako.tasks;

public class Task {

    public String title;
    public boolean active;
    public int time;
    public int start;
    public int end;
    public int interval;
    public boolean isRepeated;

    public Task(String title, int time) {
        this.title = title;
        this.time = time;
        isRepeated = false;
    }

    public Task(String title, int start, int end, int interval) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        isRepeated = true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getTime() {
        if (isRepeated) {
            return start;
        }
        return time;
    }

    public void setTime(int time) {
        this.time = time;
        if (isRepeated) {
            isRepeated = false;
            start = -1;
            end = -1;
            interval = -1;
        }
    }

    public int getStartTime() {
        if (!isRepeated) {
            return time;
        }
        return start;
    }

    public int getEndTime() {
        if (!isRepeated) {
            return time;
        }
        return end;
    }

    public int getRepeatInterval() {
        if (!isRepeated) {
            return 0;
        }
        return interval;
    }

    public void setTime(int start, int end, int interval) {
        this.start = start;
        this.end = end;
        this.interval = interval;
        if (!isRepeated) {
            isRepeated = true;
        }
    }

    public boolean isRepeated() {
        return isRepeated;
    }
}
