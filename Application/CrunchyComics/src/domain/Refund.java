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
 *
 * @author 695553
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

    public Refund() {
    }

    public Refund(Integer refundID) {
        this.refundID = refundID;
    }

    public Integer getRefundID() {
        return refundID;
    }

    public void setRefundID(Integer refundID) {
        this.refundID = refundID;
    }

    public Integer getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(Integer transactionID) {
        this.transactionID = transactionID;
    }

    public Integer getItemID() {
        return itemID;
    }

    public void setItemID(Integer itemID) {
        this.itemID = itemID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (refundID != null ? refundID.hashCode() : 0);
        return hash;
    }

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

    @Override
    public String toString() {
        return "broker.Refund[ refundID=" + refundID + " ]";
    }
    
}
