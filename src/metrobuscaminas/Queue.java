/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metrobuscaminas;

/**
 * Estructura de datos de cola. Añade valores al final de la cola y elimina el valor al inicio de la cola cuando sea utilizado.
 * 
 * @version 07/03/2025
 * @author Gabriel
 */
public class Queue<T> 
{
    private List<T> container;
    
    /**
     * Inicializa el contenedor interno de la cola.
     */
    public Queue()
    {
        this.container = new List<>();
    }
    
    /**
     * Añade un valor al final de la cola.
     * @param value Valor a añadir.
     */
    public void add(T value)
    {
        this.container.insert_back(value);
    }
    
    /**
     * Obtiene el valor al inicio de la cola. El valor es eliminado de la cola.
     * @return El valor al inicio de la cola.
     */
    public T poll()
    {
        if(this.container.get_size() == 0)
            return null;
        
        T value = this.container.get_first();
        this.container.remove_front();
        return value;
    }
    
    /**
     * Obtiene el tamaño (cantidad de elementos) de la cola.
     * @return Cantidad de elementos en la cola.
     */
    public int size() 
    { 
        return this.container.get_size(); 
    }
    
    /**
     * Limpia todos los valores de la cola.
     */
    public void clear()
    {
        this.container.clear();
    }
}
