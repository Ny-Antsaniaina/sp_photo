package org.example.trainingspringboot.Entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserLogin {
    private String username;
    private String password;
}
