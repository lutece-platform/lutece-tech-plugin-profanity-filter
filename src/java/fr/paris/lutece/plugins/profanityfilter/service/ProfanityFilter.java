package fr.paris.lutece.plugins.profanityfilter.service;

import java.util.Collection;

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
   public static ProfanityFilter getService(  )
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
		Collection<Word> wordList= WordHome.getWordsList();
		String [] wordStr= str.split(CHARACTER_SPACE_BETWEEN_WORDS);
		boolean _isSwearWords= false;
		int number= 0;
		
		for(String word:wordStr){
			
			if(wordList.contains(word)){
				
				profResult.addWord(word);
				_isSwearWords= true;
				number++;
				
			}
			
		}
		
		profResult.setIsSwearWords(_isSwearWords);
		profResult.setNumberSwearWords(number);
		
		return profResult;
	}
   
   
   

}
