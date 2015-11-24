/*
 * Copyright (c) 2002-2015, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.profanityfilter.web;
 

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import fr.paris.lutece.plugins.profanityfilter.business.Word;
import fr.paris.lutece.plugins.profanityfilter.business.WordHome;
import fr.paris.lutece.plugins.profanityfilter.service.IProfanityFilter;
import fr.paris.lutece.plugins.profanityfilter.service.ProfanityFilter;
import fr.paris.lutece.portal.service.security.UserNotSignedException;
import fr.paris.lutece.portal.util.mvc.commons.annotations.Action;
import fr.paris.lutece.portal.util.mvc.commons.annotations.View;
import fr.paris.lutece.portal.util.mvc.xpage.MVCApplication;
import fr.paris.lutece.portal.util.mvc.xpage.annotations.Controller;
import fr.paris.lutece.portal.web.xpages.XPage;
import fr.paris.lutece.util.json.AbstractJsonResponse;
import fr.paris.lutece.util.json.ErrorJsonResponse;
import fr.paris.lutece.util.json.JsonResponse;
import fr.paris.lutece.util.json.JsonUtil;
import fr.paris.plugins.profanityfilter.utils.ProfanityResult;

/**
 * This class provides the user interface to view Idee xpages
 */
 
@Controller( xpageName = "filter" , pageTitleI18nKey = "profanityfilter.xpage.filter.pageTitle" , pagePathI18nKey = "profanityfilter.xpage.filter.pagePathLabel" )
public class FilterXPage extends MVCApplication
{
    /**
     *
     */
    private static final long serialVersionUID = 2703580251118435168L;

    // Templates
    private static final String TEMPLATE_VIEW_FILTER="/skin/plugins/profanityfilter/view_filter.html";
    
    // Parameters
    private static final String PARAMETER_WORD="word";
    
    // Markers
    private static final String MARK_WORDS_NOTAUTHORIZED = "wordsNotAuthorized";
 
    
    
    
    // Views
    private static final String VIEW_FILTER = "viewFilter";
  
    //Actions 
    
    private static final String ACTION_PROFANITY_FILTER = "isSwearWord";
    
    // Json CODE
    private static final String JSON_WORD_NOT_AUTHORIZED = "WORD_NOT_AUTHORIZED";
    private static final String JSON_WORD_AUTHORIZED = "WORD_IS_AUTHORIZED";

    
    /**
     * Returns the view  of filter
     *
     * @param request The Http request
     * @return The HTML 
     */
    @View( value = VIEW_FILTER, defaultView = true )
    public XPage getViewFilter( HttpServletRequest request )
    {
       
    	List<Word> listWords = (List<Word>) WordHome.getWordsList(  );
        Map<String, Object> model = getModel(  );
        model.put(MARK_WORDS_NOTAUTHORIZED, listWords);
        
        return getXPage( TEMPLATE_VIEW_FILTER, request.getLocale(  ), model );
    }
    /**
     * 
     * @param request
     * @return
     * @throws UserNotSignedException
     */
    @Action( value = ACTION_PROFANITY_FILTER )
    public String isSwearWord( HttpServletRequest request )throws UserNotSignedException
    {
    	AbstractJsonResponse jsonResponse = null;
		
		IProfanityFilter _filterService= ProfanityFilter.getService();
		
		String word= request.getParameter(PARAMETER_WORD);
		ProfanityResult result= _filterService.checkString(word);

		if (result.isSwearWords()) {
			
			jsonResponse = new JsonResponse(result.getSwearWords( ));	
			
		}
		else {
			jsonResponse = new ErrorJsonResponse( JSON_WORD_AUTHORIZED );
		}

		return JsonUtil.buildJsonResponse(jsonResponse);
    }
 
}
