package murach.business;
import java.io.Serializable;
public class User implements Serializable {
  private String email, firstName, lastName;
  public String getEmail(){return email;}
  public void setEmail(String email){this.email=email;}
  public String getFirstName(){return firstName;}
  public void setFirstName(String v){this.firstName=v;}
  public String getLastName(){return lastName;}
  public void setLastName(String v){this.lastName=v;}
}
