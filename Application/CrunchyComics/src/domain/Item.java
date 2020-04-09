/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Creates the item object.
 * @author Vinicius Smith
 */
@Entity
@Table(name = "item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item i")
    , @NamedQuery(name = "Item.findByItemID", query = "SELECT i FROM Item i WHERE i.itemID = :itemID")
    , @NamedQuery(name = "Item.findByDescription", query = "SELECT i FROM Item i WHERE i.description = :description")
    , @NamedQuery(name = "Item.findByPrice", query = "SELECT i FROM Item i WHERE i.price = :price")
    , @NamedQuery(name = "Item.findByName", query = "SELECT i FROM Item i WHERE i.name = :name")
    , @NamedQuery(name = "Item.findByMatchingName", query = "SELECT i FROM Item i WHERE LOWER(i.name) LIKE CONCAT('%',LOWER(:name),'%')")
    , @NamedQuery(name = "Item.findByQuantity", query = "SELECT i FROM Item i WHERE i.quantity = :quantity")
    , @NamedQuery(name = "Item.findByUpc", query = "SELECT i FROM Item i WHERE i.upc = :upc")
    , @NamedQuery(name = "Item.findLastID", query = "SELECT MAX(i.itemID) FROM Item i")})
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "itemID")
    private Integer itemID;
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "price")
    private float price;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "upc")
    private String upc;
    @JoinColumn(name = "item_type", referencedColumnName = "item_type")
    @ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER)
    private Type itemType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item", fetch = FetchType.EAGER)
    private List<TransactionItem> transactionItemList;

    /**
     * Default constructor for the item object.
     */
    public Item() {
    }

    /**
     * Non-default constructor for the item object based on ID alone.
     * @param itemID 
     */
    public Item(Integer itemID) {
        this.itemID = itemID;
    }

    /**
     * Non-default constructor for the comic object.
     * @param itemID
     * @param price
     * @param name 
     */
    public Item(Integer itemID, float price, String name) {
        this.itemID = itemID;
        this.price = price;
        this.name = name;
    }

    /**
     * Gets the ItemID.
     * @return itemID
     */
    public Integer getItemID() {
        return itemID;
    }

    /**
     * Sets the ItemID.
     * @param itemID 
     */
    public void setItemID(Integer itemID) {
        this.itemID = itemID;
    }

    /**
     * Gets the description.
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     * @param description 
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the price.
     * @return price
     */
    public float getPrice() {
        return price;
    }

    /**
     * Sets the price.
     * @param price 
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Gets the name.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the quantity.
     * @return quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity.
     * @param quantity 
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the UPC
     * @return upc
     */
    public String getUpc() {
        return upc;
    }

    /**
     * Sets the UPC.
     * @param upc 
     */
    public void setUpc(String upc) {
        this.upc = upc;
    }

    /**
     * Gets the itemType.
     * @return itemType.
     */
    public Type getItemType() {
        return itemType;
    }

    /**
     * Sets the itemType.
     * @param itemType 
     */
    public void setItemType(Type itemType) {
        this.itemType = itemType;
    }

    /**
     * Gets the TransactionItem list.
     * @return transactionItemList.
     */
    @XmlTransient
    public List<TransactionItem> getTransactionItemList() {
        return transactionItemList;
    }

    /**
     * Sets the TransactionItemList.
     * @param transactionItemList 
     */
    public void setTransactionItemList(List<TransactionItem> transactionItemList) {
        this.transactionItemList = transactionItemList;
    }

    /**
     * Gets the hash value of the item.
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemID != null ? itemID.hashCode() : 0);
        return hash;
    }

    /**
     * Checks if the value of the item equals another item.
     * @param object
     * @return true if it equals otherwise false
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.itemID == null && other.itemID != null) || (this.itemID != null && !this.itemID.equals(other.itemID))) {
            return false;
        }
        return true;
    }

    /**
     * Returns the Item as a String.
     * @return "domain.Item[ itemID=" + itemID + " ]"
     */
    @Override
    public String toString() {
        return "domain.Item[ itemID=" + itemID + " ]";
    }

}
