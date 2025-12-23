package org.example.trainingspringboot.Entity;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User {
    private Integer id;
    private String username;
    private String name ;
    private String password;
    private Date createdAt;
}
