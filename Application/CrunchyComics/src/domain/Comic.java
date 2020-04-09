package domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Creates the comic object.
 *
 * @author Vinicius Smith
 */
@Entity
@Table(name = "comic")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comic.findAll", query = "SELECT c FROM Comic c")
    , @NamedQuery(name = "Comic.findByItemID", query = "SELECT c FROM Comic c WHERE c.itemID = :itemID")
    , @NamedQuery(name = "Comic.findByAuthor", query = "SELECT c FROM Comic c WHERE c.author = :author")
    , @NamedQuery(name = "Comic.findByVolume", query = "SELECT c FROM Comic c WHERE c.volume = :volume")
    , @NamedQuery(name = "Comic.findByIsbn", query = "SELECT c FROM Comic c WHERE c.isbn = :isbn")
    , @NamedQuery(name = "Comic.findByPublisher", query = "SELECT c FROM Comic c WHERE c.publisher = :publisher")})
public class Comic implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "itemID")
    private Integer itemID;
    @Basic(optional = false)
    @Column(name = "author")
    private String author;
    @Basic(optional = false)
    @Column(name = "volume")
    private int volume;
    @Basic(optional = false)
    @Column(name = "isbn")
    private String isbn;
    @Column(name = "publisher")
    private String publisher;
    @JoinColumn(name = "itemID", referencedColumnName = "itemID", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    private Item item;

    /**
     * Default constructor for the comic object.
     */
    public Comic() {
    }

    /**
     * Non-default constructor for the comic object based on ID alone.
     *
     * @param itemID
     */
    public Comic(Integer itemID) {
        this.itemID = itemID;
    }

    /**
     * Non-default constructor for the comic object.
     *
     * @param itemID
     * @param author
     * @param volume
     * @param isbn
     */
    public Comic(Integer itemID, String author, int volume, String isbn) {
        this.itemID = itemID;
        this.author = author;
        this.volume = volume;
        this.isbn = isbn;
    }

    /**
     * Gets the item ID.
     *
     * @return itemID
     */
    public Integer getItemID() {
        return itemID;
    }

    /**
     * Sets the item ID.
     *
     * @param itemID
     */
    public void setItemID(Integer itemID) {
        this.itemID = itemID;
    }

    /**
     * Gets the author of the comic.
     *
     * @return author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author for the comic.
     *
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets the volume of the comic.
     *
     * @return volume
     */
    public int getVolume() {
        return volume;
    }

    /**
     * Sets the volume for the comic.
     *
     * @param volume
     */
    public void setVolume(int volume) {
        this.volume = volume;
    }

    /**
     * Gets the ISBN of the comic.
     *
     * @return isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Sets the ISBN for the comic.
     *
     * @param isbn
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Gets the Publisher for the comic.
     *
     * @return publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Sets the Publisher for the comic.
     *
     * @param publisher
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Gets the item related to the comic.
     *
     * @return item
     */
    public Item getItem() {
        return item;
    }

    /**
     * Sets the item related to the comic.
     *
     * @param item
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * Returns the hash value of the comic.
     *
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemID != null ? itemID.hashCode() : 0);
        return hash;
    }

    /**
     * Checks if the value of the comics equals another item.
     *
     * @param object
     * @return true if it equals otherwise false
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comic)) {
            return false;
        }
        Comic other = (Comic) object;
        if ((this.itemID == null && other.itemID != null) || (this.itemID != null && !this.itemID.equals(other.itemID))) {
            return false;
        }
        return true;
    }

    /**
     * Returns the comic as a string value.
     *
     * @return "domain.Comic[ itemID=" + itemID + " ]"
     */
    @Override
    public String toString() {
        return "domain.Comic[ itemID=" + itemID + " ]";
    }

}
