/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metrobuscaminas;

/**
 * Estructura de datos correspondiente a una lista enlazada. Almacena valores en nodos enlazados al siguiente valor de la lista.
 * 
 * @version 07/03/2025
 * @author Gabriel
 */
public class List<T> 
{
    private Node<T> first;
    private Node<T> last; // Mejorar tiempo de inserción en la cola
    private int size;
    
    /**
     * Inicializa la lista.
     */
    public List()
    {
        this.clear();
    }
    
    /**
     * Limpia todos los valores de la lista.
     */
    public void clear()
    {
        this.first = null;
        this.last = null;
        this.size = 0;
    }
    
    /**
     * Inserta un valor al frente de la lista.
     * @param value Valor a añadir.
     */
    public void insert_front(T value)
    {
        Node<T> new_node = new Node(value);
        new_node.setNext(this.first);
        this.first = new_node;
        
        if(this.last == null)
            this.last = this.first;
        
        this.size++;
    }
    
    /**
     * Inserta un valor al final de la lista.
     * @param value Valor a añadir.
     */
    public void insert_back(T value)
    {
        if(this.last == null)
        {
            this.insert_front(value);
            return;
        }
        
        Node<T> new_node = new Node(value);
        this.last.setNext(new_node);;
        this.last = new_node;
        this.size++;
    }
    
    /**
     * Elimina el primer valor de la lista.
     */
    public void remove_front()
    {
        if(this.size == 0)
            return;
        
        if(this.size == 1)
        {
            this.clear();
            return;
        }
        
        this.first = this.first.getNext();
        this.size--;
    }
    
    /**
     * Elimina el último valor de la lista.
     */
    public void remove_back()
    {
        if(this.size == 0)
            return;
        
        if(this.size == 1)
        {
            this.clear();
            return;
        }
        
        Node<T> node = this.first;
        while(node.getNext() != this.last)
        {
            node = node.getNext();
        }
        
        node.setNext(null);
        this.last = node;
        this.size--;
    }
    
    /**
     * Obtiene un valor de la lista por su índice.
     * @param index El índice del valor a obtener.
     * @return Optional que almacena el valor obtenido, o vacío si el índice es inválido.
     */
    public Optional<T> get(int index)
    {
        if(index < 0 || index >= this.size)
            return new Optional(EmptyOptionalValue.EMPTY);
        
        Node<T> node = this.first;
        for(int i = 0; i < index; ++i)
            node = node.getNext();
        
        return new Optional(node.getValue());
    }
    
    /**
     * Asigna un valor a un nodo en la lista.
     * @param index El índice del nodo al cual asignar el valor.
     * @param value El valor a asignar.
     */
    public void set(int index, T value)
    {
        if(index < 0 || index >= this.size)
            return;
        
        Node<T> node = this.first;
        for(int i = 0; i < index; ++i)
            node = node.getNext();
        
        node.setValue(value);
    }
    
    /**
     * Obtiene el primer objeto de nodo de la lista.
     * @return El primer objeto de nodo de la lista.
     */
    public Node<T> get_first_node() { return this.first; }
    
    /**
     * Obtiene el último objeto de nodo de la lista.
     * @return El último objeto de nodo de la lista.
     */
    public Node<T> get_last_node() { return this.last; }
    
    /**
     * Obtiene el primer valor de la lista.
     * @return El primer valor de la lista.
     */
    public T get_first() { return this.first.getValue(); }
    
    /**
     * Obtiene el último valor de la lista.
     * @return El último valor de la lista.
     */
    public T get_last() { return this.last.getValue(); }
    
    /**
     * Comprueba si el valor existe al menos una vez en la lista.
     * @param value Valor a comprobar.
     * @return El primer índice encontrado del valor, o -1 si el valor no existe en la lista.
     */
    public int has_value(T value)
    {
        Node<T> node = this.first;
        int i = 0;
        
        while(node != null)
        {
            if(node.getValue().equals(value))
                return i;
            
            node = node.getNext();
            i++;
        }
        
        return -1;
    }
    
    /**
     * Obtiene el primer índice de un valor en la lista.
     * 
     * @param value El valor a encontrar su primer índice.
     * @return El primer índice encontrado del valor, o -1 si el valor no existe en la lista.
     */
    public int get_first_index_of(T value)
    {
        return this.has_value(value);
    }
    
    /**
     * Obtiene el tamaño (cantidad de elementos) de la lista.
     * @return Cantidad de elementos en la lista.
     */
    public int get_size() { return this.size; }
}
