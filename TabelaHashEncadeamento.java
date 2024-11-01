public class TabelaHashEncadeamento {
    private No[] tabela;
    private FuncaoHash funcaoHash;
    private int colisoes;

    public TabelaHashEncadeamento(int tamanho, FuncaoHash funcaoHash) {
        this.tabela = new No[tamanho];
        this.funcaoHash = funcaoHash;
        this.colisoes = 0;
    }

    public void inserir(Registro registro) {
        int indice = funcaoHash.hash(registro.getCodigo(), tabela.length);
        if (tabela[indice] == null) {
            tabela[indice] = new No(registro);
        } else {
            colisoes++;
            No atual = tabela[indice];
            while (atual.proximo != null) {
                atual = atual.proximo;
            }
            atual.proximo = new No(registro);
        }
    }

    public Registro buscar(int codigo) {
        int indice = funcaoHash.hash(codigo, tabela.length);
        No atual = tabela[indice];
        while (atual != null) {
            if (atual.registro.getCodigo() == codigo) {
                return atual.registro;
            }
            atual = atual.proximo;
        }
        return null;
    }

    public int getColisoes() {
        return colisoes;
    }

    private class No {
        Registro registro;
        No proximo;

        public No(Registro registro) {
            this.registro = registro;
            this.proximo = null;
        }
    }
}
