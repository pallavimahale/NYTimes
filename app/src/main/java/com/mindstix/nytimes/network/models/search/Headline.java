
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
    "main",
    "kicker",
    "print_headline"
})
public class Headline {

    @JsonProperty("main")
    private String main;
    @JsonProperty("kicker")
    private String kicker;
    @JsonProperty("print_headline")
    private String printHeadline;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("main")
    public String getMain() {
        return main;
    }

    @JsonProperty("main")
    public void setMain(String main) {
        this.main = main;
    }

    @JsonProperty("kicker")
    public String getKicker() {
        return kicker;
    }

    @JsonProperty("kicker")
    public void setKicker(String kicker) {
        this.kicker = kicker;
    }

    @JsonProperty("print_headline")
    public String getPrintHeadline() {
        return printHeadline;
    }

    @JsonProperty("print_headline")
    public void setPrintHeadline(String printHeadline) {
        this.printHeadline = printHeadline;
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
