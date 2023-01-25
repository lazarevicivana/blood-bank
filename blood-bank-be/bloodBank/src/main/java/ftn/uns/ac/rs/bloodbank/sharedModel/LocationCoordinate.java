package ftn.uns.ac.rs.bloodbank.sharedModel;

import lombok.Builder;

@Builder
public record LocationCoordinate(Double longitude, Double latitude) {
}
