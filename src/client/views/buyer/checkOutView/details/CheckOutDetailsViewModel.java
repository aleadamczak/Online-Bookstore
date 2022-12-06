package client.views.buyer.checkOutView.details;

import client.core.ModelFactory;
import client.core.ViewHandler;
import client.model.BuyerModelManager;
import client.model.UserModelManager;
import javafx.beans.property.*;
import shared.BookForSale;

public class CheckOutDetailsViewModel {
    private StringProperty fullName;
    private StringProperty username;
    private UserModelManager model;
    private BuyerModelManager buyerModelManager;
    private BookForSale bookForSale;

    private StringProperty  title,  genre , coverType, isbn, soldBy , condition;
    private StringProperty price;
    private StringProperty yearOfPublication;
    private ObjectProperty Author;


    public CheckOutDetailsViewModel() {
        bookForSale = ViewHandler.getInstance().getMainPageController().getSelectedBook();
        this.title = new SimpleStringProperty();
        this.coverType = new SimpleStringProperty();
        this.Author = new SimpleObjectProperty();
        this.isbn = new SimpleStringProperty();
        this.genre = new SimpleStringProperty();
        this.yearOfPublication = new SimpleStringProperty();
        this.price = new SimpleStringProperty();
        this.soldBy = new SimpleStringProperty();
        this.condition = new SimpleStringProperty();
        loadInitialData();
        fullName = new SimpleStringProperty();
        username = new SimpleStringProperty();
        model = ModelFactory.getInstance().getUserModelManager();
        buyerModelManager = ModelFactory.getInstance().getBuyerModelManager();
        //buyerModelManager.addPropertyChangeListener("NewBookForSale", this::onNewBookForSale);
    }

    public void loadInitialData() {
        title.set(bookForSale.getBook().getTitle());
        isbn.set(bookForSale.getBook().getIsbn());
        coverType.set(bookForSale.getBook().getCoverType());
        Author.set(bookForSale.getBook().getAuthor().toString());
        genre.set(bookForSale.getBook().getGenre().toString());
        yearOfPublication.set(String.valueOf(bookForSale.getBook().getYearOfPublish()));
        price.set(String.valueOf(bookForSale.getPrice()));
        soldBy.set(bookForSale.getUser().getUsername());
        condition.set(bookForSale.getCondition());

    }

    public StringProperty getFullNameProperty() {
        return fullName;
    }

    public StringProperty getUsernameProperty() {
        return username;
    }

    public void updateLabels()
    {
        username.set(model.getUser().getUsername());
        fullName.set(model.getUser().getFullName());
    }


    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public String getGenre() {
        return genre.get();
    }

    public StringProperty genreProperty() {
        return genre;
    }

    public String getCoverType() {
        return coverType.get();
    }

    public StringProperty coverTypeProperty() {
        return coverType;
    }

    public String getAuthor() {
        return Author.toString();
    }

    public ObjectProperty authorProperty() {
        return Author;
    }

    public String getIsbn() {
        return isbn.get();
    }

    public StringProperty isbnProperty() {
        return isbn;
    }

    public String getYearOfPublication() {
        return yearOfPublication.get();
    }

    public StringProperty yearOfPublicationProperty() {
        return yearOfPublication;
    }

    public String getPrice() {
        return price.get();
    }

    public StringProperty priceProperty() {
        return price;
    }

    public String getSoldBy() {
        return soldBy.get();
    }

    public StringProperty soldByProperty() {
        return soldBy;
    }

    public String getCondition() {
        return condition.get();
    }

    public StringProperty conditionProperty() {
        return condition;
    }
}

