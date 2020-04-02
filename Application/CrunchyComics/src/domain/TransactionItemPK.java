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
 *
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

    public TransactionItemPK() {
    }

    public TransactionItemPK(int itemID, int transactionID, int quantity, float soldPrice) {
        this.itemID = itemID;
        this.transactionID = transactionID;
        this.quantity = quantity;
        this.soldPrice = soldPrice;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getSoldPrice() {
        return soldPrice;
    }

    public void setSoldPrice(float soldPrice) {
        this.soldPrice = soldPrice;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) itemID;
        hash += (int) transactionID;
        hash += (int) quantity;
        hash += (int) soldPrice;
        return hash;
    }

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

    @Override
    public String toString() {
        return "domain.TransactionItemPK[ itemID=" + itemID + ", transactionID=" + transactionID + ", quantity=" + quantity + ", soldPrice=" + soldPrice + " ]";
    }
    
}
