package fr.paris.lutece.plugins.profanityfilter.service;

import java.util.Collection;

import org.apache.commons.lang.StringUtils;

import fr.paris.lutece.plugins.profanityfilter.business.Word;
import fr.paris.lutece.plugins.profanityfilter.business.WordHome;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.plugins.profanityfilter.utils.ProfanityResult;

public class ProfanityFilter implements IProfanityFilter{
	
	private static ProfanityFilter _singleton;
	
	// bean
    public static final String BEAN_PROFANITY_FILTER_SERVICE = "profanityfilter.profanityfilterService";
	public static final String CHARACTER_SPACE_BETWEEN_WORDS= AppPropertiesService.getProperty("character_space_between_words", " ");
	 /**
    *
    * @return IEudonetWsService
    */
   public static IProfanityFilter getService(  )
   {
       if ( _singleton == null )
       {
           _singleton = SpringContextService.getBean( BEAN_PROFANITY_FILTER_SERVICE );
       }

       return _singleton;
   }

	@Override
	public ProfanityResult checkString(String str) {
		
		ProfanityResult profResult= new ProfanityResult();
		String [] wordStr = null;
		Collection<Word> wordList= WordHome.getWordsList();
		if(str!=null && StringUtils.isNotEmpty(str) && StringUtils.isNotBlank(str)){
			wordStr= str.split(" ");
		}
		boolean _isSwearWords= false;
		int number= 0;
		if(wordStr!= null){
			
			for(String word:wordStr){
				
				if( containsReferenceTo( wordList, word)){
					
					profResult.addWord(word);
					_isSwearWords= true;
					number++;
					
				}
				
			}
		}	
		profResult.setIsSwearWords(_isSwearWords);
		profResult.setNumberSwearWords(number);
		
		return profResult;
	}
   
   
	public static  boolean containsReferenceTo(Collection<Word> collection,
	        String element) {
	    if (collection == null)
	        throw new NullPointerException("collection cannot be null");

	    for (Word x : collection) {
	        if (x.getValue( ).equals(element)) {
	            return true;
	        }
	    }
	    return false;
	}

}
