public class FuncaoHashDivisao implements FuncaoHash {
    @Override
    public int hash(int chave, int tamanhoTabela) {
        return Math.abs(chave) % tamanhoTabela;
    }
}
