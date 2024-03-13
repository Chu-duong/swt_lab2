package com.example.happyprogramingbackend.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchUserDto {
    private Long roleId;

    private String searchKey;

    private int page;

}
