import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class JanelaPrincipal extends JFrame {
    private PainelResultados painelResultados;
    private PainelGraficos painelGraficos;

    public JanelaPrincipal() {
        setTitle("Análise de Tabelas Hash");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        painelResultados = new PainelResultados();
        painelGraficos = new PainelGraficos();

        // Usando JTabbedPane para alternar entre Tabela e Gráficos
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Resultados", painelResultados);
        tabbedPane.addTab("Gráficos", painelGraficos);

        add(tabbedPane);

        // Iniciar os experimentos em uma nova thread para não travar a interface
        new Thread(() -> {
            Experimento experimento = new Experimento(painelResultados, painelGraficos);
            experimento.executar();
        }).start();
    }
}
