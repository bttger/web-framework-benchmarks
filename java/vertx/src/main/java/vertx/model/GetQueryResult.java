package vertx.model;

public class GetQueryResult {
  private final int id;
  private final String foundAt;

  public GetQueryResult(int id) {
    this.id = id;
    this.foundAt = IsoTimestamp.getIsoTimestamp();
  }

  public int getId() {
    return id;
  }

  public String getFoundAt() {
    return foundAt;
  }
}
