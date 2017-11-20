
package com.mindstix.nytimes.network.models.search;

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
    "xlargewidth",
    "xlarge",
    "xlargeheight",
    "wide",
    "widewidth",
    "wideheight",
    "thumbnailheight",
    "thumbnail",
    "thumbnailwidth"
})
public class Legacy {

    @JsonProperty("xlargewidth")
    private Integer xlargewidth;
    @JsonProperty("xlarge")
    private String xlarge;
    @JsonProperty("xlargeheight")
    private Integer xlargeheight;
    @JsonProperty("wide")
    private String wide;
    @JsonProperty("widewidth")
    private Integer widewidth;
    @JsonProperty("wideheight")
    private Integer wideheight;
    @JsonProperty("thumbnailheight")
    private Integer thumbnailheight;
    @JsonProperty("thumbnail")
    private String thumbnail;
    @JsonProperty("thumbnailwidth")
    private Integer thumbnailwidth;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("xlargewidth")
    public Integer getXlargewidth() {
        return xlargewidth;
    }

    @JsonProperty("xlargewidth")
    public void setXlargewidth(Integer xlargewidth) {
        this.xlargewidth = xlargewidth;
    }

    @JsonProperty("xlarge")
    public String getXlarge() {
        return xlarge;
    }

    @JsonProperty("xlarge")
    public void setXlarge(String xlarge) {
        this.xlarge = xlarge;
    }

    @JsonProperty("xlargeheight")
    public Integer getXlargeheight() {
        return xlargeheight;
    }

    @JsonProperty("xlargeheight")
    public void setXlargeheight(Integer xlargeheight) {
        this.xlargeheight = xlargeheight;
    }

    @JsonProperty("wide")
    public String getWide() {
        return wide;
    }

    @JsonProperty("wide")
    public void setWide(String wide) {
        this.wide = wide;
    }

    @JsonProperty("widewidth")
    public Integer getWidewidth() {
        return widewidth;
    }

    @JsonProperty("widewidth")
    public void setWidewidth(Integer widewidth) {
        this.widewidth = widewidth;
    }

    @JsonProperty("wideheight")
    public Integer getWideheight() {
        return wideheight;
    }

    @JsonProperty("wideheight")
    public void setWideheight(Integer wideheight) {
        this.wideheight = wideheight;
    }

    @JsonProperty("thumbnailheight")
    public Integer getThumbnailheight() {
        return thumbnailheight;
    }

    @JsonProperty("thumbnailheight")
    public void setThumbnailheight(Integer thumbnailheight) {
        this.thumbnailheight = thumbnailheight;
    }

    @JsonProperty("thumbnail")
    public String getThumbnail() {
        return thumbnail;
    }

    @JsonProperty("thumbnail")
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @JsonProperty("thumbnailwidth")
    public Integer getThumbnailwidth() {
        return thumbnailwidth;
    }

    @JsonProperty("thumbnailwidth")
    public void setThumbnailwidth(Integer thumbnailwidth) {
        this.thumbnailwidth = thumbnailwidth;
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
