import java.io.FileWriter;
import java.io.IOException;

public class LogConversoes {
    public static void init() {
        String logMensagem = "Conversão realizada: 10 USD para 50 BRL";

        try (FileWriter fileWriter = new FileWriter("log_conversões.txt", true)) {
            fileWriter.write(logMensagem + "\n");  // Escreve a mensagem e quebra de linha
            System.out.println("Log gravado com sucesso.");
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao gravar o log: " + e.getMessage());
        }
    }
}
