package Control;

import static View.Caixa_GUI.itens;
import static View.Caixa_GUI.preco;
import static View.Caixa_GUI.precoUnitario;
import static View.Caixa_GUI.produto_txt;
import static View.Caixa_GUI.qtd_txt;
import static View.Caixa_GUI.valor_txt;
import static View.Caixa_GUI.qtd;
import static View.Cliente_GUI.cpf_txt;
import static View.Cliente_GUI.nome_txt;
import static View.Cliente_GUI.tel_txt;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.PrintJob;
import java.awt.Toolkit;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

public class Engine_DAO {
    public static int cont=0;
    static double total=0;
    static String ac1="";
    static String ac2="";
    static double ac3=0;
    static String ac4="";
    static String ac5="";
    public static String vetorProduto[] = new String [1000];
    public static String vetorQtd[] = new String [1000];
    public static String vetorValorUni[] = new String [1000];
    public static String vetorValor[] = new String [1000];
    public static String metodo = "";
    
    public static void informacoes(){
        // recebe os valores de texto das caixas
        String prod = produto_txt.getText();
        String valor = valor_txt.getText();
        String quanti = qtd_txt.getText();
        // converte os valores em real
        double val = Double.parseDouble(valor);
        double q = Double.parseDouble(quanti);
       
        // multiplica quantidade por valor e armazena em total
        total = total + (val*q);
        
       double total2 = val*q;
       double valorUnitario = total2/q;
       String tot2 = String.valueOf(total2);
               
       // acumula valores para exibição na caixa de texto
       ac1 = ac1 + prod + "\n";
       ac2 = ac2 + tot2 + "\n";
       ac3 = ac3+total2;
       ac4 = ac4 + valorUnitario + "\n";
       ac5 = ac5 + quanti + "\n";
       cont++;
       
       
        vetorQtd[cont] = quanti;
        vetorValorUni[cont] = String.valueOf(valorUnitario);
        vetorValor[cont] = tot2;
        vetorProduto[cont] = prod;
        itens.setText(ac1);
        preco.setText(ac2);
        precoUnitario.setText(ac4);
        qtd.setText(ac5);
        produto_txt.setText("");
        valor_txt.setText("");
        qtd_txt.setText("");
    }


    public static void impressao() {
        
        LocalDate data = java.time.LocalDate.now();
        String recebe = data.format(DateTimeFormatter.ISO_DATE);
        System.out.println(recebe);
        Frame f = new Frame("Imp");
        Toolkit tk = f.getToolkit();
        PrintJob pj = tk.getPrintJob(f, "Recibo", null);
        String nomeCliente = nome_txt.getText();
        String cpfCliente = cpf_txt.getText();
        String telCliente = tel_txt.getText();
        if (pj != null) {
            Graphics m = pj.getGraphics();
        int pixel = 100;
            while (cont != 0){
             m.drawString("----------------------------------------------------------------MERCADO TAL----------------------------------------------------------------", 1, 60);
             m.drawString("Nome do Produto                              Quantidade                          Valor Unitário                              Valor Total", 1, 80);
             m.drawString(""+vetorProduto[cont], 1, pixel);
             m.drawString("                                                          "+vetorQtd[cont], 2, pixel);//15 + 1 espaço
             m.drawString("                                                                                                       "+vetorValorUni[cont], 3, pixel);//24 + 2 espaços
             m.drawString("                                                                                                                                                          "+vetorValor[cont], 4, pixel);//36
             cont--;
             pixel = pixel + 20;
            }
            m.drawString("------------------------------------------------------------------------------------------------------------------------------------------------------", 1, pixel);
            m.drawString("TOTAL:                                                                                                                                              "+ac3, 1, pixel+20);
            m.drawString("------------------------------------------------------------------------------------------------------------------------------------------------------", 1, pixel+40);
            m.drawString("CLIENTE: "+nomeCliente, 1, pixel+60);
            m.drawString("CPF: "+cpfCliente, 1, pixel+80);
            m.drawString("TELEFONE: "+telCliente, 1, pixel+100);
            m.drawString("------------------------------------------------------------------------------------------------------------------------------------------------------", 1, pixel+120);
            m.drawString("MÉTODO DE PAGAMENTO: "+metodo, 1, pixel+140);
            m.dispose();
            m.finalize();
            pj.end();
            pj.finalize();
        }
        f.dispose();
 }
}