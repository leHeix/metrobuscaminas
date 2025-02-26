/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metrobuscaminas;

/**
 *
 * @author Gabriel
 */
public class Node<T> 
{
    private Node<T> next;
    private T value;
    
    public Node(T value)
    {
        this.next = null;
        this.value = value;
    }

    /**
     * @return Nodo siguiente en la lista
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     * @param next El nodo siguiente en la lista
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }

    /**
     * @return Valor del nodo
     */
    public T getValue() {
        return value;
    }

    /**
     * @param value El valor a asignar al nodo
     */
    public void setValue(T value) {
        this.value = value;
    }
}
