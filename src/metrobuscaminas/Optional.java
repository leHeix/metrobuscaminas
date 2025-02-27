/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metrobuscaminas;

/**
 *
 * @author Gabriel
 */
public class Optional<T> {
    OptionalValueInterface<T> value;
    
    public Optional(T value)
    {
        this.value = new OptionalValue(value);
    }
    
    public Optional(EmptyOptionalValue value)
    {
        this.value = value;
    }
    
    public T get()
    {
        return this.value.get();
    }
    
    public boolean has_value()
    {
        return this.value.has_value();
    }
}
