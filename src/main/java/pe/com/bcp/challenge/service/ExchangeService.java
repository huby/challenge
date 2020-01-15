package pe.com.bcp.challenge.service;

import org.springframework.stereotype.Service;
import pe.com.bcp.challenge.domain.Currency;
import pe.com.bcp.challenge.domain.Exchange;
import pe.com.bcp.challenge.domain.ExchangeRequest;
import pe.com.bcp.challenge.domain.ExchangeResponse;
import pe.com.bcp.challenge.exception.ExchangeException;
import pe.com.bcp.challenge.repository.CurrencyRateRepository;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;


@Service
public class ExchangeService {

    private final CurrencyRateRepository currencyRateRepository;

    public ExchangeService(CurrencyRateRepository currencyRateRepository) {
        this.currencyRateRepository = currencyRateRepository;
    }

    public Mono<ExchangeResponse> processExchange(ExchangeRequest exchangeRequest) {

        Currency currencyRequest = exchangeRequest.getCurrencyRequest();

        if (currencyRequest.getCurrencyType().equals(exchangeRequest.getToCurrencyType())) {
            throw new ExchangeException("");
        }

        Mono<Exchange> currencyRate = this.currencyRateRepository.findByDate(exchangeRequest.getCurrencyRequest().getCurrencyType().name(),
                exchangeRequest.getToCurrencyType().name(), LocalDate.now());

        Mono<ExchangeResponse> exchange = currencyRate.map(exchange1 -> {
            BigDecimal finalAmount = exchange1.getRate().multiply(exchangeRequest.getCurrencyRequest().getAmount());
            Currency currencyFinal = new Currency(exchangeRequest.getToCurrencyType(), finalAmount);
            return new ExchangeResponse(exchangeRequest.getCurrencyRequest(), currencyFinal, exchange1.getRate());
        });

        return exchange;
    }

    public Mono<Exchange> updateExchangeRate(Exchange exchange) {
        Mono<Exchange> exchangeMono = this.currencyRateRepository.findByDate(exchange.getFromCurrency().name(), exchange.getToCurrency().name(), LocalDate.now())
                                        .doOnNext(exchange1 -> {
                                            exchange1.setRate(exchange.getRate());
                                        })
                                        .flatMap(crr -> this.currencyRateRepository.save(crr));
        return exchangeMono;
    }

}
