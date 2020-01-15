package pe.com.bcp.challenge.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeResponse {

    private Currency currencyOrigin;
    private Currency currencyDestination;
    private BigDecimal currencyRate;

}
