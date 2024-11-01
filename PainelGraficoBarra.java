import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.util.Locale;

public class PainelGraficoBarra extends JPanel {
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

        desenharGraficoBarra(g);
    }

    private void desenharGraficoBarra(Graphics g) {
        // Configurações básicas
        int margemEsquerda = 80;
        int margemInferior = 80;
        int larguraDisponivel = getWidth() - margemEsquerda - 50;
        int alturaDisponivel = getHeight() - margemInferior - 50;

        // Encontrar o valor máximo para escalonar o gráfico
        double maxValor = obterMaximoValorMetrica();

        // Calcular o número total de barras e o espaçamento
        int numBarras = resultados.size();
        int larguraBarra = 30;
        int espacamentoEntreBarras = 20;

        // Ajustar a largura da barra e o espaçamento se necessário
        int larguraTotalBarras = numBarras * larguraBarra + (numBarras - 1) * espacamentoEntreBarras;
        if (larguraTotalBarras > larguraDisponivel) {
            // Reduzir a largura da barra e o espaçamento proporcionalmente
            double escala = (double) larguraDisponivel / larguraTotalBarras;
            larguraBarra = (int) (larguraBarra * escala);
            espacamentoEntreBarras = (int) (espacamentoEntreBarras * escala);
            larguraTotalBarras = numBarras * larguraBarra + (numBarras - 1) * espacamentoEntreBarras;
        }

        // Desenhar eixo Y
        g.drawLine(margemEsquerda, getHeight() - margemInferior, margemEsquerda, 50);
        // Desenhar eixo X
        g.drawLine(margemEsquerda, getHeight() - margemInferior, getWidth() - 50, getHeight() - margemInferior);

        // Rótulos dos eixos
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("Experimentos", getWidth() / 2 - 50, getHeight() - 30);
        g.drawString("Colisões", margemEsquerda - 60, 60);

        // Desenhar barras
        int xInicial = margemEsquerda + (larguraDisponivel - larguraTotalBarras) / 2;
        int x = xInicial;

        g.setFont(new Font("Arial", Font.PLAIN, 10));

        for (ResultadoExperimento resultado : resultados) {
            double valorMetrica = resultado.getColisoes();
            int alturaBarra = (int) ((valorMetrica / maxValor) * alturaDisponivel);
            int y = getHeight() - margemInferior - alturaBarra;

            // Desenhar a barra
            g.setColor(Color.BLUE);
            g.fillRect(x, y, larguraBarra, alturaBarra);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, larguraBarra, alturaBarra);

            // Desenhar o valor acima da barra
            g.drawString(String.valueOf((int) valorMetrica), x + larguraBarra / 2 - 5, y - 5);

            // Desenhar o rótulo abaixo da barra
            String[] linhas = {resultado.getNomeFuncaoHash(), "T" + resultado.getTamanhoTabela(), "C" + resultado.getTamanhoConjunto()};
            int yLabel = getHeight() - margemInferior + 15;
            for (String linha : linhas) {
                int larguraTexto = g.getFontMetrics().stringWidth(linha);
                g.drawString(linha, x + (larguraBarra - larguraTexto) / 2, yLabel);
                yLabel += 12;
            }

            x += larguraBarra + espacamentoEntreBarras;
        }

        // Desenhar ticks e valores no eixo Y
        desenharEixoY(g, margemEsquerda, margemInferior, alturaDisponivel, maxValor);
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
            int y = getHeight() - margemInferior - (int) (i * (double) alturaGrafico / numDivisoes);
            g.drawLine(margemEsquerda - 5, y, margemEsquerda, y);
            String valor = String.valueOf((int) (i * (double) maxValor / numDivisoes));
            int larguraTexto = g.getFontMetrics().stringWidth(valor);
            g.drawString(valor, margemEsquerda - larguraTexto - 10, y + 5);
        }
    }
}
