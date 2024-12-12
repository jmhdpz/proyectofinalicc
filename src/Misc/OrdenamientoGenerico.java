package src.Misc;
/**
* Clase generica para ordenar arreglos de objetos comparables mediante BubbleSort.
* @author José María Hernández Pérez y Aldo Enrique Yañez Ramirez
* @version 1.0 
* @date 15-Dic-2024 
*/

public class OrdenamientoGenerico<T extends Comparable<T>> {
    
    /**
     * Arreglo generico a ordenar.
     */
    protected T[] arreglo;

    /**
     * Constructor por parametro. Recibe un arreglo generico que es el que se desea ordenar
     * @param aComparar - El arreglo a ordenar.
     */
    public OrdenamientoGenerico(T[] aComparar){
        arreglo = aComparar;
    }
    
    /**
     * @return T[] - El arreglo ordenado, mediante BubbleSort.
     */
    public T[] ordenar() {
        for (int i = 0; i < arreglo.length-1; i++) {
            for (int j = 0; j < arreglo.length-1; j++) {
                if (arreglo[j].compareTo(arreglo[j + 1]) > 0) {
                    T temp = arreglo[j];
                    arreglo[j] = arreglo[j + 1];
                    arreglo[j + 1] = temp;
                }
            }
        }
    return arreglo;
    }
}
