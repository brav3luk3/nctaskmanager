package ua.edu.sumdu.j2se.semenyako.tasks;

import java.time.LocalDateTime;
import java.util.*;

public class Tasks {

    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        SortedMap<LocalDateTime, Set<Task>> dateSetSortedMap = new TreeMap<>();
        for (Task task : tasks) {
            if (task.isActive()) {
                if (task.isRepeated()) {
                    for (LocalDateTime i = task.getStartTime();
                         i.isBefore(task.getEndTime()) || i.isEqual(task.getEndTime());
                         i = i.plusSeconds(task.getRepeatInterval())
                    ) {
                        if ((i.isAfter(start) && i.isBefore(end)) || i.isEqual(end)) {
                            Set<Task> initSet;
                            if (dateSetSortedMap.containsKey(i)) {
                                initSet = dateSetSortedMap.get(i);
                                initSet.add(task);
                                dateSetSortedMap.put(i, initSet);
                            } else {
                                initSet = new HashSet<>(Collections.singletonList(task));
                                dateSetSortedMap.put(i, initSet);
                            }
                        }
                    }
                } else {
                    if (task.getTime().isAfter(start) && task.getTime().isBefore(end)
                            || task.getTime().isEqual(start) || task.getTime().isEqual(end)) {
                        if (dateSetSortedMap.containsKey(task.getStartTime())) {
                            Set<Task> initSet = new HashSet<>();
                            initSet.add(task);
                            dateSetSortedMap.put(task.getTime(), initSet);
                        } else {
                            Set<Task> initSet = dateSetSortedMap.get(task.getStartTime());
                            initSet.add(task);
                            dateSetSortedMap.put(task.getStartTime(), initSet);
                        }
                    }
                }
            }
        }
        return dateSetSortedMap;
    }

    public static Iterable<Task> incoming(
            Iterable<Task> tasks, LocalDateTime start,
            LocalDateTime end) {
        Iterator<Task> iterator = tasks.iterator();
        do {
            try {
                if (iterator.next().nextTimeAfter(start).isAfter(end)) {
                    iterator.remove();
                }
            } catch (NullPointerException e) {
                iterator.remove();
            }
        } while (iterator.hasNext());
        return tasks;
    }


}
