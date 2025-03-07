/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metrobuscaminas;

/**
 *
 * @author Gabriel
 */
public class List<T> 
{
    private Node<T> first;
    private Node<T> last; // Mejorar tiempo de inserción en la cola
    private int size;
    
    public List()
    {
        this.clear();
    }
    
    public void clear()
    {
        this.first = null;
        this.last = null;
        this.size = 0;
    }
    
    public void insert_front(T value)
    {
        Node<T> new_node = new Node(value);
        new_node.setNext(this.first);
        this.first = new_node;
        
        if(this.last == null)
            this.last = this.first;
        
        this.size++;
    }
    
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
    
    public Optional<T> get(int index)
    {
        if(index < 0 || index >= this.size)
            return new Optional(EmptyOptionalValue.EMPTY);
        
        Node<T> node = this.first;
        for(int i = 0; i < index; ++i)
            node = node.getNext();
        
        return new Optional(node.getValue());
    }
    
    public void set(int index, T value)
    {
        if(index < 0 || index >= this.size)
            return;
        
        Node<T> node = this.first;
        for(int i = 0; i < index; ++i)
            node = node.getNext();
        
        node.setValue(value);
    }
    
    public Node<T> get_first_node() { return this.first; }
    public Node<T> get_last_node() { return this.last; }
    public T get_first() { return this.first.getValue(); }
    public T get_last() { return this.last.getValue(); }
    
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
    
    public int get_first_index_of(T value)
    {
        return this.has_value(value);
    }
    
    public int get_size() { return this.size; }
}
