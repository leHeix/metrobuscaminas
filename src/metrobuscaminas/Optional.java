/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metrobuscaminas;

/**
 * Contenedor que almacena un dato o ninguno, comprobando apropiadamente
 * si la variable posee un valor válido o no
 * 
 * @version 26/02/2025
 * @author Gabriel
 */
public class Optional<T> {
    OptionalValueInterface<T> value;
    
    /**
     * Crea un nuevo Optional con un valor válido.
     * @param value El valor a asignar al Optional.
     */
    public Optional(T value)
    {
        this.value = new OptionalValue(value);
    }
    
    /**
     * Crea un nuevo Optional que no almacena ningún dato.
     * @param value EmptyOptionalValue.EMPTY
     */
    public Optional(EmptyOptionalValue value)
    {
        this.value = value;
    }
    
    /**
     * Obtiene el valor almacenado por el Optional.
     * @return Valor de la variable.
     */
    public T get()
    {
        return this.value.get();
    }
    
    /**
     * Comprueba si el Optional almacena un valor válido.
     * @return true si el Optional contiene un valor, false para Optional construido con EmptyOptionalValue.
     */
    public boolean has_value()
    {
        return this.value.has_value();
    }
}
