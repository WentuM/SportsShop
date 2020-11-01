package orm.models;

import lombok.*;
import model.Manufacturer;
import model.Review;
import orm.annotation.Constraint;
import orm.annotation.ManyToOne;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@ToString
@orm.annotation.Table(name = "product")
public class Product {
    @Constraint(pk = true, notNull = true, autoInc = true)
    private Long id;
    private String name;
    private int price;
    private String description;
    private String imageProduct;
    private int count;
    private String category;
    private List<Review> list;
    @ManyToOne
    private Manufacturer manufacturer;



}
