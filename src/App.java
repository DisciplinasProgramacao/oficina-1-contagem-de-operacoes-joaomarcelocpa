import java.util.Random;

public class App {

    static int[] tamanhosTesteGrande =  { 125_000, 250_000, 500_000, 1_000_000, 2_000_000 };
    static int[] tamanhosTesteMedio =   {  12_500,  25_000,  50_000,   100_000,   200_000 };
    static int[] tamanhosTestePequeno = {       3,       6,      12,        24,        48 };
    static Random aleatorio = new Random(42);

    // Percorre um vetor de inteiros e soma os valores dos elementos que estão em posições pares
    //O(n/2) = O(n)
    static int[] codigo1(int[] vetor) {
        int resposta = 0;
        int operacoes = 0;
        for (int i = 0; i < vetor.length; i += 2) {
            resposta += vetor[i] % 2;
            operacoes++;
        }
        return new int[] { resposta, operacoes };
    }

    // Percorre um vetor de inteiros do último elemento até o primeiro, dividindo o índice por 2 a cada iteração, e conta o número total de iterações realizadas.
    //O(n)
    static int codigo2(int[] vetor) {
        int contador = 0;
        for (int k = (vetor.length - 1); k > 0; k /= 2) {
            for (int i = 0; i <= k; i++) {
                contador++;
            }
        }
        return contador;
    }


    // Ordena por seleção um vetor de inteiros em ordem crescente, encontrando o menor elemento da parte não ordenada e colocando-o no início
    //O(n^2)
    static int codigo3(int[] vetor) {
        int trocas = 0;
        for (int i = 0; i < vetor.length - 1; i++) {
            int menor = i;
            for (int j = i + 1; j < vetor.length; j++) {
                if (vetor[j] < vetor[menor])
                    menor = j;
            }
            if (menor != i) {
                int temp = vetor[i];
                vetor[i] = vetor[menor];
                vetor[menor] = temp;
                trocas++;
            }
        }
        return trocas;
    }


    // Calcula o n-ésimo termo da sequência de Fibonacci recursivamente Fibonacci => (F(n) = F(n-1) + F(n-2))
    //O(2^n)
    static int[] codigo4(int n) {
        if (n <= 2) {
            return new int[]{1, 1};
        } else {
            int[] resultado1 = codigo4(n - 1);
            int[] resultado2 = codigo4(n - 2);
            return new int[]{resultado1[0] + resultado2[0], resultado1[1] + resultado2[1] + 1};
        }
    }


    // Gera um vetor de inteiros aleatórios
    static int[] gerarVetor(int tamanho) {
        int[] vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = aleatorio.nextInt(1, Math.max(2, tamanho / 2));
        }
        return vetor;
    }


    public static void main(String[] args) {
        // Teste para o código 1 (Teste Grande)
        for (int tamanho : tamanhosTesteGrande) {
            int[] vetor = gerarVetor(tamanho);
            long inicio = System.nanoTime();
            int[] resultado1 = codigo1(vetor);
            long tempo1 = System.nanoTime() - inicio;
            System.out.println("Código 1 - Tamanho: " + tamanho + ", Operações: " + resultado1[1] + ", Tempo: " + tempo1 + " ns");
        }

        // Teste para o código 2 (Teste Grande)
        for (int tamanho : tamanhosTesteGrande) {
            int[] vetor = gerarVetor(tamanho);
            long inicio = System.nanoTime();
            int resultado2 = codigo2(vetor);
            long tempo2 = System.nanoTime() - inicio;
            System.out.println("Código 2 - Tamanho: " + tamanho + ", Operações: " + resultado2 + ", Tempo: " + tempo2 + " ns");
        }

        // Teste para o código 3 (Teste Médio)
        for (int tamanho : tamanhosTesteMedio) {
            int[] vetor = gerarVetor(tamanho);
            long inicio = System.nanoTime();
            int trocas = codigo3(vetor);
            long tempo = System.nanoTime() - inicio;
            System.out.println("Código 3 - Tamanho: " + tamanho + ", Operações: " + trocas + ", Tempo: " + tempo + " ns");
        }

        // Teste para o código 4 (Teste Pequeno)
        for (int n : tamanhosTestePequeno) {
            long inicio = System.nanoTime();
            int[] resultado = codigo4(n);
            long tempo = System.nanoTime() - inicio;
            System.out.println("Código 4 - Tamanho: " + n + ", Operações: " + resultado[1] + ", Tempo: " + tempo + " ns");
        }
    }
}