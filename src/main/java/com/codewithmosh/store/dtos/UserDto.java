package com.codewithmosh.store.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class UserDto {

    private Long id;
    private String name;
    private String email;

}
