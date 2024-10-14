import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
//import org.json.JSONObject;

public class CotacaoAPI {
    // Chave da API para acessar o serviço de cotação
    private final String apiKey = "1d6d395cacbb1a91c7eade75";

    // Objeto Gson para converter JSON para objetos Java
    private final Gson gson = new Gson();

    // Método para obter a cotação de uma moeda em relação a outra
    public double obterCotacao(String moedaBase, String moedaDestino) throws Exception {
        // Constrói a URL da requisição à API
        String url = "https://api.exchangerate-api.com/v4/latest/USD" + moedaBase + "?base=" + moedaBase + "&symbols=" + moedaDestino;

        // Cria um cliente HTTP para realizar a requisição
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("apikey", apiKey)
                .build();

        // Envia a requisição e obtém a resposta
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Converte a resposta JSON para um objeto CotacaoResponse
        CotacaoResponse cotacaoResponse = gson.fromJson(response.body(), CotacaoResponse.class);

        // Retorna a taxa de conversão para a moeda de destino
        return cotacaoResponse.getRates().get(moedaDestino);
    }
}
