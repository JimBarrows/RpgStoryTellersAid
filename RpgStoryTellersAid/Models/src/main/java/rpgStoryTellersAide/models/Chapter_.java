package rpgStoryTellersAide.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-01-29T00:16:30.589-0700")
@StaticMetamodel(Chapter.class)
public class Chapter_ {
	public static volatile SingularAttribute<Chapter, Long> id;
	public static volatile SingularAttribute<Chapter, Long> version;
	public static volatile SingularAttribute<Chapter, String> title;
	public static volatile SingularAttribute<Chapter, Long> number;
	public static volatile SingularAttribute<Chapter, String> description;
	public static volatile SingularAttribute<Chapter, Story> story;
	public static volatile ListAttribute<Chapter, Scene> scenes;
}
