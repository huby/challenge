package pe.com.bcp.challenge.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("EXCHANGE")
public class Exchange {

    @Id
    @Column("ID")
    private Integer id;
    @Column("FROM_CURRENCY")
    private CurrencyType fromCurrency;
    @Column("TO_CURRENCY")
    private CurrencyType toCurrency;
    @Column("EXCHANGE_RATE")
    private BigDecimal rate;
    @Column("EXCHANGE_DATE")
    private LocalDate exchangeDate;
    @Column("VERSION")
    private int version;
    @Column("STATUS")
    private int status;
}
