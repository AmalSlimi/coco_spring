package tn.esprit.coco.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class RoomDto {
    private String roomType;
    private float rent;
    private String amenities;
    private String roomDetails;
    private Long accommodationID;
}

