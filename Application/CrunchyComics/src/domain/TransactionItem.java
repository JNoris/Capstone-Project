/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Vinicius Smith
 */
@Entity
@Table(name = "transaction_item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TransactionItem.findAll", query = "SELECT t FROM TransactionItem t")
    , @NamedQuery(name = "TransactionItem.findByItemID", query = "SELECT t FROM TransactionItem t WHERE t.transactionItemPK.itemID = :itemID")
    , @NamedQuery(name = "TransactionItem.findByTransactionID", query = "SELECT t FROM TransactionItem t WHERE t.transactionItemPK.transactionID = :transactionID")
    , @NamedQuery(name = "TransactionItem.findBySoldPrice", query = "SELECT t FROM TransactionItem t WHERE t.soldPrice = :soldPrice")})

public class TransactionItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TransactionItemPK transactionItemPK;
    @Basic(optional = false)
    @Column(name = "sold_price")
    private float soldPrice;
    @JoinColumn(name = "transactionID", referencedColumnName = "transactionID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Transaction transaction;
    @JoinColumn(name = "itemID", referencedColumnName = "itemID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Item item;

    public TransactionItem() {
    }

    public TransactionItem(TransactionItemPK transactionItemPK) {
        this.transactionItemPK = transactionItemPK;
    }

    public TransactionItem(TransactionItemPK transactionItemPK, float soldPrice) {
        this.transactionItemPK = transactionItemPK;
        this.soldPrice = soldPrice;
    }

    public TransactionItem(int itemID, int transactionID) {
        this.transactionItemPK = new TransactionItemPK(itemID, transactionID);
    }

    public TransactionItemPK getTransactionItemPK() {
        return transactionItemPK;
    }

    public void setTransactionItemPK(TransactionItemPK transactionItemPK) {
        this.transactionItemPK = transactionItemPK;
    }

    public float getSoldPrice() {
        return soldPrice;
    }

    public void setSoldPrice(float soldPrice) {
        this.soldPrice = soldPrice;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transactionItemPK != null ? transactionItemPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransactionItem)) {
            return false;
        }
        TransactionItem other = (TransactionItem) object;
        if ((this.transactionItemPK == null && other.transactionItemPK != null) || (this.transactionItemPK != null && !this.transactionItemPK.equals(other.transactionItemPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.TransactionItem[ transactionItemPK=" + transactionItemPK + " ]";
    }
    
}
