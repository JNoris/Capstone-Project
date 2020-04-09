/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Creates the refund object.
 * @author Vinicius Smith
 */
@Entity
@Table(name = "refund")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Refund.findAll", query = "SELECT r FROM Refund r")
    , @NamedQuery(name = "Refund.findByRefundID", query = "SELECT r FROM Refund r WHERE r.refundID = :refundID")
    , @NamedQuery(name = "Refund.findByTransactionID", query = "SELECT r FROM Refund r WHERE r.transactionID = :transactionID")
    , @NamedQuery(name = "Refund.findByItemID", query = "SELECT r FROM Refund r WHERE r.itemID = :itemID")})
public class Refund implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "refundID")
    private Integer refundID;
    @Column(name = "transactionID")
    private Integer transactionID;
    @Column(name = "itemID")
    private Integer itemID;

    /**
     * Default constructor for the refund object.
     */
    public Refund() {
    }

    /**
     * Non-default constructor for the refund based on refund ID alone.
     * @param refundID 
     */
    public Refund(Integer refundID) {
        this.refundID = refundID;
    }

    /**
     * Gets the refund ID.
     * @return refundID
     */
    public Integer getRefundID() {
        return refundID;
    }

    /**
     * Sets the refund ID.
     * @param refundID 
     */
    public void setRefundID(Integer refundID) {
        this.refundID = refundID;
    }

    /**
     * Gets the TransactionID for the refund
     * @return transactionID
     */
    public Integer getTransactionID() {
        return transactionID;
    }

    /**
     * Sets the TransactionID for the refund
     * @param transactionID 
     */
    public void setTransactionID(Integer transactionID) {
        this.transactionID = transactionID;
    }

    /**
     * Gets the ItemID for the refund.
     * @return itemID
     */
    public Integer getItemID() {
        return itemID;
    }

    /**
     * Sets the ItemID for the refund.
     * @param itemID 
     */
    public void setItemID(Integer itemID) {
        this.itemID = itemID;
    }

    /**
     * Gets the hash value of the refund.
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (refundID != null ? refundID.hashCode() : 0);
        return hash;
    }

    /**
     * Checks if the value of the refund equals another item.
     * @param object
     * @return true if it equals otherwise false
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Refund)) {
            return false;
        }
        Refund other = (Refund) object;
        if ((this.refundID == null && other.refundID != null) || (this.refundID != null && !this.refundID.equals(other.refundID))) {
            return false;
        }
        return true;
    }

    /**
     * Returns the refund as a string value
     * @return "domain.Refund[ refundID=" + refundID + " ]"
     */
    @Override
    public String toString() {
        return "domain.Refund[ refundID=" + refundID + " ]";
    }
    
}
