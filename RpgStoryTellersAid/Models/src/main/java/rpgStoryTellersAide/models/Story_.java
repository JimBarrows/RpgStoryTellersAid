package rpgStoryTellersAide.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-02-01T06:44:13.392-0700")
@StaticMetamodel(Story.class)
public class Story_ {
	public static volatile SingularAttribute<Story, Long> id;
	public static volatile SingularAttribute<Story, Long> version;
	public static volatile SingularAttribute<Story, String> name;
	public static volatile SingularAttribute<Story, String> description;
	public static volatile ListAttribute<Story, Chapter> chapters;
}
