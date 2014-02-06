package rpgStoryTellersAid.ui.web.converters;

import static org.apache.commons.lang3.StringUtils.isBlank;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import rpgStoryTellersAide.models.Location;
import rpgStoryTellersAide.services.Stories;
import fj.data.Option;

@FacesConverter("location")
public class LocationConverter implements Converter {

	@EJB
	private Stories stories;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if( isBlank( value)) {
			return null;
		}
		long id = Long.parseLong(value);
		Option<Location> locationBy = stories.findLocationBy( id);
		return locationBy.toNull();
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if( value instanceof Location) {
			return ((Location) value).getId().toString();
		}
		return null;
	}

}
