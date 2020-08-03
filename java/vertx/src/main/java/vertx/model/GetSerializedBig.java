package vertx.model;

import java.util.ArrayList;
import java.util.List;

public class GetSerializedBig {

  private class Publication {
    private final int year;
    private final boolean related;
    private final String description;

    public Publication(int year, boolean related, String description) {
      this.year = year;
      this.related = related;
      this.description = description;
    }

    public int getYear() {
      return year;
    }

    public boolean isRelated() {
      return related;
    }

    public String getDescription() {
      return description;
    }
  }

  private class DateLocation {
    private final int year;
    private final String month;
    private final int day;
    private final String city;
    private final String country;

    public DateLocation(int year, String month, int day, String city, String country) {
      this.year = year;
      this.month = month;
      this.day = day;
      this.city = city;
      this.country = country;
    }

    public int getYear() {
      return year;
    }

    public String getMonth() {
      return month;
    }

    public int getDay() {
      return day;
    }

    public String getCity() {
      return city;
    }

    public String getCountry() {
      return country;
    }
  }

  private class Classifier {
    private final String name;
    private final DateLocation born;
    private final DateLocation died;
    private final List<Publication> publications;

    public Classifier(String name, DateLocation born, DateLocation died, List<Publication> publications) {
      this.name = name;
      this.born = born;
      this.died = died;
      this.publications = publications;
    }

    public String getName() {
      return name;
    }

    public DateLocation getBorn() {
      return born;
    }

    public DateLocation getDied() {
      return died;
    }

    public List<Publication> getPublications() {
      return publications;
    }
  }

  private class ScientificClassification {
    private final String kingdom;
    private final String pyhlum;
    private final String class_;
    private final String order;
    private final String superfamily;
    private final Classifier classifier;

    public ScientificClassification(String kingdom, String pyhlum, String class_, String order, String superfamily, Classifier classifier) {
      this.kingdom = kingdom;
      this.pyhlum = pyhlum;
      this.class_ = class_;
      this.order = order;
      this.superfamily = superfamily;
      this.classifier = classifier;
    }

    public String getKingdom() {
      return kingdom;
    }

    public String getPyhlum() {
      return pyhlum;
    }

    public String getClass_() {
      return class_;
    }

    public String getOrder() {
      return order;
    }

    public String getSuperfamily() {
      return superfamily;
    }

    public Classifier getClassifier() {
      return classifier;
    }
  }

  private final String family;
  private final ScientificClassification scientificClassification;

  public GetSerializedBig(int numberPublications) {
    List<Publication> publications = new ArrayList<>(numberPublications);
    for (int i = 0; i < numberPublications; i++) {
      publications.add(new Publication(1821 + i, true, "Some discovery in " + (1821 + i)));
    }

    this.family = "Elephantidae";
    this.scientificClassification = new ScientificClassification(
      "Animalia",
      "Chordata",
      "Mammalia",
      "Proboscidea",
      "Elephantoidea",
      new Classifier("John Edward Gray",
        new DateLocation(1800,
          "February",
          12,
          "Walsall",
          "England"
        ),
        new DateLocation(1875,
          "March",
          7,
          "London",
          "England"
        ),
        publications
      )
    );
  }

  public String getFamily() {
    return family;
  }

  public ScientificClassification getScientificClassification() {
    return scientificClassification;
  }
}
