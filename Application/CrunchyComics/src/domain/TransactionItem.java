/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
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
 * Creates the Transaction Item object.
 * @author Vinicius Smith
 */
@Entity
@Table(name = "transaction_item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TransactionItem.findAll", query = "SELECT t FROM TransactionItem t")
    , @NamedQuery(name = "TransactionItem.findByItemID", query = "SELECT t FROM TransactionItem t WHERE t.transactionItemPK.itemID = :itemID")
    , @NamedQuery(name = "TransactionItem.findByTransactionID", query = "SELECT t FROM TransactionItem t WHERE t.transactionItemPK.transactionID = :transactionID")
    , @NamedQuery(name = "TransactionItem.findTransactionItem", query = "SELECT t FROM TransactionItem t WHERE t.transactionItemPK.transactionID = :transactionID AND t.transactionItemPK.itemID = :itemID")
//    , @NamedQuery(name = "TransactionItem.findByPrice", query = "SELECT t FROM TransactionItem t WHERE t.price = :price")
    , @NamedQuery(name = "TransactionItem.findByQuantity", query = "SELECT t FROM TransactionItem t WHERE t.transactionItemPK.quantity = :quantity")
    , @NamedQuery(name = "TransactionItem.findBySoldPrice", query = "SELECT t FROM TransactionItem t WHERE t.transactionItemPK.soldPrice = :soldPrice")
    , @NamedQuery(name = "TransactionItem.findMostSoldItem", query = "SELECT t FROM TransactionItem t GROUP BY t.transactionItemPK.itemID ORDER BY COUNT(t) desc")})
public class TransactionItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TransactionItemPK transactionItemPK;
    @JoinColumn(name = "transactionID", referencedColumnName = "transactionID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Transaction transaction;
    @JoinColumn(name = "itemID", referencedColumnName = "itemID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Item item;

    /**
     * Default constructor for the transaction item object.
     */
    public TransactionItem() {
    }

     /**
     * Non-default constructor for the transaction item based on transactionitemPK alone.
     * @param transactionItemPK 
     */
    public TransactionItem(TransactionItemPK transactionItemPK) {
        this.transactionItemPK = transactionItemPK;
    }

    /**
     * Non-default constructor for the TransactionItem.
     * @param itemID
     * @param transactionID
     * @param quantity
     * @param soldPrice 
     */
    public TransactionItem(int itemID, int transactionID, int quantity, float soldPrice) {
        this.transactionItemPK = new TransactionItemPK(itemID, transactionID, quantity, soldPrice);
    }

    /**
     * Gets the TransactionItem PK.
     * @return transactionItemPK
     */
    public TransactionItemPK getTransactionItemPK() {
        return transactionItemPK;
    }

    /**
     * Sets the TransactionItemPK
     * @param transactionItemPK 
     */
    public void setTransactionItemPK(TransactionItemPK transactionItemPK) {
        this.transactionItemPK = transactionItemPK;
    }

    /**
     * Gets the TransactionItem based on Transaction.
     * @return 
     */
    public Transaction getTransaction() {
        return transaction;
    }

    /**
     * Sets the TransactionItem based on Transaction.
     * @param transaction 
     */
    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    /**
     * Gets the TransactionItem based on Item.
     * @return 
     */
    public Item getItem() {
        return item;
    }

    /**
     * Sets the item related to TransactionItem.
     * @param item 
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * Gets the hash value of the Transaction Item.
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transactionItemPK != null ? transactionItemPK.hashCode() : 0);
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
        if (!(object instanceof TransactionItem)) {
            return false;
        }
        TransactionItem other = (TransactionItem) object;
        if ((this.transactionItemPK == null && other.transactionItemPK != null) || (this.transactionItemPK != null && !this.transactionItemPK.equals(other.transactionItemPK))) {
            return false;
        }
        return true;
    }

    /**
     * Returns the transaction as a string value
     * @return "domain.TransactionItem[ transactionItemPK=" + transactionItemPK + " ]"
     */
    @Override
    public String toString() {
        return "domain.TransactionItem[ transactionItemPK=" + transactionItemPK + " ]";
    }

}
