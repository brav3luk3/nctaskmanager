package ua.edu.sumdu.j2se.semenyako.tasks;

import java.time.LocalDateTime;
import java.util.Objects;

public class Task implements Cloneable {

    private String title;
    private boolean active;
    private LocalDateTime time;
    private LocalDateTime start;
    private LocalDateTime end;
    private int interval;
    private boolean isRepeated;

    public Task(String title, LocalDateTime time) {
        if (title != null) {
            this.title = title;
        } else {
            throw new IllegalArgumentException("Title must be not NULL.");
        }
        if (time != null) {
            this.time = time;
        } else {
            throw new IllegalArgumentException("Time must be not < 0.\ttime = null");
        }
        isRepeated = false;
    }

    public Task(String title, LocalDateTime start, LocalDateTime end, int interval) {
        if (title != null) {
            this.title = title;
        } else {
            throw new IllegalArgumentException("Title must be not NULL.");
        }
        if (start != null && end != null) {
            this.start = start;
            this.end = end;
        } else {
            throw new IllegalArgumentException("Start and end must be not < 0.");
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

    public LocalDateTime  getTime() {
        if (isRepeated) {
            return start;
        }
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
        if (isRepeated) {
            isRepeated = false;
            start = null;
            end = null;
            interval = -1;
        }
    }

    public LocalDateTime getStartTime() {
        if (!isRepeated) {
            return time;
        }
        return start;
    }

    public LocalDateTime getEndTime() {
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

    public void setTime(LocalDateTime start, LocalDateTime end, int interval) {
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

    public LocalDateTime nextTimeAfter(LocalDateTime current) {
        if (!this.active) {
            return null;
        }
        if (!isRepeated) {
            if(current.isAfter(this.time) || current.isEqual(this.time)) {
                return null;
            }
            else
                return time;
        }
        else {
            if (current.isBefore(this.start)) {
                return start;
            }
            LocalDateTime start_i = start;
            LocalDateTime end_i = start_i.plusSeconds(this.interval);
            while (start_i.isBefore(end)) {
                if (end_i.isAfter(end)) {
                    return null;
                }
                if (current.isBefore(end_i)) {
                    return end_i;
                }
                if (end_i.isEqual(end)) {
                    return null;
                }
                start_i = end_i;
                end_i = start_i.plusSeconds(this.interval);
            }
            return null;
        }
    }

    @Override
    public String toString() {
        return "Task:" +
                "\t\ttitle: " + title +
                "\t\ttime: " + time +
                "\t\tstart: " + start +
                "\t\tend: " + end +
                "\t\tinterval: " + interval +
                "\t\tactive: " + active +
                "\t\trepeated: " + isRepeated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        if (isRepeated != task.isRepeated()) {
            return false;
        }
        if(isRepeated) {
            return start == task.start &&
                    end == task.end &&
                    interval == task.interval &&
                    active == task.active &&
                    Objects.equals(title, task.title);
        } else {
            return time == task.time &&
                    active == task.active &&
                    Objects.equals(title, task.title);
        }
    }

    @Override
    public int hashCode() {
        if (isRepeated){
            return Objects.hash(title, start, end, interval, active, true);
        }
        else
            return Objects.hash(title, time, active, false);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
