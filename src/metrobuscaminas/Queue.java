/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metrobuscaminas;

/**
 *
 * @author Gabriel
 */
public class Queue<T> 
{
    private List<T> container;
    
    public Queue()
    {
        this.container = new List<>();
    }
    
    public void add(T value)
    {
        this.container.insert_back(value);
    }
    
    public T poll()
    {
        T value = this.container.get_first();
        this.container.remove_front();
        return value;
    }
    
    public int size() 
    { 
        return this.container.get_size(); 
    }
    
    public void clear()
    {
        this.container.clear();
    }
}
