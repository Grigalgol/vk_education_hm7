package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private @NotNull Integer id;
    private @NotNull String name;
    private @NotNull Integer companyId;
    private @NotNull Integer count;
}
