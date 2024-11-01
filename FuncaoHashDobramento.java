public class FuncaoHashDobramento implements FuncaoHash {
    @Override
    public int hash(int chave, int tamanhoTabela) {
        String chaveStr = String.valueOf(Math.abs(chave));
        int soma = 0;
        for (int i = 0; i < chaveStr.length(); i += 3) {
            int fim = Math.min(i + 3, chaveStr.length());
            soma += Integer.parseInt(chaveStr.substring(i, fim));
        }
        return soma % tamanhoTabela;
    }
}
