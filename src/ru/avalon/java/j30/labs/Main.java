package ru.avalon.java.j30.labs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.io.*;

/**
 * Лабораторная работа №3
 * <p>
 * Курс: "DEV-OCPJP. Подготовка к сдаче сертификационных экзаменов серии Oracle Certified Professional Java Programmer"
 * <p>
 * Тема: "JDBC - Java Database Connectivity" 
 *
 * @author Daniel Alpatov <danial.alpatov@gmail.com>
 */
public class Main {

    public static HashMap<String, String> sqlMap;
    /**
     * Точка входа в приложение
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        if (loadDriver()){
            
        
        /*
         * TODO #01 Подключите к проекту все библиотеки, необходимые для соединения с СУБД.
         */
        try (Connection connection = getConnection()) {
            if (connection != null) {
                System.out.println("Работа с базой");
                
                sqlMap = getQueries("sql/queries.sql");
                printAllQueries(sqlMap);
                
            Product product = new Product(100, "Mixer", 2005.00);
            product.save(connection);
            printAllCodes(connection);

            product.setPrice(1500.00);
            product.save(connection);
            printAllCodes(connection);
            }
        }
        }
        /*
         * TODO #14 Средствами отладчика проверьте корректность работы программы
         */
    }
    /**
     * Выводит в кодсоль все коды товаров
     * 
     * @param connection действительное соединение с базой данных
     * @throws SQLException 
     */    
    private static void printAllCodes(Connection connection) throws SQLException {
        Collection<Product> codes = Product.all(connection);
        for (Product code : codes) {
            System.out.println(code);
        }
    }
    /**
     * Возвращает URL, описывающий месторасположение базы данных
     * 
     * @return URL в виде объекта класса {@link String}
     */
    private static String getUrl() {
        /*
         * TODO #02 Реализуйте метод getUrl
         */
        throw new UnsupportedOperationException("Not implemented yet!");
    }
    /**
     * Возвращает параметры соединения
     * 
     * @return Объект класса {@link Properties}, содержащий параметры user и 
     * password
     */
    private static Properties getProperties() {
        /*
         * TODO #03 Реализуйте метод getProperties
         */
        throw new UnsupportedOperationException("Not implemented yet!");
    }
    /**
     * Возвращает соединение с базой данных Sample
     * 
     * @return объект типа {@link Connection}
     * @throws SQLException 
     */
    private static Connection getConnection() {
        /*
         * TODO #04 Реализуйте метод getConnection
         */
        String url = "jdbc:hsqldb:hsql://localhost:9002/example";
        try (Connection conn = DriverManager.getConnection(url, "example", "")){
            
            System.out.println("соединение получено");
            return conn;
        } catch (SQLException ex) {
            System.out.println("Ошибка соединения");
        }
        return null;
    }

    private static boolean loadDriver() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            System.out.println("Драйвер загружен");
            return true;
        } catch (ClassNotFoundException ex){
            System.out.println("не найден драйвер" + ex.getMessage());
            return false;
        }
    }

    private static HashMap<String, String> getQueries(String path) {
        LinkedList<String> lines = new LinkedList<>();
        try(InputStream is = ClassLoader.getSystemResourceAsStream(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(is)) ){
            String s;
            while ( (s = br.readLine()) != null) {
            lines.add(s);
                //System.out.println("строка: " + s);
        }
        } catch (IOException ex){
            System.out.println("Ошибка чтения файла " + path);
                }
        HashMap<String, String> hss = new HashMap<>();
        boolean entryStarted = false;
        String key = null;
        StringBuilder sb = new StringBuilder();
        for(String line : lines) {
            if(line.startsWith("-->")){  //если начинается с символов то это комментарий
                entryStarted = true;
                key = line.substring(3).trim();//начиная с 3 символа забираем в ключ
            } else {
                if (entryStarted) sb.append(line);
                if (line.trim().endsWith(";")){
                    entryStarted = false;
                    hss.put(key, sb.toString());
                    sb.delete(0, sb.length()); // стереть все символы
                }
            }
        }
        return hss;
    }

    private static void printAllQueries(HashMap<String, String> sqlMap) {
        for(Map.Entry<String, String> entry : sqlMap.entrySet() ){
            System.out.println("имя: " + entry.getKey() + "\nзапрос: " + entry.getValue() );
        }
    }
}
