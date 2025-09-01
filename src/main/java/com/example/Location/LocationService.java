package com.example.Location;

import com.example.Location.Dto.LocationDTO;
import com.example.feign.PackageServiceClient;
import com.example.Location.Mapper.LocationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import feign.FeignException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor  // Lombok pour injecter les dépendances
public class LocationService {
    private final LocationRepository repository;
    private final LocationMapper mapper;
    private final PackageServiceClient packageServiceClient;  // FeignClient

    public LocationDTO create(LocationDTO dto) {
        try {
            if (!packageServiceClient.validateLocation(dto.getLocationId())) {
                throw new IllegalArgumentException("Invalid location ID: " + dto.getLocationId());
            }
        } catch (FeignException.NotFound ex) {
            throw new IllegalStateException("Service distant indisponible ou endpoint incorrect pour l'ID: " + dto.getLocationId(), ex);
        } catch (FeignException ex) {
            throw new IllegalStateException("Erreur lors de l'appel au service distant: " + ex.getMessage(), ex);
        }
        Location entity = mapper.toEntity(dto);
        Location saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    public List<LocationDTO> readAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public LocationDTO readById(String id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Location non trouvée"));
    }
}