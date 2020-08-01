package bttger.webframeworkbenchmarks.spring.model;

public class GetQueryResult {
    private final int id;
    private final String foundAt;

    public GetQueryResult(int id) {
        this.id = id;
        this.foundAt = IsoTimestamp.getIsoTimeString();
    }

    public int getId() {
        return id;
    }

    public String getFoundAt() {
        return foundAt;
    }
}
