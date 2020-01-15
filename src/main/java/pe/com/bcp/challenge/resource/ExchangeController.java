package pe.com.bcp.challenge.resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.bcp.challenge.domain.Exchange;
import pe.com.bcp.challenge.domain.ExchangeRequest;
import pe.com.bcp.challenge.domain.ExchangeResponse;
import pe.com.bcp.challenge.service.ExchangeService;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {

    private final ExchangeService exchangeService;

    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @PostMapping("/process")
    public Mono<ExchangeResponse> process(@Valid @RequestBody ExchangeRequest exchangeRequest) {
        return this.exchangeService.processExchange(exchangeRequest);
    }

    @PostMapping("/rate")
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<Exchange> updateExchangeRate(@Valid @RequestBody Exchange exchange) {
        return this.exchangeService.updateExchangeRate(exchange);
    }

}
