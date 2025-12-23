package org.example.trainingspringboot.Entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Image {
    private int id;
    private String name;
    private String url;
    private User username;
}
