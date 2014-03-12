package rpgStoryTellersAide.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-01-29T00:16:30.604-0700")
@StaticMetamodel(Location.class)
public class Location_ {
	public static volatile SingularAttribute<Location, Long> id;
	public static volatile SingularAttribute<Location, Long> version;
	public static volatile SingularAttribute<Location, String> name;
	public static volatile SingularAttribute<Location, String> description;
	public static volatile ListAttribute<Location, Scene> scenes;
}
