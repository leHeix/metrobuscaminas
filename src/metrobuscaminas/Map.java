/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metrobuscaminas;

/**
 * Estructura de datos correspondiente a un mapa. Almacena valores identificados por llaves.
 * 
 * @version 07/03/2025
 * @author Gabriel
 */
public class Map<Key, Value> 
{
    private List<Key> keys;
    private List<Value> values;
    
    /**
     * Inicializa los contenedores necesarios para los valores
     */
    public Map()
    {
        this.keys = new List<>();
        this.values = new List<>();
    }
    
    /**
     * Inserta un valor al mapa.
     * @param key La llave por la cual será identificada el valor.
     * @param value El valor a añadir.
     */
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
    
    /**
     * Asigna un valor a una llave ya existente en el mapa.
     * @param key La llave del valor.
     * @param value El valor a asignar.
     */
    public void set(Key key, Value value)
    {
        int idx = this.keys.has_value(key);
        
        if(idx != -1)
        {
            this.values.set(idx, value);
        }
    }
    
    /**
     * Obtiene un valor en el mapa con su llave.
     * @param key Llave del valor a obtener.
     * @return Optional que contiene el valor por la llave, o vacío si no pudo encontrar el valor.
     */
    public Optional<Value> get(Key key)
    {
        int idx = this.keys.has_value(key);
        if(idx == -1)
        {
            return new Optional(EmptyOptionalValue.EMPTY);
        }
        
        return this.values.get(idx);
    }
}
