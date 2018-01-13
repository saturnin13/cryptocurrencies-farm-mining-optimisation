package Server.RequestHandler;

import OptimalMining.ClientConfig.ClientConfiguration;
import OptimalMining.ClientDashBoard.ClientDashBoardConfiguration;
import OptimalMining.MiningConfig.MiningConfiguration;
import OptimalMining.OptimalMiningConfigCalculation.OptimalMiningConfigCalculation;
import Server.dataExchangeAnalyser.DataExchangeMedium;
import crypto_currencies.currencies_retrieval.CoinWarzCurrencyInformationRetriever;

public class CryptoCurrencyOptimalMiningConfigHandler implements Handler {

    //TODO: implement this
    @Override
    public MiningConfiguration handle(DataExchangeMedium requestData) {
        // TODO: add dashboard interface (electricity cost and consumption)
        ClientDashBoardConfiguration clientDashBoardConfiguration = new ClientDashBoardConfiguration();
        ClientConfiguration clientConfig = new ClientConfiguration(requestData);
        OptimalMiningConfigCalculation optimalMiningConfigCalculation = new OptimalMiningConfigCalculation();
        CoinWarzCurrencyInformationRetriever coinWarzCurrencyInformationRetriever = new CoinWarzCurrencyInformationRetriever();

        return optimalMiningConfigCalculation.calculateOptimalConfig(clientConfig, clientDashBoardConfiguration,coinWarzCurrencyInformationRetriever);
    }
}