package com.facebook.DAOConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Provides a static method to retrieve a database connection using the postgreSQL driver
 *
 * @version 1.0
 * @author vasanth
 */
public class JDBCConnection {

    private static JDBCConnection connection;
    private static final String SQL_URL = "jdbc:postgresql://localhost:5432/facebook";
    private static final String USER_NAME = "postgres";
    private static final String PASSWORD = "vasanth16";
    private static final Integer MAX_POOL_SIZE = 10;
    private static BlockingQueue<Connection> connectionPool;

    private JDBCConnection() {
        connectionPool = new ArrayBlockingQueue<>(MAX_POOL_SIZE);

        createPool();
    }

    public static JDBCConnection getInstance() {
        return null == connection ? connection = new JDBCConnection() : connection;
    }

    private static void createPool() {
        try {

            for (int i = 0; i < MAX_POOL_SIZE; i++) {
                final Connection connection = getConnection();

                if (null != connection) {
                    connectionPool.offer(connection);
                }
            }
        } catch (final Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Retrieves a database connection using the postgreSQL driver
     *
     * @return The database connection object.
     */
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(SQL_URL, USER_NAME, PASSWORD);
        } catch (final Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public Connection get() {
        try {
            return connectionPool.take();
        } catch (final Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public void releaseConnection(final Connection connection) {
        if (null != connection) {
            connectionPool.offer(connection);
        }
    }

    public void closeConnectionPool() {
        try {
            for (final Connection connection : connectionPool) {
                connection.close();
            }
        } catch (final Exception exception) {
            exception.printStackTrace();
        }
    }


}


