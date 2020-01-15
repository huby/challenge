package pe.com.bcp.challenge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pe.com.bcp.challenge.domain.Exchange;
import pe.com.bcp.challenge.domain.CurrencyType;
import pe.com.bcp.challenge.repository.CurrencyRateRepository;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ChallengeApplicationTests {

	@Autowired
	private CurrencyRateRepository currencyRateRepository;

	@Autowired
	private DatabaseClient databaseClient;

	@Test
	void contextLoads() {
	}

	@BeforeEach
	public void setup() {
		List<String> statements = Arrays.asList(//
				"DROP TABLE IF EXISTS EXCHANGE;",
				"create table exchange (" +
						"id INT AUTO_INCREMENT PRIMARY KEY"+
						", from_currency VARCHAR2(50)"+
						", to_currency VARCHAR2(50)"+
						", exchange_rate DECIMAL"+
						", exchange_date DATE"+
						", version INT"+
						", status INT"+
				");");

		statements.forEach(it -> databaseClient.execute(it)
				.fetch()
				.rowsUpdated()
				.as(StepVerifier::create)
				.expectNextCount(1)
				.verifyComplete());

	}

	@Test
	public void testLoad() {
		List<Exchange> exchanges = new ArrayList<>();
		Exchange exchange = new Exchange();

		exchange.setFromCurrency(CurrencyType.PEN);
		exchange.setToCurrency(CurrencyType.USD);
		exchange.setRate(new BigDecimal("3.34"));
		exchange.setExchangeDate(LocalDate.now());
		exchange.setVersion(1);
		exchange.setStatus(1);
		exchanges.add(exchange);

		currencyRateRepository.saveAll(exchanges).subscribe();

		currencyRateRepository.findByDate("PEN", "USD", LocalDate.now())
				.as(StepVerifier::create)
				.expectNextCount(1)
				.verifyComplete();

	}

}
