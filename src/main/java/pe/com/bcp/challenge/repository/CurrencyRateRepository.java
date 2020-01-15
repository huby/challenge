package pe.com.bcp.challenge.repository;


import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import pe.com.bcp.challenge.domain.Exchange;
import reactor.core.publisher.Mono;

import java.time.LocalDate;


public interface CurrencyRateRepository extends ReactiveCrudRepository<Exchange, String> {

    @Query("select * from exchange e where e.from_currency = :fromCurrency and e.to_currency = :toCurrency and e.exchange_date = :exchangeDate")
    Mono<Exchange> findByDate(@Param("fromCurrency") String fromCurrency, @Param("toCurrency") String toCurrency, @Param("exchangeDate") LocalDate exchangeDate);
}
