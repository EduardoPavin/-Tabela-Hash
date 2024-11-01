import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import java.util.Locale;

public class PainelGraficos extends JPanel {
    private List<ResultadoExperimento> resultados;

    private PainelGraficoLinha painelGraficoLinha;
    private PainelGraficoBarra painelGraficoBarra;

    public PainelGraficos() {
        setLayout(new GridLayout(2, 1)); // Dividir em duas linhas

        // Inicializar os subpainéis
        painelGraficoLinha = new PainelGraficoLinha();
        painelGraficoBarra = new PainelGraficoBarra();

        // Adicionar bordas e títulos aos painéis
        painelGraficoLinha.setBorder(BorderFactory.createTitledBorder("Gráfico de Linhas"));
        painelGraficoBarra.setBorder(BorderFactory.createTitledBorder("Gráfico de Barras"));

        // Adicionar os subpainéis ao painel principal
        add(painelGraficoLinha);
        add(painelGraficoBarra);
    }

    public void setResultados(List<ResultadoExperimento> resultados) {
        this.resultados = resultados;
        // Atualizar resultados nos subpainéis
        painelGraficoLinha.setResultados(resultados);
        painelGraficoBarra.setResultados(resultados);
    }
}
