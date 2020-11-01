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
@Table(name = "user")
public class User {
    @Constraint(pk = true, notNull = true, autoInc = true)
    private Long id;
    private String name;
    private String number;
    private Integer password;
    private String image;
    @Constraint(notNull = true, unic = true)
    private String email;
}
