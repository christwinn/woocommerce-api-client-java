/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

/*
 * WooCommerce REST API
 * The REST API is a powerful part of WooCommerce which lets you read and write various parts of WooCommerce data such as orders, products, coupons, customers, and shipping zones.
 *
 * The version of the OpenAPI document: v3
 *
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.report;

import java.util.Objects;

//import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import uk.co.twinn.api.woocommerce.core.JacksonObjectMapper;
import uk.co.twinn.api.woocommerce.core.deserialisers.JsonMappedLinks;
import uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.global.Link;

/**
 * ReportSalesSummary
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.13.0")
public class ReportSalesSummary {
  public static final String SERIALIZED_NAME_TOTAL_SALES = "total_sales";
  //@SerializedName(SERIALIZED_NAME_TOTAL_SALES)
  @javax.annotation.Nullable
  private String totalSales;

  public static final String SERIALIZED_NAME_NET_SALES = "net_sales";
  //@SerializedName(SERIALIZED_NAME_NET_SALES)
  @javax.annotation.Nullable
  private String netSales;

  public static final String SERIALIZED_NAME_AVERAGE_SALES = "average_sales";
  //@SerializedName(SERIALIZED_NAME_AVERAGE_SALES)
  @javax.annotation.Nullable
  private String averageSales;

  public static final String SERIALIZED_NAME_TOTAL_ORDERS = "total_orders";
  //@SerializedName(SERIALIZED_NAME_TOTAL_ORDERS)
  @javax.annotation.Nullable
  private Integer totalOrders;

  public static final String SERIALIZED_NAME_TOTAL_ITEMS = "total_items";
  //@SerializedName(SERIALIZED_NAME_TOTAL_ITEMS)
  @javax.annotation.Nullable
  private Integer totalItems;

  public static final String SERIALIZED_NAME_TOTAL_TAX = "total_tax";
  //@SerializedName(SERIALIZED_NAME_TOTAL_TAX)
  @javax.annotation.Nullable
  private String totalTax;

  public static final String SERIALIZED_NAME_TOTAL_SHIPPING = "total_shipping";
  //@SerializedName(SERIALIZED_NAME_TOTAL_SHIPPING)
  @javax.annotation.Nullable
  private String totalShipping;

  public static final String SERIALIZED_NAME_TOTAL_REFUNDS = "total_refunds";
  //@SerializedName(SERIALIZED_NAME_TOTAL_REFUNDS)
  @javax.annotation.Nullable
  private BigDecimal totalRefunds;

  public static final String SERIALIZED_NAME_TOTAL_DISCOUNT = "total_discount";
  //@SerializedName(SERIALIZED_NAME_TOTAL_DISCOUNT)
  @javax.annotation.Nullable
  private String totalDiscount;

  public static final String SERIALIZED_NAME_TOTALS_GROUPED_BY = "totals_grouped_by";
  //@SerializedName(SERIALIZED_NAME_TOTALS_GROUPED_BY)
  @javax.annotation.Nullable
  private String totalsGroupedBy;

  public static final String SERIALIZED_NAME_TOTAL_CUSTOMERS = "total_customers";
  //@SerializedName(SERIALIZED_NAME_TOTAL_CUSTOMERS)
  @javax.annotation.Nullable
  private Integer totalCustomers;

  public static final String SERIALIZED_NAME_TOTALS = "totals";
  //@SerializedName(SERIALIZED_NAME_TOTALS)
  @javax.annotation.Nullable
  private Map<String, ReportSalesSummaryItem> totals = new HashMap<>();

  private HashMap<String, Link> links;

  public ReportSalesSummary() {
  }

  public ReportSalesSummary totalSales(@javax.annotation.Nullable String totalSales) {
    this.totalSales = totalSales;
    return this;
  }

  /**
   * Get totalSales
   * @return totalSales
   */
  @javax.annotation.Nullable
  public String getTotalSales() {
    return totalSales;
  }

  public void setTotalSales(@javax.annotation.Nullable String totalSales) {
    this.totalSales = totalSales;
  }


  public ReportSalesSummary netSales(@javax.annotation.Nullable String netSales) {
    this.netSales = netSales;
    return this;
  }

  /**
   * Get netSales
   * @return netSales
   */
  @javax.annotation.Nullable
  public String getNetSales() {
    return netSales;
  }

  public void setNetSales(@javax.annotation.Nullable String netSales) {
    this.netSales = netSales;
  }


  public ReportSalesSummary averageSales(@javax.annotation.Nullable String averageSales) {
    this.averageSales = averageSales;
    return this;
  }

  /**
   * Get averageSales
   * @return averageSales
   */
  @javax.annotation.Nullable
  public String getAverageSales() {
    return averageSales;
  }

  public void setAverageSales(@javax.annotation.Nullable String averageSales) {
    this.averageSales = averageSales;
  }


  public ReportSalesSummary totalOrders(@javax.annotation.Nullable Integer totalOrders) {
    this.totalOrders = totalOrders;
    return this;
  }

  /**
   * Get totalOrders
   * @return totalOrders
   */
  @javax.annotation.Nullable
  public Integer getTotalOrders() {
    return totalOrders;
  }

  public void setTotalOrders(@javax.annotation.Nullable Integer totalOrders) {
    this.totalOrders = totalOrders;
  }


  public ReportSalesSummary totalItems(@javax.annotation.Nullable Integer totalItems) {
    this.totalItems = totalItems;
    return this;
  }

  /**
   * Get totalItems
   * @return totalItems
   */
  @javax.annotation.Nullable
  public Integer getTotalItems() {
    return totalItems;
  }

  public void setTotalItems(@javax.annotation.Nullable Integer totalItems) {
    this.totalItems = totalItems;
  }


  public ReportSalesSummary totalTax(@javax.annotation.Nullable String totalTax) {
    this.totalTax = totalTax;
    return this;
  }

  /**
   * Get totalTax
   * @return totalTax
   */
  @javax.annotation.Nullable
  public String getTotalTax() {
    return totalTax;
  }

  public void setTotalTax(@javax.annotation.Nullable String totalTax) {
    this.totalTax = totalTax;
  }


  public ReportSalesSummary totalShipping(@javax.annotation.Nullable String totalShipping) {
    this.totalShipping = totalShipping;
    return this;
  }

  /**
   * Get totalShipping
   * @return totalShipping
   */
  @javax.annotation.Nullable
  public String getTotalShipping() {
    return totalShipping;
  }

  public void setTotalShipping(@javax.annotation.Nullable String totalShipping) {
    this.totalShipping = totalShipping;
  }


  public ReportSalesSummary totalRefunds(@javax.annotation.Nullable BigDecimal totalRefunds) {
    this.totalRefunds = totalRefunds;
    return this;
  }

  /**
   * Get totalRefunds
   * @return totalRefunds
   */
  @javax.annotation.Nullable
  public BigDecimal getTotalRefunds() {
    return totalRefunds;
  }

  public void setTotalRefunds(@javax.annotation.Nullable BigDecimal totalRefunds) {
    this.totalRefunds = totalRefunds;
  }


  public ReportSalesSummary totalDiscount(@javax.annotation.Nullable String totalDiscount) {
    this.totalDiscount = totalDiscount;
    return this;
  }

  /**
   * Get totalDiscount
   * @return totalDiscount
   */
  @javax.annotation.Nullable
  public String getTotalDiscount() {
    return totalDiscount;
  }

  public void setTotalDiscount(@javax.annotation.Nullable String totalDiscount) {
    this.totalDiscount = totalDiscount;
  }


  public ReportSalesSummary totalsGroupedBy(@javax.annotation.Nullable String totalsGroupedBy) {
    this.totalsGroupedBy = totalsGroupedBy;
    return this;
  }

  /**
   * Get totalsGroupedBy
   * @return totalsGroupedBy
   */
  @javax.annotation.Nullable
  public String getTotalsGroupedBy() {
    return totalsGroupedBy;
  }

  public void setTotalsGroupedBy(@javax.annotation.Nullable String totalsGroupedBy) {
    this.totalsGroupedBy = totalsGroupedBy;
  }


  public ReportSalesSummary totalCustomers(@javax.annotation.Nullable Integer totalCustomers) {
    this.totalCustomers = totalCustomers;
    return this;
  }

  /**
   * Get totalCustomers
   * @return totalCustomers
   */
  @javax.annotation.Nullable
  public Integer getTotalCustomers() {
    return totalCustomers;
  }

  public void setTotalCustomers(@javax.annotation.Nullable Integer totalCustomers) {
    this.totalCustomers = totalCustomers;
  }


  public ReportSalesSummary totals(@javax.annotation.Nullable Map<String, ReportSalesSummaryItem> totals) {
    this.totals = totals;
    return this;
  }

  public ReportSalesSummary putTotalsItem(String key, ReportSalesSummaryItem totalsItem) {
    if (this.totals == null) {
      this.totals = new HashMap<>();
    }
    this.totals.put(key, totalsItem);
    return this;
  }

  /**
   * Get totals
   * @return totals
   */
  @javax.annotation.Nullable
  public Map<String, ReportSalesSummaryItem> getTotals() {
    return totals;
  }

  public void setTotals(@javax.annotation.Nullable Map<String, ReportSalesSummaryItem> totals) {
    this.totals = totals;
  }

    @JsonProperty("_links")
    @JsonDeserialize(using = JsonMappedLinks.class)
    public void setLinks(HashMap<String, Link> links) {
        this.links = links;
    }
    @JsonProperty("_links")
    public HashMap<String, Link> getLinks( ) {
        return links;
    }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReportSalesSummary reportSalesSummary = (ReportSalesSummary) o;
    return Objects.equals(this.totalSales, reportSalesSummary.totalSales) &&
        Objects.equals(this.netSales, reportSalesSummary.netSales) &&
        Objects.equals(this.averageSales, reportSalesSummary.averageSales) &&
        Objects.equals(this.totalOrders, reportSalesSummary.totalOrders) &&
        Objects.equals(this.totalItems, reportSalesSummary.totalItems) &&
        Objects.equals(this.totalTax, reportSalesSummary.totalTax) &&
        Objects.equals(this.totalShipping, reportSalesSummary.totalShipping) &&
        Objects.equals(this.totalRefunds, reportSalesSummary.totalRefunds) &&
        Objects.equals(this.totalDiscount, reportSalesSummary.totalDiscount) &&
        Objects.equals(this.totalsGroupedBy, reportSalesSummary.totalsGroupedBy) &&
        Objects.equals(this.totalCustomers, reportSalesSummary.totalCustomers) &&
        Objects.equals(this.totals, reportSalesSummary.totals);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalSales, netSales, averageSales, totalOrders, totalItems, totalTax, totalShipping, totalRefunds, totalDiscount, totalsGroupedBy, totalCustomers, totals);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReportSalesSummary {\n");
    sb.append("    totalSales: ").append(toIndentedString(totalSales)).append("\n");
    sb.append("    netSales: ").append(toIndentedString(netSales)).append("\n");
    sb.append("    averageSales: ").append(toIndentedString(averageSales)).append("\n");
    sb.append("    totalOrders: ").append(toIndentedString(totalOrders)).append("\n");
    sb.append("    totalItems: ").append(toIndentedString(totalItems)).append("\n");
    sb.append("    totalTax: ").append(toIndentedString(totalTax)).append("\n");
    sb.append("    totalShipping: ").append(toIndentedString(totalShipping)).append("\n");
    sb.append("    totalRefunds: ").append(toIndentedString(totalRefunds)).append("\n");
    sb.append("    totalDiscount: ").append(toIndentedString(totalDiscount)).append("\n");
    sb.append("    totalsGroupedBy: ").append(toIndentedString(totalsGroupedBy)).append("\n");
    sb.append("    totalCustomers: ").append(toIndentedString(totalCustomers)).append("\n");
    sb.append("    totals: ").append(toIndentedString(totals)).append("\n");
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

  /**
   * Convert an instance of ReportSalesSummary to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
      return new JacksonObjectMapper().toJson(this);
  }
}

