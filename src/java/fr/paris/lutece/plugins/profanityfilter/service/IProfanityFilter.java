package fr.paris.lutece.plugins.profanityfilter.service;

import fr.paris.plugins.profanityfilter.utils.ProfanityResult;

public interface IProfanityFilter {
	
	/**
	 * Check the str
	 * @param str the string to check
	 * @return Object ProfanityResult 
	 */
	public ProfanityResult checkString(String str);

}
