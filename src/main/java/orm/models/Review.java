package orm.models;

import lombok.*;
import orm.annotation.Constraint;
import orm.annotation.ManyToOne;
import orm.annotation.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@ToString
@Table(name = "review")
public class Review {
    @Constraint(pk = true, notNull = true, autoInc = true)
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private int product_id;
    private String data;
    private String text;
}
