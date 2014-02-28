package rpgStoryTellersAide.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-01-29T00:16:30.596-0700")
@StaticMetamodel(Clue.class)
public class Clue_ {
	public static volatile SingularAttribute<Clue, Long> id;
	public static volatile SingularAttribute<Clue, Long> version;
	public static volatile SingularAttribute<Clue, String> description;
	public static volatile SingularAttribute<Clue, String> obtainBy;
	public static volatile SingularAttribute<Clue, Scene> scene;
}
