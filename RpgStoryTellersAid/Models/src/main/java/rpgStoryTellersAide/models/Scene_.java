package rpgStoryTellersAide.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-02-04T07:40:26.595-0700")
@StaticMetamodel(Scene.class)
public class Scene_ {
	public static volatile SingularAttribute<Scene, Long> id;
	public static volatile SingularAttribute<Scene, Long> version;
	public static volatile SingularAttribute<Scene, String> title;
	public static volatile SingularAttribute<Scene, Long> number;
	public static volatile SingularAttribute<Scene, String> gmDescription;
	public static volatile SingularAttribute<Scene, String> playerDescription;
	public static volatile SingularAttribute<Scene, Chapter> chapter;
	public static volatile ListAttribute<Scene, Clue> clues;
	public static volatile ListAttribute<Scene, Actor> actors;
	public static volatile SingularAttribute<Scene, Location> location;
}
