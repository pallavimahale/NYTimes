
package com.mindstix.nytimes.network.models.books.history;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "primary_isbn10",
    "primary_isbn13",
    "rank",
    "list_name",
    "display_name",
    "published_date",
    "bestsellers_date",
    "weeks_on_list",
    "ranks_last_week",
    "asterisk",
    "dagger"
})
public class RanksHistory {

    @JsonProperty("primary_isbn10")
    private String primaryIsbn10;
    @JsonProperty("primary_isbn13")
    private String primaryIsbn13;
    @JsonProperty("rank")
    private Integer rank;
    @JsonProperty("list_name")
    private String listName;
    @JsonProperty("display_name")
    private String displayName;
    @JsonProperty("published_date")
    private String publishedDate;
    @JsonProperty("bestsellers_date")
    private String bestsellersDate;
    @JsonProperty("weeks_on_list")
    private Integer weeksOnList;
    @JsonProperty("ranks_last_week")
    private Object ranksLastWeek;
    @JsonProperty("asterisk")
    private Integer asterisk;
    @JsonProperty("dagger")
    private Integer dagger;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("primary_isbn10")
    public String getPrimaryIsbn10() {
        return primaryIsbn10;
    }

    @JsonProperty("primary_isbn10")
    public void setPrimaryIsbn10(String primaryIsbn10) {
        this.primaryIsbn10 = primaryIsbn10;
    }

    @JsonProperty("primary_isbn13")
    public String getPrimaryIsbn13() {
        return primaryIsbn13;
    }

    @JsonProperty("primary_isbn13")
    public void setPrimaryIsbn13(String primaryIsbn13) {
        this.primaryIsbn13 = primaryIsbn13;
    }

    @JsonProperty("rank")
    public Integer getRank() {
        return rank;
    }

    @JsonProperty("rank")
    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @JsonProperty("list_name")
    public String getListName() {
        return listName;
    }

    @JsonProperty("list_name")
    public void setListName(String listName) {
        this.listName = listName;
    }

    @JsonProperty("display_name")
    public String getDisplayName() {
        return displayName;
    }

    @JsonProperty("display_name")
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @JsonProperty("published_date")
    public String getPublishedDate() {
        return publishedDate;
    }

    @JsonProperty("published_date")
    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    @JsonProperty("bestsellers_date")
    public String getBestsellersDate() {
        return bestsellersDate;
    }

    @JsonProperty("bestsellers_date")
    public void setBestsellersDate(String bestsellersDate) {
        this.bestsellersDate = bestsellersDate;
    }

    @JsonProperty("weeks_on_list")
    public Integer getWeeksOnList() {
        return weeksOnList;
    }

    @JsonProperty("weeks_on_list")
    public void setWeeksOnList(Integer weeksOnList) {
        this.weeksOnList = weeksOnList;
    }

    @JsonProperty("ranks_last_week")
    public Object getRanksLastWeek() {
        return ranksLastWeek;
    }

    @JsonProperty("ranks_last_week")
    public void setRanksLastWeek(Object ranksLastWeek) {
        this.ranksLastWeek = ranksLastWeek;
    }

    @JsonProperty("asterisk")
    public Integer getAsterisk() {
        return asterisk;
    }

    @JsonProperty("asterisk")
    public void setAsterisk(Integer asterisk) {
        this.asterisk = asterisk;
    }

    @JsonProperty("dagger")
    public Integer getDagger() {
        return dagger;
    }

    @JsonProperty("dagger")
    public void setDagger(Integer dagger) {
        this.dagger = dagger;
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
