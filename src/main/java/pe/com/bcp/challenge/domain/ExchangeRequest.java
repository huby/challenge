package pe.com.bcp.challenge.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRequest {
    @NotNull
    private Currency currencyRequest;
    @NotNull
    private CurrencyType toCurrencyType;
}
