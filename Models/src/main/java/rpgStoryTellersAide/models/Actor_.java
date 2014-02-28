package rpgStoryTellersAide.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-01-29T00:16:30.561-0700")
@StaticMetamodel(Actor.class)
public class Actor_ {
	public static volatile SingularAttribute<Actor, Long> id;
	public static volatile SingularAttribute<Actor, Long> version;
	public static volatile SingularAttribute<Actor, String> name;
	public static volatile SingularAttribute<Actor, String> physicalDescription;
	public static volatile SingularAttribute<Actor, String> description;
	public static volatile ListAttribute<Actor, Scene> scenes;
}
