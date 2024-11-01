import java.util.Random;

public class Experimento {
    private int[] tamanhosTabela;
    private FuncaoHash[] funcoesHash;
    private String[] nomesFuncoesHash;
    private int[] tamanhosConjuntoDados;
    private long semente;
    private PainelResultados painelResultados;
    private PainelGraficos painelGraficos;

    public Experimento(PainelResultados painelResultados, PainelGraficos painelGraficos) {

        this.tamanhosTabela = new int[]{10000, 100000, 1000000};

        // Funções hash
        this.funcoesHash = new FuncaoHash[]{
                new FuncaoHashDivisao(),
                new FuncaoHashMultiplicacao(),
                new FuncaoHashDobramento()
        };
        this.nomesFuncoesHash = new String[]{"Divisão", "Multiplicação", "Dobramento"};

        // Conjuntos de dados
        this.tamanhosConjuntoDados = new int[]{100_000, 500_000, 1_000_000};
        this.semente = 42L; // Você pode alterar a semente conforme necessário

        this.painelResultados = painelResultados;
        this.painelGraficos = painelGraficos;
    }

    public void executar() {
        for (int tamanhoConjunto : tamanhosConjuntoDados) {
            System.out.println("Gerando conjunto de dados com " + tamanhoConjunto + " registros...");
            Registro[] conjuntoDados = GeradorConjuntoDados.gerarConjuntoDados(tamanhoConjunto, semente);

            for (int tamanhoTabela : tamanhosTabela) {
                for (int i = 0; i < funcoesHash.length; i++) {
                    FuncaoHash funcaoHash = funcoesHash[i];
                    String nomeFuncaoHash = nomesFuncoesHash[i];

                    System.out.println("Executando experimento: Conjunto " + tamanhoConjunto + ", Tabela " + tamanhoTabela + ", Função Hash " + nomeFuncaoHash);

                    // Encadeamento
                    executarExperimentoEncadeamento(conjuntoDados, tamanhoConjunto, tamanhoTabela, funcaoHash, nomeFuncaoHash);

                    System.out.println("Experimento concluído: Conjunto " + tamanhoConjunto + ", Tabela " + tamanhoTabela + ", Função Hash " + nomeFuncaoHash);
                }
            }
        }
    }

    private void executarExperimentoEncadeamento(Registro[] conjuntoDados, int tamanhoConjunto, int tamanhoTabela, FuncaoHash funcaoHash, String nomeFuncaoHash) {
        TabelaHashEncadeamento tabelaHash = new TabelaHashEncadeamento(tamanhoTabela, funcaoHash);

        long tempoInicio = System.nanoTime();
        for (int i = 0; i < conjuntoDados.length; i++) {
            tabelaHash.inserir(conjuntoDados[i]);


            if ((i + 1) % 100000 == 0 || i == conjuntoDados.length - 1) {
                System.out.println("Inseridos " + (i + 1) + " registros de " + conjuntoDados.length);
            }
        }
        long tempoFim = System.nanoTime();
        double tempoInsercao = (tempoFim - tempoInicio) / 1e9;
        int colisoes = tabelaHash.getColisoes();

        // Busca
        int comparacoes = 0;
        tempoInicio = System.nanoTime();


        for (int j = 0; j < 5; j++) {
            Registro registroBusca = conjuntoDados[j % conjuntoDados.length];
            Registro resultado = tabelaHash.buscar(registroBusca.getCodigo());
            if (resultado != null) {
                comparacoes++;
            }
        }
        tempoFim = System.nanoTime();
        double tempoBusca = (tempoFim - tempoInicio) / 1e9;


        ResultadoExperimento resultado = new ResultadoExperimento(
                tamanhoConjunto, tamanhoTabela, nomeFuncaoHash,
                tempoInsercao, colisoes, tempoBusca, comparacoes
        );


        painelResultados.adicionarResultado(resultado);

        // Atualizar gráficos
        painelGraficos.setResultados(painelResultados.getResultados());
    }
}
