public class ResultadoExperimento {
    private int tamanhoConjunto;
    private int tamanhoTabela;
    private String nomeFuncaoHash;
    private double tempoInsercao;
    private int colisoes;
    private double tempoBusca;
    private int comparacoes;

    public ResultadoExperimento(int tamanhoConjunto, int tamanhoTabela, String nomeFuncaoHash,
                                double tempoInsercao, int colisoes, double tempoBusca, int comparacoes) {
        this.tamanhoConjunto = tamanhoConjunto;
        this.tamanhoTabela = tamanhoTabela;
        this.nomeFuncaoHash = nomeFuncaoHash;
        this.tempoInsercao = tempoInsercao;
        this.colisoes = colisoes;
        this.tempoBusca = tempoBusca;
        this.comparacoes = comparacoes;
    }

    // Getters
    public int getTamanhoConjunto() {
        return tamanhoConjunto;
    }

    public int getTamanhoTabela() {
        return tamanhoTabela;
    }

    public String getNomeFuncaoHash() {
        return nomeFuncaoHash;
    }

    public double getTempoInsercao() {
        return tempoInsercao;
    }

    public int getColisoes() {
        return colisoes;
    }

    public double getTempoBusca() {
        return tempoBusca;
    }

    public int getComparacoes() {
        return comparacoes;
    }
}
