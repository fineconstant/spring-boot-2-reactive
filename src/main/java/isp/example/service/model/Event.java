package isp.example.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@ToString
public class Event {
    private final long id;
    private final LocalDateTime dateTime;
}
