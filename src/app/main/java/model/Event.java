package model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@AllArgsConstructor
public class Event {
    private String name;
    private String description;
    private LocalDateTime startTime;
    private String[] type;
    private int availablePlaces;

    public void decrementPlaces(int number){
        availablePlaces -= number;
    }

    @Override
    public String toString() {
        return name + " (" + startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ")";
    }
}
