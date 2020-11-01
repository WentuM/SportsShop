package orm.models;

import lombok.*;
import model.Product;
import model.User;
import orm.annotation.Constraint;
import orm.annotation.ManyToOne;
import orm.annotation.Table;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@ToString
@Table(name = "`order`")
public class Order {
    @Constraint(pk = true, notNull = true, autoInc = true)
    private Long id;
    @ManyToOne
    private User user;
    private int total_price;
    private List<Product> productList;
    private int buyed;
}
