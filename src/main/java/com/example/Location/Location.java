package com.example.Location;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "locations")
public class Location {
    @Id
    private String locationId;

    private String city;
    private String zone;
    private boolean checkpointAvailable;


}