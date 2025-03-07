/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metrobuscaminas;

/**
 *
 * @author Gabriel
 */
public class Stack<T>
{
    private List<T> container;
    
    public Stack()
    {
        this.container = new List<>();
    }
    
    public void push(T value)
    {
        this.container.insert_back(value);
    }
    
    public T pop()
    {
        if(this.container.get_size() <= 0)
            return null;
        
        T value = this.container.get_last();
        this.container.remove_back();
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
