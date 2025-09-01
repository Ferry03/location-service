package com.example.Location.Dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class LocationDTO {

        private String locationId;
        private String city;
        private String zone;
        private boolean checkpointAvailable;


}
