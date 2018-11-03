package net.softengine.model;

import org.apache.commons.lang.BooleanUtils;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Copyright @ Soft Engine [www.soft-engine.net].
 * Created on 07-Jan-18 at 3:58 PM
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Last Edited on : 07-Jan-18
 * Version : 1.0
 */

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "SEC_USER_MASTER")
public class User extends GenericModel implements Serializable {

    private static final long serialVersionUID = 7389991231663740924L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer status;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "short_name")
    private String shortName;

    /*@ManyToMany(fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "CSR_SEC_GROUP_VS_MEMBER", joinColumns = @JoinColumn(name = "MEMBER_ID"),
            inverseJoinColumns = @JoinColumn(name = "GROUP_ID"))
    private List<Group> groupList = new ArrayList<>();
*/
    // Removed token from user to keep token 1 step far from Users
    /*@OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    @Fetch(FetchMode.SELECT)
    private Token token;*/

    public boolean isActive() {
        return this.status != null && this.status == 1;
    }

    public void setActive(boolean active) {
        this.status = active ? 1 : 0;
    }

    @Transient
    public String getDiscriminatorValue() {
        DiscriminatorValue val = this.getClass().getAnnotation(DiscriminatorValue.class);
        return val == null ? this.getClass().getSimpleName() : val.value();
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }


    public String info() {
        return this.name;
    }

    //================================
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
/* public List<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }*/
}
