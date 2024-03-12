package model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class Toys {
    private String name;
    private int type;
    private double price;
    private int quantity;
}