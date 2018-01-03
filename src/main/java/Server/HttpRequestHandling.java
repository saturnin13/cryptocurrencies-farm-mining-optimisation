package Server;

import OptimalMining.MiningConfig.MiningConfiguration;
import Server.AccessSecurity.RequestAuthorisation;
import Server.RequestHandler.CryptoCurrencyOptimalMiningConfigHandler;
import Server.dataExchangeAnalyser.DataExchangeMedium;
import Server.dataExchangeAnalyser.DataExchangeMediumFactory;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import crypto_currencies.currencies_retrieval.CoinWarzCurrencyInformationRetriever;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

import static Server.AccessSecurity.AuthorisedRequests.OptimalCryptoMining;

// TODO: look at jetty and use itpor
public class HttpRequestHandling {

    private final static Logger logger = Logger.getLogger(CoinWarzCurrencyInformationRetriever.class);

    //TODO clean this function
    public static void startServer(int portNumber) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(portNumber), 0);
        server.createContext("/", new ClientConfigOptimal());
        server.setExecutor(null); // creates a default executor
        server.start();
        logger.info("Http server running on port " + portNumber);
    }

    public static class ClientConfigOptimal implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            // TODO: convert request body bytes to String text
            logger.info("Optimal mining config request received");
            String requestBody = IOUtils.toString(t.getRequestBody(), StandardCharsets.US_ASCII);
            DataExchangeMedium dataExchangeMedium = DataExchangeMediumFactory.getDataExchangeMedium(requestBody);
            if (dataExchangeMedium == null || !RequestAuthorisation.hasAuthorisedId(dataExchangeMedium.getUserId(), OptimalCryptoMining)) {
                t.sendResponseHeaders(404, 0);
                t.getResponseBody().close();
                logger.warn("Unauthorised request received");
                return;
            }

            MiningConfiguration responseConfig = new CryptoCurrencyOptimalMiningConfigHandler().handle(dataExchangeMedium);
            String response = responseConfig.toJson().toString();
            //TODO: remove next line
            response = response + "tests";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
            logger.info("Optimal mining config request completed");
        }
    }

}
