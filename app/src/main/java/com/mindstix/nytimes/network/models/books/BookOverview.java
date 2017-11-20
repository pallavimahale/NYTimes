
package com.mindstix.nytimes.network.models.books;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "age_group",
    "amazon_product_url",
    "article_chapter_link",
    "author",
    "book_image",
    "book_image_width",
    "book_image_height",
    "book_review_link",
    "contributor",
    "contributor_note",
    "created_date",
    "description",
    "first_chapter_link",
    "price",
    "primary_isbn10",
    "primary_isbn13",
    "publisher",
    "rank",
    "rank_last_week",
    "sunday_review_link",
    "title",
    "updated_date",
    "weeks_on_list",
    "buy_links"
})
public class BookOverview {

    @JsonProperty("age_group")
    private String ageGroup;
    @JsonProperty("amazon_product_url")
    private String amazonProductUrl;
    @JsonProperty("article_chapter_link")
    private String articleChapterLink;
    @JsonProperty("author")
    private String author;
    @JsonProperty("book_image")
    private String bookImage;
    @JsonProperty("book_image_width")
    private Integer bookImageWidth;
    @JsonProperty("book_image_height")
    private Integer bookImageHeight;
    @JsonProperty("book_review_link")
    private String bookReviewLink;
    @JsonProperty("contributor")
    private String contributor;
    @JsonProperty("contributor_note")
    private String contributorNote;
    @JsonProperty("created_date")
    private String createdDate;
    @JsonProperty("description")
    private String description;
    @JsonProperty("first_chapter_link")
    private String firstChapterLink;
    @JsonProperty("price")
    private Integer price;
    @JsonProperty("primary_isbn10")
    private String primaryIsbn10;
    @JsonProperty("primary_isbn13")
    private String primaryIsbn13;
    @JsonProperty("publisher")
    private String publisher;
    @JsonProperty("rank")
    private Integer rank;
    @JsonProperty("rank_last_week")
    private Integer rankLastWeek;
    @JsonProperty("sunday_review_link")
    private String sundayReviewLink;
    @JsonProperty("title")
    private String title;
    @JsonProperty("updated_date")
    private String updatedDate;
    @JsonProperty("weeks_on_list")
    private Integer weeksOnList;
    @JsonProperty("buy_links")
    private List<BuyLink> buyLinks = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("age_group")
    public String getAgeGroup() {
        return ageGroup;
    }

    @JsonProperty("age_group")
    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    @JsonProperty("amazon_product_url")
    public String getAmazonProductUrl() {
        return amazonProductUrl;
    }

    @JsonProperty("amazon_product_url")
    public void setAmazonProductUrl(String amazonProductUrl) {
        this.amazonProductUrl = amazonProductUrl;
    }

    @JsonProperty("article_chapter_link")
    public String getArticleChapterLink() {
        return articleChapterLink;
    }

    @JsonProperty("article_chapter_link")
    public void setArticleChapterLink(String articleChapterLink) {
        this.articleChapterLink = articleChapterLink;
    }

    @JsonProperty("author")
    public String getAuthor() {
        return author;
    }

    @JsonProperty("author")
    public void setAuthor(String author) {
        this.author = author;
    }

    @JsonProperty("book_image")
    public String getBookImage() {
        return bookImage;
    }

    @JsonProperty("book_image")
    public void setBookImage(String bookImage) {
        this.bookImage = bookImage;
    }

    @JsonProperty("book_image_width")
    public Integer getBookImageWidth() {
        return bookImageWidth;
    }

    @JsonProperty("book_image_width")
    public void setBookImageWidth(Integer bookImageWidth) {
        this.bookImageWidth = bookImageWidth;
    }

    @JsonProperty("book_image_height")
    public Integer getBookImageHeight() {
        return bookImageHeight;
    }

    @JsonProperty("book_image_height")
    public void setBookImageHeight(Integer bookImageHeight) {
        this.bookImageHeight = bookImageHeight;
    }

    @JsonProperty("book_review_link")
    public String getBookReviewLink() {
        return bookReviewLink;
    }

    @JsonProperty("book_review_link")
    public void setBookReviewLink(String bookReviewLink) {
        this.bookReviewLink = bookReviewLink;
    }

    @JsonProperty("contributor")
    public String getContributor() {
        return contributor;
    }

    @JsonProperty("contributor")
    public void setContributor(String contributor) {
        this.contributor = contributor;
    }

    @JsonProperty("contributor_note")
    public String getContributorNote() {
        return contributorNote;
    }

    @JsonProperty("contributor_note")
    public void setContributorNote(String contributorNote) {
        this.contributorNote = contributorNote;
    }

    @JsonProperty("created_date")
    public String getCreatedDate() {
        return createdDate;
    }

    @JsonProperty("created_date")
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("first_chapter_link")
    public String getFirstChapterLink() {
        return firstChapterLink;
    }

    @JsonProperty("first_chapter_link")
    public void setFirstChapterLink(String firstChapterLink) {
        this.firstChapterLink = firstChapterLink;
    }

    @JsonProperty("price")
    public Integer getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(Integer price) {
        this.price = price;
    }

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

    @JsonProperty("publisher")
    public String getPublisher() {
        return publisher;
    }

    @JsonProperty("publisher")
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @JsonProperty("rank")
    public Integer getRank() {
        return rank;
    }

    @JsonProperty("rank")
    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @JsonProperty("rank_last_week")
    public Integer getRankLastWeek() {
        return rankLastWeek;
    }

    @JsonProperty("rank_last_week")
    public void setRankLastWeek(Integer rankLastWeek) {
        this.rankLastWeek = rankLastWeek;
    }

    @JsonProperty("sunday_review_link")
    public String getSundayReviewLink() {
        return sundayReviewLink;
    }

    @JsonProperty("sunday_review_link")
    public void setSundayReviewLink(String sundayReviewLink) {
        this.sundayReviewLink = sundayReviewLink;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("updated_date")
    public String getUpdatedDate() {
        return updatedDate;
    }

    @JsonProperty("updated_date")
    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    @JsonProperty("weeks_on_list")
    public Integer getWeeksOnList() {
        return weeksOnList;
    }

    @JsonProperty("weeks_on_list")
    public void setWeeksOnList(Integer weeksOnList) {
        this.weeksOnList = weeksOnList;
    }

    @JsonProperty("buy_links")
    public List<BuyLink> getBuyLinks() {
        return buyLinks;
    }

    @JsonProperty("buy_links")
    public void setBuyLinks(List<BuyLink> buyLinks) {
        this.buyLinks = buyLinks;
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
