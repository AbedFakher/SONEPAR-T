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
 * AddItemRequest
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-10-25T01:48:08.507030+01:00[Africa/Tunis]")

public class AddItemRequest   {
  @JsonProperty("product_id")
  private Long productId;

  @JsonProperty("quantity")
  private Integer quantity;

  public AddItemRequest productId(Long productId) {
    this.productId = productId;
    return this;
  }

  /**
   * id of product to add
   * @return productId
  */
  @ApiModelProperty(example = "3", value = "id of product to add")


  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public AddItemRequest quantity(Integer quantity) {
    this.quantity = quantity;
    return this;
  }

  /**
   * quantity to add, or updated quantity in case of existing product in cart
   * @return quantity
  */
  @ApiModelProperty(example = "2", value = "quantity to add, or updated quantity in case of existing product in cart")


  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AddItemRequest addItemRequest = (AddItemRequest) o;
    return Objects.equals(this.productId, addItemRequest.productId) &&
        Objects.equals(this.quantity, addItemRequest.quantity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productId, quantity);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AddItemRequest {\n");
    
    sb.append("    productId: ").append(toIndentedString(productId)).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
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

