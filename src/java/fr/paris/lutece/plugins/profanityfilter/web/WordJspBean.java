/*
 * Copyright (c) 2002-2021, City of Paris
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

import fr.paris.lutece.plugins.profanityfilter.business.Word;
import fr.paris.lutece.plugins.profanityfilter.business.WordHome;
import fr.paris.lutece.portal.service.message.AdminMessage;
import fr.paris.lutece.portal.service.message.AdminMessageService;
import fr.paris.lutece.portal.util.mvc.admin.annotations.Controller;
import fr.paris.lutece.portal.util.mvc.commons.annotations.Action;
import fr.paris.lutece.portal.util.mvc.commons.annotations.View;
import fr.paris.lutece.util.url.UrlItem;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * This class provides the user interface to manage Word features ( manage, create, modify, remove )
 */
@Controller( controllerJsp = "ManageWords.jsp", controllerPath = "jsp/admin/plugins/profanityfilter/", right = "PROFANITYFILTER_MANAGEMENT" )
public class WordJspBean extends ManageProfanityfilterJspBean
{
    ////////////////////////////////////////////////////////////////////////////
    // Constants

    // templates
    private static final String TEMPLATE_MANAGE_WORDS = "/admin/plugins/profanityfilter/manage_words.html";
    private static final String TEMPLATE_CREATE_WORD = "/admin/plugins/profanityfilter/create_word.html";
    private static final String TEMPLATE_MODIFY_WORD = "/admin/plugins/profanityfilter/modify_word.html";

    // Parameters
    private static final String PARAMETER_ID_WORD = "id";

    // Properties for page titles
    private static final String PROPERTY_PAGE_TITLE_MANAGE_WORDS = "profanityfilter.manage_words.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_MODIFY_WORD = "profanityfilter.modify_word.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_CREATE_WORD = "profanityfilter.create_word.pageTitle";

    // Markers
    private static final String MARK_WORD_LIST = "word_list";
    private static final String MARK_WORD = "word";
    private static final String JSP_MANAGE_WORDS = "jsp/admin/plugins/profanityfilter/ManageWords.jsp";

    // Properties
    private static final String MESSAGE_CONFIRM_REMOVE_WORD = "profanityfilter.message.confirmRemoveWord";
    private static final String PROPERTY_DEFAULT_LIST_WORD_PER_PAGE = "profanityfilter.listWords.itemsPerPage";
    private static final String VALIDATION_ATTRIBUTES_PREFIX = "profanityfilter.model.entity.word.attribute.";

    // Views
    private static final String VIEW_MANAGE_WORDS = "manageWords";
    private static final String VIEW_CREATE_WORD = "createWord";
    private static final String VIEW_MODIFY_WORD = "modifyWord";

    // Actions
    private static final String ACTION_CREATE_WORD = "createWord";
    private static final String ACTION_MODIFY_WORD = "modifyWord";
    private static final String ACTION_REMOVE_WORD = "removeWord";
    private static final String ACTION_CONFIRM_REMOVE_WORD = "confirmRemoveWord";

    // Infos
    private static final String INFO_WORD_CREATED = "profanityfilter.info.word.created";
    private static final String INFO_WORD_UPDATED = "profanityfilter.info.word.updated";
    private static final String INFO_WORD_REMOVED = "profanityfilter.info.word.removed";

    // Session variable to store working values
    private Word _word;

    /**
     * 
     * @param request
     * @return
     */
    @View( value = VIEW_MANAGE_WORDS, defaultView = true )
    public String getManageWords( HttpServletRequest request )
    {
        _word = null;

        List<Word> listWords = (List<Word>) WordHome.getWordsList( );
        Map<String, Object> model = getPaginatedListModel( request, MARK_WORD_LIST, listWords, JSP_MANAGE_WORDS );

        return getPage( PROPERTY_PAGE_TITLE_MANAGE_WORDS, TEMPLATE_MANAGE_WORDS, model );
    }

    /**
     * Returns the form to create a word
     *
     * @param request
     *            The Http request
     * @return the html code of the word form
     */
    @View( VIEW_CREATE_WORD )
    public String getCreateWord( HttpServletRequest request )
    {
        _word = ( _word != null ) ? _word : new Word( );

        Map<String, Object> model = getModel( );
        model.put( MARK_WORD, _word );

        return getPage( PROPERTY_PAGE_TITLE_CREATE_WORD, TEMPLATE_CREATE_WORD, model );
    }

    /**
     * Process the data capture form of a new word
     *
     * @param request
     *            The Http Request
     * @return The Jsp URL of the process result
     */
    @Action( ACTION_CREATE_WORD )
    public String doCreateWord( HttpServletRequest request )
    {
        populate( _word, request );

        // Check constraints
        if ( !validateBean( _word, VALIDATION_ATTRIBUTES_PREFIX ) )
        {
            return redirectView( request, VIEW_CREATE_WORD );
        }

        WordHome.create( _word );
        addInfo( INFO_WORD_CREATED, getLocale( ) );

        return redirectView( request, VIEW_MANAGE_WORDS );
    }

    /**
     * Manages the removal form of a word whose identifier is in the http request
     *
     * @param request
     *            The Http request
     * @return the html code to confirm
     */
    @Action( ACTION_CONFIRM_REMOVE_WORD )
    public String getConfirmRemoveWord( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_WORD ) );
        UrlItem url = new UrlItem( getActionUrl( ACTION_REMOVE_WORD ) );
        url.addParameter( PARAMETER_ID_WORD, nId );

        String strMessageUrl = AdminMessageService.getMessageUrl( request, MESSAGE_CONFIRM_REMOVE_WORD, url.getUrl( ), AdminMessage.TYPE_CONFIRMATION );

        return redirect( request, strMessageUrl );
    }

    /**
     * Handles the removal form of a word
     *
     * @param request
     *            The Http request
     * @return the jsp URL to display the form to manage words
     */
    @Action( ACTION_REMOVE_WORD )
    public String doRemoveWord( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_WORD ) );
        WordHome.remove( nId );
        addInfo( INFO_WORD_REMOVED, getLocale( ) );

        return redirectView( request, VIEW_MANAGE_WORDS );
    }

    /**
     * Returns the form to update info about a word
     *
     * @param request
     *            The Http request
     * @return The HTML form to update info
     */
    @View( VIEW_MODIFY_WORD )
    public String getModifyWord( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_WORD ) );

        if ( ( _word == null ) || ( _word.getId( ) != nId ) )
        {
            _word = WordHome.findByPrimaryKey( nId );
        }

        Map<String, Object> model = getModel( );
        model.put( MARK_WORD, _word );

        return getPage( PROPERTY_PAGE_TITLE_MODIFY_WORD, TEMPLATE_MODIFY_WORD, model );
    }

    /**
     * Process the change form of a word
     *
     * @param request
     *            The Http request
     * @return The Jsp URL of the process result
     */
    @Action( ACTION_MODIFY_WORD )
    public String doModifyWord( HttpServletRequest request )
    {
        populate( _word, request );

        // Check constraints
        if ( !validateBean( _word, VALIDATION_ATTRIBUTES_PREFIX ) )
        {
            return redirect( request, VIEW_MODIFY_WORD, PARAMETER_ID_WORD, _word.getId( ) );
        }

        WordHome.update( _word );
        addInfo( INFO_WORD_UPDATED, getLocale( ) );

        return redirectView( request, VIEW_MANAGE_WORDS );
    }
}
