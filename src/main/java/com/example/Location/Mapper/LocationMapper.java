package com.example.Location.Mapper;

import com.example.Location.Dto.LocationDTO;
import com.example.Location.Location;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public abstract class LocationMapper {
    public abstract LocationDTO toDto(Location entity);
    public abstract Location toEntity(LocationDTO dto);
}
