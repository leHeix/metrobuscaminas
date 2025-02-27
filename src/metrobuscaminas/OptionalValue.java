/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metrobuscaminas;

/**
 *
 * @author Gabriel
 */
public class OptionalValue<T> implements OptionalValueInterface<T>
{
    private final T value;
    
    public OptionalValue(T value)
    {
        this.value = value;
    }
    
    @Override
    public T get() { return value; }
    
    @Override
    public boolean has_value() { return true; }
}
