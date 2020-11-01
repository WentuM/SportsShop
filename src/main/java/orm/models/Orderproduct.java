package orm.models;

import lombok.*;
import model.Order;
import model.Product;
import orm.annotation.Constraint;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@ToString
@orm.annotation.Table(name = "orderproduct")
public class Orderproduct {
    @Constraint(pk = true, notNull = true, autoInc = true)
    private Product product;
    private Order order;
    private int count;
    @Constraint(unic = true, notNull = true)
    private String flag_id;
}
