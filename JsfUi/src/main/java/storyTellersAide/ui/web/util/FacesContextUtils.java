package storyTellersAide.ui.web.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesContextUtils {

	public static void errorMessage(String message) {

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
	}

	public static void errorMessage(String message, String id) {
		FacesContext.getCurrentInstance().addMessage(id,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
	}

	public static void return404() {
		FacesContext.getCurrentInstance().getExternalContext()
				.setResponseStatus(404);
		FacesContext.getCurrentInstance().responseComplete();
	}
}
