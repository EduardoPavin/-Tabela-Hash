import java.util.Random;

public class GeradorConjuntoDados {
    public static Registro[] gerarConjuntoDados(int tamanho, long semente) {
        Random random = new Random(semente); // Você pode alterar a seed (42)
        int[] numbers = new int[200];

        // Gerar 200 números aleatórios
        for (int i = 0; i < 200; i++) {
            numbers[i] = random.nextInt(900_000_000) + 100_000_000; // Gera números de 9 dígitos
        }

        // Criar o conjunto de dados repetindo os números gerados
        Registro[] conjuntoDados = new Registro[tamanho];
        for (int i = 0; i < tamanho; i++) {
            conjuntoDados[i] = new Registro(numbers[i % 200]);

            // Mensagem de progresso durante a geração dos registros
            if ((i + 1) % 100000 == 0 || i == tamanho - 1) {
                System.out.println("Gerados " + (i + 1) + " registros de " + tamanho);
            }
        }

        return conjuntoDados;
    }
}
