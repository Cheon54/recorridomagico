/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recorridomagico;

import java.util.LinkedList;
import java.util.Stack;

/**
 *
 * @author angel
 */
public class Recorrido {
    private int fil;
    private int col;
    private int matriz[][];
    private int matrizp[][];
    private long contadorintentos;
    private long contadorsoluciones;
    private LinkedList pila = new LinkedList();
    
    //camino más corto
    private int matrizmejor[][];
    private int mejorsuma=Integer.MAX_VALUE;
    private Object pilamejor = new LinkedList();
    
    public Recorrido(int[][] matriz){
        //inicializar parametros
        this.fil= matriz.length;
        this.col = matriz[0].length;
        this.matriz=matriz;
        this.matrizp = new int[fil][col];
        this.matrizmejor = new int[fil][col];
        //mostrar datos iniciales
        mostrarMatriz();
        System.out.println("Filas: "+fil+" Columnas: "+col);
        System.out.println("~~~~~~~~~~~~~~~");
    }
    public void resolver(){
        resolver(0,0);
        System.out.println("Intentos : "+contadorintentos);
        System.out.println("Soluciones : "+contadorsoluciones);
    }
    private void resolver(int fila, int columna){
        contadorintentos++;
        if(llegoAlFinal(fila, columna)){
            //marcar pasado el último elemento e ingresar a la pila
            matrizp[fila][columna]=1;
            pila.push(matriz[fila][columna]);
            //mostrar solucion
            mostrarRecorrido();
            //sacar de la pila, para otras soluciones
            pila.pop();
            contadorsoluciones++;
            return;
        }
        if(!coordenadasSonValidas(fila, columna)){
            return;
        }
        if(coordenadaPasada(fila,columna)){
            return;
        }
        //marcar pasados y ingresar a la pila
        matrizp[fila][columna]=1;
        pila.push(matriz[fila][columna]);
        //moverse por la matriz
        //derecha
            resolver(fila,columna+1);
        //izquierda
            resolver(fila,columna-1);
        //abajo
            resolver(fila+1,columna);
        //arriba
            resolver(fila-1,columna);
        
        //desmarcar pasados y sacar de la pila
        pila.pop();
        matrizp[fila][columna]= 0;
    }
    private boolean llegoAlFinal(int fila, int columna) {
        if(fila == fil-1 && columna == col-1)return true;
        return false;
    }
    private void mostrarMatriz() {
        //Datos ingresados
        for(int i=0;i<fil;i++){
            for(int j=0;j<col;j++){
                System.out.print("["+matriz[i][j]+"] ");
            }
            System.out.println("");
        }
        System.out.println("~~~~~~~~~~~~~~~");
        
    }
    private void mostrarRecorrido() {
        //mostrar matriz de recorrido
        for(int i=0;i<fil;i++){
            for(int j=0;j<col;j++){
                System.out.print("["+matrizp[i][j]+"] ");
            }
            System.out.println("");
        }
        System.out.print("Recorrido : ");
        //duplicar pila a 2 pilas, para una de éstas devovlerla a la original.
        Object pila2 = mostrarPila(pila);
        //Mostrar recorrido y la suma de la solución
        System.out.print(pila2);
        System.out.println("");
        sumaSolucion(matrizp);
        System.out.println("~~~~~~~~~~~~~~~");
    }
    public LinkedList mostrarPila(LinkedList pi){
        LinkedList pila2 = new LinkedList();
        LinkedList pila3 = new LinkedList();
        while(!pi.isEmpty()){
            Object a = pi.pop();
            pila2.push(a);
            pila3.push(a);
        }
        while(!pila3.isEmpty()){
            pi.push(pila3.pop());
        }
        return pila2;
    }
    private boolean coordenadasSonValidas(int fila, int columna) {
        if(fila<0 || fila>=fil){return false;}
        if(columna<0 || columna>=col){return false;}
        return true;
    }

    private boolean coordenadaPasada(int fila, int columna) {
        if(matrizp[fila][columna] == 1){
            return true;
        }
        return false;
    }

    private void sumaSolucion(int[][] matrizp) {
        //sumar verificando matriz de recorrido
        int sumat=0;
        for(int i=0;i<fil;i++){
            for(int j=0;j<col;j++){
                if(matrizp[i][j]==1){
                    sumat=sumat+matriz[i][j];
                }
            }
        }
        System.out.println("Suma Total= "+sumat+"");
        
        
        //Elegir mejor opción
        if( sumat < mejorsuma){
            mejorsuma = sumat;
            for(int i=0;i<fil;i++){
                for(int j=0;j<col;j++){
                    matrizmejor[i][j] = matrizp[i][j];
                }
            }
            pilamejor = mostrarPila(pila);
        }
    }
    public void mostrarMasCorto(){
        System.out.println("~~~~~~~~~~~~~~");
        System.out.println("Camino más corto");
        for(int i=0;i<fil;i++){
            for(int j=0;j<col;j++){
                System.out.print("["+matrizmejor[i][j]+"] ");
            }
            System.out.println("");
        }
        System.out.println("Recorrido : "+pilamejor);
        System.out.println("Mejor Suma: "+mejorsuma);
    }
}
