package orm.models;

import lombok.*;
import orm.annotation.Constraint;
import orm.annotation.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@ToString
@Table(name = "manufacturer")
public class Manufacturer {
    @Constraint(pk = true, notNull = true, autoInc = true)
    private Long id;
    private String name;
    private String description;
    private String manufacturerImage;
}
