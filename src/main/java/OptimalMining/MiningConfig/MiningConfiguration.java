package OptimalMining.MiningConfig;

import OptimalMining.Configuration;
import crypto_currencies.CurrenciesShortName;
import lombok.Data;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

//TODO
@Data
public class MiningConfiguration implements Configuration {

    Boolean activateMining;
    List<CurrenciesShortName> currenciesToMine;

    public MiningConfiguration() {
        activateMining = true;
        currenciesToMine = new LinkedList<>();
    }

    public JSONObject toJson() {
        JSONObject finalObject = new JSONObject();
        try {
            finalObject.put("activateMining", activateMining);
            finalObject.put("currenciesToMine", currenciesToMine);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return finalObject;
    }
}