/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metrobuscaminas;

/**
 * Estructura de datos que actúa como una pila. 
 * Los valores son agregados al final de la pila y eliminados del tope de la pila cuando sean utilizados.
 * 
 * @version 07/03/2025
 * @author Gabriel
 */
public class Stack<T>
{
    private List<T> container;
    
    /**
     * Inicializa el contenedor interno de la pila.
     */
    public Stack()
    {
        this.container = new List<>();
    }
    
    /**
     * Inserta un valor en el tope de la pila.
     * @param value Valor a insertar.
     */
    public void push(T value)
    {
        this.container.insert_back(value);
    }
    
    /**
     * Obtiene el valor en el tope de la pila. El valor es
     * eliminado de la pila.
     * 
     * @return Valor en el tope de la pila.
     */
    public T pop()
    {
        if(this.container.get_size() <= 0)
            return null;
        
        T value = this.container.get_last();
        this.container.remove_back();
        return value;
    }
    
    /**
     * Obtiene el tamaño (cantidad de elementos) de la pila.
     * @return Cantidad de elementos en la pila.
     */
    public int size()
    {
        return this.container.get_size();
    }
    
    /**
     * Limpia todos los valores de la pila.
     */
    public void clear()
    {
        this.container.clear();
    }
}
