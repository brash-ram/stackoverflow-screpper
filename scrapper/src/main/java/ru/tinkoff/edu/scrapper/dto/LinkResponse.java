package ru.tinkoff.edu.scrapper.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

import java.net.URI;
import java.util.Objects;

/**
 * LinkResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-03-27T01:09:28.504597700+03:00[Europe/Moscow]")
public class LinkResponse {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("url")
  private URI url;

  public LinkResponse id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  
  @Schema(name = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LinkResponse url(URI url) {
    this.url = url;
    return this;
  }

  /**
   * Get url
   * @return url
  */
  @Valid 
  @Schema(name = "url", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public URI getUrl() {
    return url;
  }

  public void setUrl(URI url) {
    this.url = url;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LinkResponse linkResponse = (LinkResponse) o;
    return Objects.equals(this.id, linkResponse.id) &&
        Objects.equals(this.url, linkResponse.url);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, url);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LinkResponse {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

