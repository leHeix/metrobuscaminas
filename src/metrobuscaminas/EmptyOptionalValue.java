/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metrobuscaminas;

/**
 * Clase que corresponde a un valor vacío de Optional.
 * 
 * @version 26/02/2025
 * @author Gabriel
 */
public class EmptyOptionalValue<T> implements OptionalValueInterface<T>
{
    /**
     * Variable que corresponde a un EmptyOptionalValue
     */
    static final EmptyOptionalValue<?> EMPTY = new EmptyOptionalValue<>();
    
    private EmptyOptionalValue() {}
    
    /**
     * Obtiene un valor nulo del Optional.
     * @return null
     */
    @Override
    public T get() { return null; }
    
    /**
     * Función para comprobar si el Optional contiene un valor.
     * @return false para EmptyOptionalValue.
     */
    @Override
    public boolean has_value() { return false; }
}
