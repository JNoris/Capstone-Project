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
 * Creates the OrderItemPK object.
 * @author Vinicius Smith
 */
@Embeddable
public class OrderItemPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "itemID")
    private int itemID;
    @Basic(optional = false)
    @Column(name = "order_no")
    private int orderNo;

    /**
     * Default constructor for the OrderItemPK object.
     */
    public OrderItemPK() {
    }

    /**
     * Non-default constructor for the Order Item PK object.
     * @param itemID
     * @param orderNo 
     */
    public OrderItemPK(int itemID, int orderNo) {
        this.itemID = itemID;
        this.orderNo = orderNo;
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
     * Gets the order number.
     * @return orderNo
     */
    public int getOrderNo() {
        return orderNo;
    }

    /**
     * Sets the order number.
     * @param orderNo 
     */
    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * Returns the hash value of order item PK.
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) itemID;
        hash += (int) orderNo;
        return hash;
    }

    /**
     * Checks if the value of the order item PK equals another item.
     * @param object
     * @return true if it equals otherwise false
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderItemPK)) {
            return false;
        }
        OrderItemPK other = (OrderItemPK) object;
        if (this.itemID != other.itemID) {
            return false;
        }
        if (this.orderNo != other.orderNo) {
            return false;
        }
        return true;
    }

    /**
     * Returns the order item PK as a string value.
     * @return "domain.OrderItemPK[ itemID=" + itemID + ", orderNo=" + orderNo + " ]"
     */
    @Override
    public String toString() {
        return "domain.OrderItemPK[ itemID=" + itemID + ", orderNo=" + orderNo + " ]";
    }
    
}
