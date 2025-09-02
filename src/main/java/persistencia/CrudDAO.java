/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.sql.SQLException;
import java.util.List;

/**
 *Clase abstracta que define operaciones CRUD genéricas.
 *Esto asegura que todas las tablas tendran el mismo patrón CRUD (create, read, update, Delete)
 * @param <T> Tipo de entidad que será manejado por el DAO
 * @author andy
 */
public abstract class CrudDAO<T> {
    /**
     * Sirve para realizar inserciones
     * @param entity a modelar
     * @return informacion de una tabla
     * @throws SQLException  si no se encuentra
     */
    public abstract T insert(T entity) throws SQLException;
    
    /**
     * Busqueda de una entidad en base de datos
     * @param id de la tabla
     * @return una valor en tabla
     * @throws SQLException si no existe en la tabla
     */
    public abstract T findById(String id) throws SQLException;
    
    /**
     * sirve para encontrar una lista en las tablas
     * @return una lista con las entidades buscadas
     * @throws SQLException si la tabla esta vacia
     */
    public abstract List<T> findAll() throws SQLException;
    
    /**
     * Edicion de un valor en la tabla
     * @param entity un objeto a bucar
     * @throws SQLException si no existe
     */
    public abstract void update(T entity) throws SQLException;
    
    /**
     * Eliminar en la tabla
     * @param id de una entidad
     * @throws SQLException si no existe
     */
    public abstract void delete(Integer id) throws SQLException;
}
