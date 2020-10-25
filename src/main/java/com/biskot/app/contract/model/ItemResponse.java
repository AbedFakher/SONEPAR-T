package com.biskot.app.contract.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ItemResponse
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-10-25T01:48:08.507030+01:00[Africa/Tunis]")

public class ItemResponse   {
  @JsonProperty("product_id")
  private Long productId;

  @JsonProperty("product_label")
  private String productLabel;

  @JsonProperty("quantity")
  private Integer quantity;

  @JsonProperty("unit_price")
  private Double unitPrice;

  @JsonProperty("line_price")
  private Double linePrice;

  public ItemResponse productId(Long productId) {
    this.productId = productId;
    return this;
  }

  /**
   * Get productId
   * @return productId
  */
  @ApiModelProperty(value = "")


  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public ItemResponse productLabel(String productLabel) {
    this.productLabel = productLabel;
    return this;
  }

  /**
   * Get productLabel
   * @return productLabel
  */
  @ApiModelProperty(value = "")


  public String getProductLabel() {
    return productLabel;
  }

  public void setProductLabel(String productLabel) {
    this.productLabel = productLabel;
  }

  public ItemResponse quantity(Integer quantity) {
    this.quantity = quantity;
    return this;
  }

  /**
   * Get quantity
   * @return quantity
  */
  @ApiModelProperty(value = "")


  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public ItemResponse unitPrice(Double unitPrice) {
    this.unitPrice = unitPrice;
    return this;
  }

  /**
   * Get unitPrice
   * @return unitPrice
  */
  @ApiModelProperty(value = "")


  public Double getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(Double unitPrice) {
    this.unitPrice = unitPrice;
  }

  public ItemResponse linePrice(Double linePrice) {
    this.linePrice = linePrice;
    return this;
  }

  /**
   * Get linePrice
   * @return linePrice
  */
  @ApiModelProperty(value = "")


  public Double getLinePrice() {
    return linePrice;
  }

  public void setLinePrice(Double linePrice) {
    this.linePrice = linePrice;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ItemResponse itemResponse = (ItemResponse) o;
    return Objects.equals(this.productId, itemResponse.productId) &&
        Objects.equals(this.productLabel, itemResponse.productLabel) &&
        Objects.equals(this.quantity, itemResponse.quantity) &&
        Objects.equals(this.unitPrice, itemResponse.unitPrice) &&
        Objects.equals(this.linePrice, itemResponse.linePrice);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productId, productLabel, quantity, unitPrice, linePrice);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ItemResponse {\n");
    
    sb.append("    productId: ").append(toIndentedString(productId)).append("\n");
    sb.append("    productLabel: ").append(toIndentedString(productLabel)).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
    sb.append("    unitPrice: ").append(toIndentedString(unitPrice)).append("\n");
    sb.append("    linePrice: ").append(toIndentedString(linePrice)).append("\n");
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

