import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class CotacaoResponse {
    // Atributos para representar a resposta da API
    @SerializedName("base")
    private String base;
    @SerializedName("rates")
    private Map<String, Double> rates;



    // Construtor padrão (opcional)
    public CotacaoResponse() {}

    // Getters e setters
    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;

    }

    @Override
    public String toString() {
        return
                "base='" + base + '\'' +
                ", rates=" + rates ;
    }
}
