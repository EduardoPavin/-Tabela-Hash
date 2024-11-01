import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.util.Locale;

public class PainelGraficoLinha extends JPanel {
    private List<ResultadoExperimento> resultados;

    public void setResultados(List<ResultadoExperimento> resultados) {
        this.resultados = resultados;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (resultados == null || resultados.isEmpty()) {
            g.drawString("Nenhum resultado disponível.", getWidth() / 2 - 50, getHeight() / 2);
            return;
        }

        desenharGraficoLinha(g);
    }

    private void desenharGraficoLinha(Graphics g) {
        // Configurações básicas
        int margemEsquerda = 80;
        int margemInferior = 80;
        int larguraGrafico = getWidth() - margemEsquerda - 50;
        int alturaGrafico = getHeight() - margemInferior - 50;

        // Preparar dados
        double maxValor = obterMaximoValorMetrica();
        int numPontos = resultados.size();

        // Desenhar eixos
        g.drawLine(margemEsquerda, getHeight() - margemInferior, margemEsquerda, 50);
        g.drawLine(margemEsquerda, getHeight() - margemInferior, getWidth() - 50, getHeight() - margemInferior);

        // Rótulos dos eixos
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("Experimentos", getWidth() / 2 - 50, getHeight() - 30);
        g.drawString("Colisões", margemEsquerda - 60, 60);

        // Desenhar linhas
        int espacamentoX = larguraGrafico / (numPontos - 1);
        int xAnterior = 0;
        int yAnterior = 0;

        for (int i = 0; i < numPontos; i++) {
            ResultadoExperimento resultado = resultados.get(i);
            double valorMetrica = resultado.getColisoes();

            int x = margemEsquerda + i * espacamentoX;
            int y = (int) (getHeight() - margemInferior - (valorMetrica / maxValor) * alturaGrafico);

            // Desenhar ponto
            g.setColor(Color.RED);
            g.fillOval(x - 3, y - 3, 6, 6);

            // Desenhar linha
            if (i > 0) {
                g.setColor(Color.BLUE);
                g.drawLine(xAnterior, yAnterior, x, y);
            }

            xAnterior = x;
            yAnterior = y;

            // Rótulos do eixo X
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.PLAIN, 10));
            String[] linhas = {resultado.getNomeFuncaoHash(), "T" + resultado.getTamanhoTabela(), "C" + resultado.getTamanhoConjunto()};
            int yLabel = getHeight() - margemInferior + 15;
            for (String linha : linhas) {
                int larguraTexto = g.getFontMetrics().stringWidth(linha);
                g.drawString(linha, x - larguraTexto / 2, yLabel);
                yLabel += 12;
            }
        }

        // Desenhar ticks e valores no eixo Y
        desenharEixoY(g, margemEsquerda, margemInferior, alturaGrafico, maxValor);
    }

    private double obterMaximoValorMetrica() {
        double maxValor = 1;
        for (ResultadoExperimento resultado : resultados) {
            double valor = resultado.getColisoes();
            if (valor > maxValor) {
                maxValor = valor;
            }
        }
        return maxValor;
    }

    private void desenharEixoY(Graphics g, int margemEsquerda, int margemInferior, int alturaGrafico, double maxValor) {
        int numDivisoes = 10;
        for (int i = 0; i <= numDivisoes; i++) {
            int y = (int) (getHeight() - margemInferior - i * (alturaGrafico / numDivisoes));
            g.drawLine(margemEsquerda - 5, y, margemEsquerda, y);
            String valor = String.valueOf((int) (i * (maxValor / numDivisoes)));
            int larguraTexto = g.getFontMetrics().stringWidth(valor);
            g.drawString(valor, margemEsquerda - larguraTexto - 10, y + 5);
        }
    }
}
