import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PrincipalConversorMoeda {

    private CotacaoResponse processarResposta(String jsonResponse) {
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(jsonResponse).getAsJsonObject();
        Gson gson = new Gson();
        return gson.fromJson(jsonObject, CotacaoResponse.class);
    }

    public static void main(String[] args) throws Exception {

        System.out.println("*********************************************");
        System.out.println("Seja bem-vindo/a ao Conversor de Moeda =]");
        Scanner scanner = new Scanner(System.in);
        CotacaoAPI cotacaoAPI = new CotacaoAPI();

        try (FileWriter fileWriter = new FileWriter("log_conversões.txt", true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            int opcao;

            do {
                System.out.println("Escolha uma opção:");
                System.out.println("1) Dólar => Peso argentino");
                System.out.println("2) Peso argentino => Dólar");
                System.out.println("3) Dólar => Real brasileiro");
                System.out.println("4) Real brasileiro => Dólar");
                System.out.println("5) Euro => Real brasileiro");
                System.out.println("6) Real brasileiro => Euro");
                System.out.println("7) Sair");
                System.out.println("*********************************************");
                System.out.print("Digite uma opção: ");

                opcao = scanner.nextInt();

                switch (opcao) {
                    case 1:
                        double valorDolar = obterValorUsuario(scanner, "dólares");
                        double taxaDolarParaPeso = cotacaoAPI.obterCotacao("USD", "ARS");
                        double resultado = valorDolar * taxaDolarParaPeso;
                        System.out.println(valorDolar + " dólares equivalem a " + resultado + " pesos argentinos.");
                        bufferedWriter.write(String.valueOf(resultado));
                        bufferedWriter.newLine();
                        break;
                    case 2:
                        double valorPeso = obterValorUsuario(scanner, "pesos argentinos");
                        taxaDolarParaPeso = cotacaoAPI.obterCotacao("ARS", "USD");
                        resultado = valorPeso * taxaDolarParaPeso;
                        System.out.println(valorPeso + " pesos argentinos equivalem a " + resultado + " dólares.");
                        bufferedWriter.write(String.valueOf(resultado));
                        bufferedWriter.newLine();
                        break;
                    case 3:
                        double valorDolar3 = obterValorUsuario(scanner, "dólares");
                        double taxaDolarParaReal = cotacaoAPI.obterCotacao("USD", "BRL");
                        double resultado3 = valorDolar3 * taxaDolarParaReal;
                        System.out.println(valorDolar3 + " dólares equivalem a " + resultado3 + " reais.");
                        bufferedWriter.write(String.valueOf(resultado3));
                        bufferedWriter.newLine();
                        break;
                    case 4:
                        double valorReal = obterValorUsuario(scanner, "reais");
                        double taxaRealParaDolar = cotacaoAPI.obterCotacao("BRL", "USD");
                        resultado = valorReal * taxaRealParaDolar;
                        System.out.println(valorReal + " reais equivalem a " + resultado + " dólares.");
                        bufferedWriter.write(String.valueOf(resultado));
                        bufferedWriter.newLine();
                        break;
                    case 5:
                        double valorEuro = obterValorUsuario(scanner, "euros");
                        double taxaEuroParaReal = cotacaoAPI.obterCotacao("EUR", "BRL");
                        resultado = valorEuro * taxaEuroParaReal;
                        System.out.println(valorEuro + " euros equivalem a " + resultado + " reais.");
                        bufferedWriter.write(String.valueOf(resultado));
                        bufferedWriter.newLine();
                        break;
                    case 6:
                        valorReal = obterValorUsuario(scanner, "reais");
                        double taxaRealParaEuro = cotacaoAPI.obterCotacao("BRL", "EUR");
                        resultado = valorReal * taxaRealParaEuro;
                        System.out.println(valorReal + " reais equivalem a " + resultado + " euros.");
                        bufferedWriter.write(String.valueOf(resultado));
                        bufferedWriter.newLine();
                        break;
                    case 7:
                        System.out.println("Obrigado por usar nosso conversor de moedas");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } while (opcao != 7);
        } catch (Exception e) {
            System.err.println("Ocorreu um erro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static double obterValorUsuario(Scanner scanner, String moeda) {
        double valor = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            System.out.print("Digite o valor em " + moeda + ": ");
            try {
                valor = scanner.nextDouble();
                entradaValida = true;  // Marca como válida e encerra o loop
            } catch (InputMismatchException e) {
                System.out.println("Erro: Valor não numérico inserido. Por favor, digite um número.");
                scanner.next();  // Limpa o valor inválido do scanner
            } catch (NumberFormatException e) {
                System.out.println("Erro de formato: Certifique-se de usar ponto para decimais.");
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        }
        return valor;
    }
}
