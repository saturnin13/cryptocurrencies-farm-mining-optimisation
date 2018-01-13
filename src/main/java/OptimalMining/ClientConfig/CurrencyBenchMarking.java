package OptimalMining.ClientConfig;

import crypto_currencies.CurrenciesShortName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CurrencyBenchMarking {
    CurrenciesShortName currenciesShortNames;
    double hashRate;
}
