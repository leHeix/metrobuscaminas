/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metrobuscaminas;

/**
 *
 * @author Gabriel
 */
public class Map<Key, Value> 
{
    private List<Key> keys;
    private List<Value> values;
    
    public Map()
    {
        this.keys = new List<>();
        this.values = new List<>();
    }
    
    public void insert(Key key, Value value)
    {
        int idx = this.keys.has_value(key);
        
        if(idx != -1)
        {
            this.values.set(idx, value);
        }
        else
        {
            this.keys.insert_back(key);
            this.values.insert_back(value);
        }
    }
    
    public void set(Key key, Value value)
    {
        int idx = this.keys.has_value(key);
        
        if(idx != -1)
        {
            this.values.set(idx, value);
        }
    }
    
    public Optional<Value> get(Key key)
    {
        int idx = this.keys.has_value(key);
        if(idx == -1)
        {
            return new Optional(EmptyOptionalValue.EMPTY);
        }
        
        return new Optional(this.values.get(idx));
    }
}
