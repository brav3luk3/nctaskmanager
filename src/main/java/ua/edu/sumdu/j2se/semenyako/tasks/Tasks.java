package ua.edu.sumdu.j2se.semenyako.tasks;

import java.time.LocalDateTime;
import java.util.*;

public class Tasks {

    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        SortedMap<LocalDateTime, Set<Task>> dateSetSortedMap = new TreeMap<>();
        for (Task task : tasks) {
            if (!task.isActive()) {
                continue;
            }
            if (task.isRepeated()) {
                for (LocalDateTime i = task.getStartTime();
                     i.isBefore(task.getEndTime()) || i.isEqual(task.getEndTime());
                     i = i.plusSeconds(task.getRepeatInterval())) {
                    if(i.isBefore(start) || i.isAfter(end)) {
                        continue;
                    }
                    Set<Task> initSet;
                    if (dateSetSortedMap.containsKey(i)) {
                        dateSetSortedMap.get(i).add(task);
                    } else {
                        initSet = new HashSet<>(Collections.singletonList(task));
                        dateSetSortedMap.put(i, initSet);
                    }
                }
            } else {
                if(task.getTime().isBefore(start) || task.getTime().isAfter(end)) {
                    continue;
                }
                if (dateSetSortedMap.containsKey(task.getStartTime())) {
                    dateSetSortedMap.get(task.getStartTime()).add(task);
                } else {
                    Set<Task> initSet = new HashSet<>();
                    initSet.add(task);
                    dateSetSortedMap.put(task.getTime(), initSet);
                }
            }
        }
        return dateSetSortedMap;
    }

    public static Iterable<Task> incoming(
            Iterable<Task> tasks, LocalDateTime start,
            LocalDateTime end) {
        Iterator<Task> iterator = tasks.iterator();
        while(iterator.hasNext()) {
            Task iteratorNext = iterator.next();
            if (iteratorNext.nextTimeAfter(start) == null || iteratorNext.nextTimeAfter(start).isAfter(end)) {
                iterator.remove();
            }
        }
        return tasks;
    }


}
