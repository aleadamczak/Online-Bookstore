package server.database.book;

import server.database.DatabaseConnection;
import server.database.user.UserDAOImpl;
import shared.Book;
import shared.BookForSale;
import shared.Seller;
import shared.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookForSaleDAOImpl implements BookForSaleDAO {

    private static BookForSaleDAOImpl instance;
    private Connection connection;

    private BookForSaleDAOImpl() {
        connection = DatabaseConnection.getInstance().getConnection();
    }
    public static synchronized BookForSaleDAOImpl getInstance() throws SQLException {
        if (instance == null) {
            instance = new BookForSaleDAOImpl();
        }
        return instance;
    }


    @Override
    public BookForSale create(String condition, double price, Book book, User user) throws SQLException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO bookforsale  (isbn,seller_id, price, condition) values (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(4,condition);
            preparedStatement.setDouble(3, price);
            preparedStatement.setString(1, book.getIsbn());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.executeUpdate();
            ResultSet keys = preparedStatement.getGeneratedKeys();

            if (keys.next()) {
                return new BookForSale(keys.getInt(1), condition, price, book, user);
            }
            else return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<BookForSale> getAllBooks() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from bookforsale");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<BookForSale> booksForSale = new ArrayList<>();
            while (resultSet.next()) {
                booksForSale.add(new BookForSale(resultSet.getInt("id"), resultSet.getString("condition"),
                        resultSet.getDouble("price"), BookDAOImpl.getInstance().readByISBN(resultSet.getString("isbn")),
                        UserDAOImpl.getInstance().getUserByUsername(resultSet.getString("seller_id"))));
            }
            return booksForSale;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
