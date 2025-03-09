/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metrobuscaminas;

/**
 * Clase que corresponde a un valor válido de Optional.
 * 
 * @version 26/02/2025
 * @author Gabriel
 */
public class OptionalValue<T> implements OptionalValueInterface<T>
{
    private final T value;
    
    /**
     * Construye un valor para Optional válido.
     * @param value El valor a asignar al Optional.
     */
    public OptionalValue(T value)
    {
        this.value = value;
    }
    
    /**
     * Obtiene el valor almacenado por el Optional.
     * @return Valor de la variable.
     */
    @Override
    public T get() { return value; }
    
    /**
     * Función para comprobar si el Optional contiene un valor.
     * @return true para OptionalValue.
     */
    @Override
    public boolean has_value() { return true; }
}
