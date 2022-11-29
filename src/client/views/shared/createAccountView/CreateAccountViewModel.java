package client.views.shared.createAccountView;

import client.core.ModelFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CreateAccountViewModel
{
  private StringProperty fullName, dateOfBirth, address, phoneNumber, email, username, password;

  public CreateAccountViewModel()
  {
    this.fullName = new SimpleStringProperty();
    this.dateOfBirth = new SimpleStringProperty();
    this.address = new SimpleStringProperty();
    this.phoneNumber = new SimpleStringProperty();
    this.email = new SimpleStringProperty();
    this.username = new SimpleStringProperty();
    this.password = new SimpleStringProperty();
  }

  public StringProperty fullNameProperty(){return fullName;}

  public StringProperty dateOfBirthProperty(){return dateOfBirth;}

  public StringProperty addressProperty(){return address;}

  public StringProperty phoneNumberProperty(){return phoneNumber;}

  public StringProperty emailProperty(){return email;}

  public StringProperty usernameProperty(){return username;}

  public StringProperty passwordProperty(){return password;}

  public void registerBuyer() {
    ModelFactory.getInstance().getUserModelManager().registerBuyer(fullName.toString(), address.toString(),phoneNumber.toString(),email.toString(),username.toString(),password.toString());
  }

  public void registerSeller() {
    ModelFactory.getInstance().getUserModelManager().registerSeller(fullName.toString(), address.toString(),phoneNumber.toString(),email.toString(),username.toString(),password.toString());
  }

}
