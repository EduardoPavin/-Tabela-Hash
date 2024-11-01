public class FuncaoHashMultiplicacao implements FuncaoHash {
    private static final double A = 0.6180339887; // Constante (parte fracionária do número áureo)

    @Override
    public int hash(int chave, int tamanhoTabela) {
        double parteFracionaria = (chave * A) % 1;
        return (int) (tamanhoTabela * parteFracionaria);
    }
}
