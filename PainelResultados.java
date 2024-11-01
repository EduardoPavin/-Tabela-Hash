import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Collectors;
import java.util.Locale;


public class PainelResultados extends JPanel {
    private JTable tabela;
    private DefaultTableModel modeloTabela;

    public PainelResultados() {
        setLayout(new BorderLayout());

        String[] colunas = {"Conjunto", "Tamanho Tabela", "Função Hash", "Tempo Inserção (s)", "Colisões", "Tempo Busca (s)", "Comparações"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabela = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabela);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void adicionarResultado(ResultadoExperimento resultado) {
        modeloTabela.addRow(new Object[]{
                resultado.getTamanhoConjunto(),
                resultado.getTamanhoTabela(),
                resultado.getNomeFuncaoHash(),
                String.format(Locale.US, "%.4f", resultado.getTempoInsercao()),
                resultado.getColisoes(),
                String.format(Locale.US, "%.4f", resultado.getTempoBusca()),
                resultado.getComparacoes()
        });
    }


    public List<ResultadoExperimento> getResultados() {
        // Retorna todos os resultados da tabela
        int rowCount = modeloTabela.getRowCount();
        return IntStream.range(0, rowCount)
                .mapToObj(i -> new ResultadoExperimento(
                        Integer.parseInt(modeloTabela.getValueAt(i, 0).toString()),
                        Integer.parseInt(modeloTabela.getValueAt(i, 1).toString()),
                        modeloTabela.getValueAt(i, 2).toString(),
                        Double.parseDouble(modeloTabela.getValueAt(i, 3).toString()),
                        Integer.parseInt(modeloTabela.getValueAt(i, 4).toString()),
                        Double.parseDouble(modeloTabela.getValueAt(i, 5).toString()),
                        Integer.parseInt(modeloTabela.getValueAt(i, 6).toString())
                )).collect(Collectors.toList());
    }
}
