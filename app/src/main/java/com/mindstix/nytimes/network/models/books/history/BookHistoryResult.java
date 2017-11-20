
package com.mindstix.nytimes.network.models.books.history;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.mindstix.nytimes.network.models.books.Isbn;
import com.mindstix.nytimes.network.models.books.Review;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "title",
    "description",
    "contributor",
    "author",
    "contributor_note",
    "price",
    "age_group",
    "publisher",
    "isbns",
    "ranks_history",
    "reviews"
})
public class BookHistoryResult {

    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("contributor")
    private String contributor;
    @JsonProperty("author")
    private String author;
    @JsonProperty("contributor_note")
    private String contributorNote;
    @JsonProperty("price")
    private Integer price;
    @JsonProperty("age_group")
    private String ageGroup;
    @JsonProperty("publisher")
    private String publisher;
    @JsonProperty("isbns")
    private List<Isbn> isbns = null;
    @JsonProperty("ranks_history")
    private List<RanksHistory> ranksHistory = null;
    @JsonProperty("reviews")
    private List<Review> reviews = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("contributor")
    public String getContributor() {
        return contributor;
    }

    @JsonProperty("contributor")
    public void setContributor(String contributor) {
        this.contributor = contributor;
    }

    @JsonProperty("author")
    public String getAuthor() {
        return author;
    }

    @JsonProperty("author")
    public void setAuthor(String author) {
        this.author = author;
    }

    @JsonProperty("contributor_note")
    public String getContributorNote() {
        return contributorNote;
    }

    @JsonProperty("contributor_note")
    public void setContributorNote(String contributorNote) {
        this.contributorNote = contributorNote;
    }

    @JsonProperty("price")
    public Integer getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(Integer price) {
        this.price = price;
    }

    @JsonProperty("age_group")
    public String getAgeGroup() {
        return ageGroup;
    }

    @JsonProperty("age_group")
    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    @JsonProperty("publisher")
    public String getPublisher() {
        return publisher;
    }

    @JsonProperty("publisher")
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @JsonProperty("isbns")
    public List<Isbn> getIsbns() {
        return isbns;
    }

    @JsonProperty("isbns")
    public void setIsbns(List<Isbn> isbns) {
        this.isbns = isbns;
    }

    @JsonProperty("ranks_history")
    public List<RanksHistory> getRanksHistory() {
        return ranksHistory;
    }

    @JsonProperty("ranks_history")
    public void setRanksHistory(List<RanksHistory> ranksHistory) {
        this.ranksHistory = ranksHistory;
    }

    @JsonProperty("reviews")
    public List<Review> getReviews() {
        return reviews;
    }

    @JsonProperty("reviews")
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
