package domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Access(AccessType.PROPERTY)
public abstract class CanBeCommented  extends DomainEntity{

    protected List<Comment> comments;

    @OneToMany(mappedBy = "canBeCommented")
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

}
