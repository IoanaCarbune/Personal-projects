package bll.validator;

import java.util.List;

import model.Client;


/**
 * The Class ClientValidator.
 */
public class ClientValidator {

	/**
	 * Validate.
	 *
	 * @param clientFields the client fields
	 * @return the client
	 */
	public static Client validate(List<String> clientFields) {
		int ok=0;
		if(clientFields.get(0).matches("[a-zA-Z]+") && clientFields.get(1).matches("[a-zA-Z]+")&& clientFields.get(3).matches("[0-9]+"))
			ok=1;
		else ok=0;
		
		for(String str:clientFields) {
			if(str.length()==0)
				ok=0;
		}
		if(ok==1)
		{
			Client newClient=new Client(8,clientFields.get(0),clientFields.get(1),clientFields.get(2),clientFields.get(3));
			return newClient;
		}
		else
		return null;
	}

}
