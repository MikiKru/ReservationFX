package model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class Event {
    private String name;
    private String description;
    private LocalDateTime startTime;
    private String[] type;
    private int availablePlaces;
}
