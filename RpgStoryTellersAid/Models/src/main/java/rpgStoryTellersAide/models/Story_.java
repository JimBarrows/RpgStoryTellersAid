package rpgStoryTellersAide.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-01-29T00:16:30.627-0700")
@StaticMetamodel(Story.class)
public class Story_ {
	public static volatile SingularAttribute<Story, Long> id;
	public static volatile SingularAttribute<Story, Long> version;
	public static volatile SingularAttribute<Story, String> name;
	public static volatile SingularAttribute<Story, String> description;
	public static volatile ListAttribute<Story, Chapter> chapters;
}
