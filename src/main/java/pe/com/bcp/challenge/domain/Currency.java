package pe.com.bcp.challenge.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Currency {
    private CurrencyType currencyType;
    private BigDecimal amount;
}
