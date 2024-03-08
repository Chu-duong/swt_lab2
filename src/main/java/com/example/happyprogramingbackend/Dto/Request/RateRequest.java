package com.example.happyprogramingbackend.Dto.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RateRequest {
    private Long teacherId;
    private Float rateValue;

}
