package com.illimity.mostafa.movements;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Document
@Data
@NoArgsConstructor
public class AccountMovements {
    @NotNull
    @NotEmpty
    private String customerId;
    @Id
    private String movementId;
    private String description;
    private Double amount;
    private LocalDateTime date;
    private String currency;
}
