package vertx.model;

import java.util.List;

public class InsertObject {

  private int id;
  private String createdAt;
  private String name;
  private List<Address> addresses;
  private boolean oldTown;

  public InsertObject() {
    this.createdAt = IsoTimestamp.getIsoTimestamp();
    this.id = 300;
  }

  public InsertObject(String name, List<Address> addresses, boolean oldTown) {
    this.name = name;
    this.addresses = addresses;
    this.oldTown = oldTown;
    this.createdAt = IsoTimestamp.getIsoTimestamp();
    this.id = 300;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Address> getAddresses() {
    return addresses;
  }

  public void setAddresses(List<Address> addresses) {
    this.addresses = addresses;
  }

  public boolean isOldTown() {
    return oldTown;
  }

  public void setOldTown(boolean oldTown) {
    this.oldTown = oldTown;
  }
}
