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
 * @author 695553
 */
@Embeddable
public class TransactionItemPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "itemID")
    private int itemID;
    @Basic(optional = false)
    @Column(name = "transactionID")
    private int transactionID;

    public TransactionItemPK() {
    }

    public TransactionItemPK(int itemID, int transactionID) {
        this.itemID = itemID;
        this.transactionID = transactionID;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) itemID;
        hash += (int) transactionID;
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
        return true;
    }

    @Override
    public String toString() {
        return "broker.TransactionItemPK[ itemID=" + itemID + ", transactionID=" + transactionID + " ]";
    }
    
}
