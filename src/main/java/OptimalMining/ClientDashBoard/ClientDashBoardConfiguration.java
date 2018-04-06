package OptimalMining.ClientDashBoard;

import Database.DatabaseAccessor;
import crypto_currencies.CurrenciesShortName;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ClientDashBoardConfiguration {

    private boolean activateMining;
    Map<CurrenciesShortName, Boolean> minedCryptocurrencies = new HashMap<>();

    public ClientDashBoardConfiguration(String userEmail) {
        DatabaseAccessor databaseAccessor = new DatabaseAccessor(userEmail);
        activateMining = convertActivateMiningValue(databaseAccessor.getConfigFieldString("activate_mining"));
        for(CurrenciesShortName currency : CurrenciesShortName.values()) {
            Boolean isMinedCurrency = databaseAccessor.getConfigFieldBoolean(currency.toString());
            if(isMinedCurrency != null) {
                minedCryptocurrencies.put(currency, isMinedCurrency);
            }
        }
    }

    private boolean convertActivateMiningValue(String value) {
        if(value.equals("t")) {
            return true;
        } else if(value.equals("f")) {
            return false;
        }
        return false;
    }
}
