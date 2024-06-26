package net.javaguides.giftbackend.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DistrictsDto {
    private Long districtID;

    private String districtName;

    private String type;

    private Long provinceID;
}
