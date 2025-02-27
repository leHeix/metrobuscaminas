/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metrobuscaminas;

/**
 *
 * @author Gabriel
 */
public class EmptyOptionalValue<T> implements OptionalValueInterface<T>
{
    static final EmptyOptionalValue<?> EMPTY = new EmptyOptionalValue<>();
    
    private EmptyOptionalValue() {}
    
    @Override
    public T get() { return null; }
    
    @Override
    public boolean has_value() { return false; }
}
