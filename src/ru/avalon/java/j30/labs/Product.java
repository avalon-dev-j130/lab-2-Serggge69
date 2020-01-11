package ru.avalon.java.j30.labs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;


public class Product {

    private int id; //Код товара
    private String name; //Кода скидки
    private double price; // Описание

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    /**
     * Инициализирует объект значениями из переданного {@link ResultSet}
     * 
     * @param set {@link ResultSet}, полученный в результате запроса, 
     * содержащего все поля таблицы PRODUCT_CODE базы данных Sample.
     */
    private Product(ResultSet set) {
        /*
         * TODO #05 реализуйте конструктор класса ProductCode
         */
        throw new UnsupportedOperationException("Not implemented yet!");        
    }
  
    public PreparedStatement getSelectQuery(Connection connection) throws SQLException {
        
        String sql = Main.sqlMap.get("findProductById");
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1, this.id);
        return st;
        
    }
    /**
     * Возвращает запрос на добавление записи в таблицу PRODUCT_CODE 
     * базы данных Sample
     * 
     * @param connection действительное соединение с базой данных
     * @return Запрос в виде объекта класса {@link PreparedStatement}
     */
    public PreparedStatement getInsertQuery(Connection connection) throws SQLException {

        String sql = Main.sqlMap.get("insertNewProduct");
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, this.name);
        st.setDouble(2, this.price);
        st.setInt(3, this.id);
        return st;
        
    }
    /**
     * Возвращает запрос на обновление значений записи в таблице PRODUCT_CODE 
     * базы данных Sample
     * 
     * @param connection действительное соединение с базой данных
     * @return Запрос в виде объекта класса {@link PreparedStatement}
     */
    public PreparedStatement getUpdateQuery(Connection connection) throws SQLException {

        String sql = Main.sqlMap.get("updateProductWithId");
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, this.name);
        st.setDouble(2, this.price);
        st.setInt(3, this.id);
        return st;
        
        
    }
    /**
     * Преобразует {@link ResultSet} в коллекцию объектов типа {@link Product}
     * 
     * @param set {@link ResultSet}, полученный в результате запроса, содержащего 
     * все поля таблицы PRODUCT_CODE базы данных Sample
     * @return Коллекция объектов типа {@link Product}
     * @throws SQLException 
     */
    public static Collection<Product> convert(ResultSet set) throws SQLException {
        /*
         * TODO #12 Реализуйте метод convert
         */
        throw new UnsupportedOperationException("Not implemented yet!");
    }
    /**
     * Сохраняет текущий объект в базе данных. 
     * <p>
     * Если запись ещё не существует, то выполняется запрос типа INSERT.
     * <p>
     * Если запись уже существует в базе данных, то выполняется запрос типа UPDATE.
     * 
     * @param connection действительное соединение с базой данных
     */
    public void save(Connection connection) throws SQLException {
        System.out.println("Соединение в save: " + connection);
            //проверить наличие в базе ID
           PreparedStatement st = getSelectQuery(connection);
           ResultSet rs = st.executeQuery();
           st.close();
           if(rs.first()) {
               //есть такой продукт - делаем UPDATE
               st = getUpdateQuery(connection);
               st.executeUpdate();
           } else {
               //нет такого в базе - делаем INSERT
               st = getUpdateQuery(connection);
           }
    }
    /**
     * Возвращает все записи таблицы PRODUCT_CODE в виде коллекции объектов
     * типа {@link Product}
     * 
     * @param connection действительное соединение с базой данных
     * @return коллекция объектов типа {@link Product}
     * @throws SQLException 
     */
    public static Collection<Product> all(Connection connection) throws SQLException {
//        try (PreparedStatement statement = getSelectQuery(connection)) {
//            try (ResultSet result = statement.executeQuery()) {
//                return convert(result);
//            }
//        }
return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
}
