/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recorridomagico;

/**
 *
 * @author angel
 */
public class RecorridoMagico {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int[][] matriz = new int[2][3];
        for (int i = 0; i < matriz.length; i++) {
            for (int j=0;j<matriz[0].length;j++ ) {
                matriz[i][j]=(int)(Math.random()*15)+5;
            }

        }

        Recorrido re = new Recorrido(matriz);
        re.resolver();
        re.mostrarMasCorto();

    }
}
