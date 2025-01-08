package org.example.studyplanner;

public class TimelineView {
    // Mantemos o método na TimelineView, mas delegamos a implementação para HabitTracker
    public String habitDateViewAll(HabitTracker ht){
        return ht.habitDateViewAll();
    }
}