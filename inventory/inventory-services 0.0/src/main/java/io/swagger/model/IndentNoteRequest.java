package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.IndentNote;
import io.swagger.model.RequestInfo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Contract class for web request. Array of Indent Note items are used in case of create or update
 */
@ApiModel(description = "Contract class for web request. Array of Indent Note items are used in case of create or update")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-10-28T13:21:55.964+05:30")

public class IndentNoteRequest   {
  @JsonProperty("requestInfo")
  private RequestInfo requestInfo = null;

  @JsonProperty("indentNotes")
  @Valid
  private List<IndentNote> indentNotes = null;

  public IndentNoteRequest requestInfo(RequestInfo requestInfo) {
    this.requestInfo = requestInfo;
    return this;
  }

   /**
   * Get requestInfo
   * @return requestInfo
  **/
  @ApiModelProperty(value = "")

  @Valid

  public RequestInfo getRequestInfo() {
    return requestInfo;
  }

  public void setRequestInfo(RequestInfo requestInfo) {
    this.requestInfo = requestInfo;
  }

  public IndentNoteRequest indentNotes(List<IndentNote> indentNotes) {
    this.indentNotes = indentNotes;
    return this;
  }

  public IndentNoteRequest addIndentNotesItem(IndentNote indentNotesItem) {
    if (this.indentNotes == null) {
      this.indentNotes = new ArrayList<IndentNote>();
    }
    this.indentNotes.add(indentNotesItem);
    return this;
  }

   /**
   * Used for search result and create only
   * @return indentNotes
  **/
  @ApiModelProperty(value = "Used for search result and create only")

  @Valid

  public List<IndentNote> getIndentNotes() {
    return indentNotes;
  }

  public void setIndentNotes(List<IndentNote> indentNotes) {
    this.indentNotes = indentNotes;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IndentNoteRequest indentNoteRequest = (IndentNoteRequest) o;
    return Objects.equals(this.requestInfo, indentNoteRequest.requestInfo) &&
        Objects.equals(this.indentNotes, indentNoteRequest.indentNotes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(requestInfo, indentNotes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IndentNoteRequest {\n");
    
    sb.append("    requestInfo: ").append(toIndentedString(requestInfo)).append("\n");
    sb.append("    indentNotes: ").append(toIndentedString(indentNotes)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

