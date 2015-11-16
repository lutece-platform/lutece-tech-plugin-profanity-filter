package fr.paris.lutece.plugins.profanityfilter.service;

import fr.paris.plugins.profanityfilter.utils.ProfanityResult;

public interface IProfanityFilter {
	
	
	public ProfanityResult checkString(String str);

}
