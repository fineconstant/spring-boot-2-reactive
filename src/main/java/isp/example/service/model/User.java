package isp.example.service.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@ToString
@Document
@NoArgsConstructor
public class User {

    @Id
    private String id;
    private String name;

    public User(String name) {
        this.name = name;
    }
}
