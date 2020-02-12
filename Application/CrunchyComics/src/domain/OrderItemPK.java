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
public class OrderItemPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "itemID")
    private int itemID;
    @Basic(optional = false)
    @Column(name = "order_no")
    private int orderNo;

    public OrderItemPK() {
    }

    public OrderItemPK(int itemID, int orderNo) {
        this.itemID = itemID;
        this.orderNo = orderNo;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) itemID;
        hash += (int) orderNo;
        return hash;
    }

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

    @Override
    public String toString() {
        return "broker.OrderItemPK[ itemID=" + itemID + ", orderNo=" + orderNo + " ]";
    }
    
}
