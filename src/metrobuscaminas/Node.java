/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metrobuscaminas;

/**
 * Nodos correspondientes a la clase List.
 * 
 * @version 25/02/2025
 * @author Gabriel
 */
public class Node<T> 
{
    private Node<T> next;
    private T value;
    
    /**
     * Crea un nodo con un valor sin nodo siguiente.
     * @param value Valor almacenado por el nodo.
     */
    public Node(T value)
    {
        this.next = null;
        this.value = value;
    }

    /**
     * Obtiene el nodo siguiente al actual.
     * @return Nodo siguiente en la lista.
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     * Asigna el nodo siguiente al actual.
     * @param next El nodo siguiente en la lista.
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }

    /**
     * Obtiene el valor del nodo.
     * @return Valor del nodo.
     */
    public T getValue() {
        return value;
    }

    /**
     * Asigna el valor almacenado por el nodo.
     * @param value El valor a asignar al nodo.
     */
    public void setValue(T value) {
        this.value = value;
    }
}
