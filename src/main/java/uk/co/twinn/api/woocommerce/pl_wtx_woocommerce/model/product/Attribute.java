/*
 * Copyright (c) 2026.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.product;

import java.util.ArrayList;
import java.util.List;

public class Attribute /*extends ProductAttribute you would think! id and name are the only commonality */ {

    private Integer id; //	integer	Attribute ID.
    private String name; //	string	Attribute name.
    private Integer position; // integer	Attribute position.
    private Boolean visible; //	boolean	Define if the attribute is visible on the "Additional information" tab in the product's page. Default is false.
    private Boolean variation; //	boolean	Define if the attribute can be used as variation. Default is false.
    private List<String> options; //array	List of available term names of the attribute.

    protected Attribute(){}

    /**
     * Attribute as part of a product is different to a ProductAttribute,
     * ProductAttribute is the main listing
     * Attribute is a tie-up between the ProductAttribute and the Product
     * @param productAttributeId the productAttributeId
     * @param options the list of options
     */
    public Attribute(int productAttributeId, List<String> options){
        this.id = productAttributeId;
        this.options = options;
    }

    /**
     * Attribute as part of a product is different to a ProductAttribute,
     * ProductAttribute is the main listing
     * Attribute is a tie-up between the ProductAttribute and the Product
     * @param productAttributeId the productAttributeId
     * @param option an option to set
     */
    public Attribute(int productAttributeId, String option){
        this.id = productAttributeId;
        this.options = new ArrayList<>();
        this.options.add(option);
    }

    public Attribute id(int id){
        this.setId(id);
        return this;
    }

    public Attribute position(int position){
        this.setPosition(position);
        return this;
    }
    public Attribute visible(boolean visible){
        this.setVisible(visible);
        return this;
    }
    public Attribute variation(boolean variation){
        this.setVariation(variation);
        return this;
    }
    public Attribute options(List<String> options){
        this.setOptions(options);
        return this;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    /**
     * Name is read-only, here for json.
     * @param name the name of the attribute
     */
    public void setName(String name) {
        this.name = name;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Boolean getVariation() {
        return variation;
    }

    public void setVariation(Boolean variation) {
        this.variation = variation;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}
