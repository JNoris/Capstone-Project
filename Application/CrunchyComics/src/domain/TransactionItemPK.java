/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Creates the transaction item PK
 * @author Vinicius Smith
 */
@Embeddable
public class TransactionItemPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "itemID")
    private int itemID;
    @Basic(optional = false)
    @Column(name = "transactionID")
    private int transactionID;
    @Basic(optional = false)
    @Column(name = "quantity")
    private int quantity;
    @Basic(optional = false)
    @Column(name = "sold_price")
    private float soldPrice;

    /**
     * Default constructor for the transaction item PK object.
     */
    public TransactionItemPK() {
    }

    /**
     * Non-default constructor for the Transaction Item PK.
     * @param itemID
     * @param transactionID
     * @param quantity
     * @param soldPrice 
     */
    public TransactionItemPK(int itemID, int transactionID, int quantity, float soldPrice) {
        this.itemID = itemID;
        this.transactionID = transactionID;
        this.quantity = quantity;
        this.soldPrice = soldPrice;
    }

    /**
     * Gets the ItemID.
     * @return itemID
     */
    public int getItemID() {
        return itemID;
    }

    /**
     * Sets the ItemID.
     * @param itemID 
     */
    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    /**
     * Gets the TransactionID.
     * @return transactionID
     */
    public int getTransactionID() {
        return transactionID;
    }

    /**
     * Sets the transaction ID.
     * @param transactionID 
     */
    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    /**
     * Gets the quantity.
     * @return quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity.
     * @param quantity 
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the Sold Price.
     * @return soldPrice
     */
    public float getSoldPrice() {
        return soldPrice;
    }

    /**
     * Sets the sold price.
     * @param soldPrice 
     */
    public void setSoldPrice(float soldPrice) {
        this.soldPrice = soldPrice;
    }

    /**
     * Gets the hash value of the TransactionItemPK.
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) itemID;
        hash += (int) transactionID;
        hash += (int) quantity;
        hash += (int) soldPrice;
        return hash;
    }

     /**
     * Checks if the value of the transaction item equals another item.
     * @param object
     * @return true if it equals otherwise false
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransactionItemPK)) {
            return false;
        }
        TransactionItemPK other = (TransactionItemPK) object;
        if (this.itemID != other.itemID) {
            return false;
        }
        if (this.transactionID != other.transactionID) {
            return false;
        }
        if (this.quantity != other.quantity) {
            return false;
        }
        if (this.soldPrice != other.soldPrice) {
            return false;
        }
        return true;
    }

    /**
     * Returns the transaction item PK as a string value
     * @return "domain.TransactionItemPK[ itemID=" + itemID + ", transactionID=" + transactionID + ", quantity=" + quantity + ", soldPrice=" + soldPrice + " ]"
     */
    @Override
    public String toString() {
        return "domain.TransactionItemPK[ itemID=" + itemID + ", transactionID=" + transactionID + ", quantity=" + quantity + ", soldPrice=" + soldPrice + " ]";
    }
    
}
