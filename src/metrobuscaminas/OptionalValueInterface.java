/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package metrobuscaminas;

/**
 * Interfaz utilizada para EmptyOptionalValue y OptionalValue.
 * 
 * @version 26/02/2025
 * @author Gabriel
 */
public interface OptionalValueInterface<T> 
{
    T get();
    boolean has_value();
}
