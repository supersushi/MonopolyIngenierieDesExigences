package settings;

import exception.ResponseNotFoundException;
import model.Fenetre;

public class FenetreFactorySettings {
	public static Object controller(String request, Fenetre fenetre) throws ResponseNotFoundException {
		FenetresControllerSettings.configuration(fenetre);
		if (FenetresControllerSettings.controllerByRequest.get(request) == null)
			throw new ResponseNotFoundException("Aucun Controller n'a été trouvé.");
		return FenetresControllerSettings.controllerByRequest.get(request);
	}
}
