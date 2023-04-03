package ua.edu.sumdu.j2se.semenyako.tasks;

import java.util.Objects;

public class Task {

    private String title;
    private boolean active;
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean isRepeated;

    public Task(String title, int time) throws IllegalArgumentException {
        if (title != null) {
            this.title = title;
        } else {
            throw new IllegalArgumentException("Title must be not NULL.");
        }
        if (time < 0) {
            throw new IllegalArgumentException("Time must be not < 0.");
        } else {
            this.time = time;
        }
        isRepeated = false;
    }

    public Task(String title, int start, int end, int interval) throws IllegalArgumentException {
        if (title != null) {
            this.title = title;
        } else {
            throw new IllegalArgumentException("Title must be not NULL.");
        }
        if (start < 0 && end < 0) {
            throw new IllegalArgumentException("Start and end must be not < 0.");
        } else {
            this.start = start;
            this.end = end;
        }
        if (interval > 0) {
            this.interval = interval;
        } else {
            throw new IllegalArgumentException("Interval must be > 0.");
        }
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

    public int nextTimeAfter(int current) {
        if (!active) {
            return -1;
        }
        if (!isRepeated) {
            if(time <= current) {
                return -1;
            }
            else
                return time;
        }
        else {
            if (current < start) {
                return start;
            }
            int start_i = start;
            int end_i = start_i + interval;
            while (start_i < end) {
                if (current < end_i) {
                    return end_i;
                }
                start_i = end_i;
                if (start_i + interval >= end) {
                    return -1;
                }
                else {
                    end_i = start_i + interval;
                }
            }
            return -1;
        }
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return active == task.active && time == task.time && start == task.start && end == task.end && interval == task.interval && isRepeated == task.isRepeated && Objects.equals(title, task.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, active, time, start, end, interval, isRepeated);
    }
}
