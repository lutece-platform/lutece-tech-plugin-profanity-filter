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
package fr.paris.lutece.plugins.profanityfilter.service;

import fr.paris.lutece.plugins.profanityfilter.business.Counter;
import fr.paris.lutece.plugins.profanityfilter.business.CounterHome;
import fr.paris.lutece.plugins.profanityfilter.business.Word;
import fr.paris.lutece.plugins.profanityfilter.business.WordHome;
import fr.paris.lutece.plugins.profanityfilter.utils.ProfanityResult;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;

import org.apache.commons.lang.StringUtils;

import java.text.Normalizer;
import java.util.Collection;
import java.util.regex.Pattern;

public class ProfanityFilter implements IProfanityFilter
{
    private static ProfanityFilter _singleton;

    // bean
    public static final String BEAN_PROFANITY_FILTER_SERVICE = "profanityfilter.profanityfilterService";
    public static final String CHARACTER_SPACE_BETWEEN_WORDS = AppPropertiesService.getProperty( "character_space_between_words", " " );

    /**
     *
     * @return IEudonetWsService
     */
    public static IProfanityFilter getService( )
    {
        if ( _singleton == null )
        {
            _singleton = SpringContextService.getBean( BEAN_PROFANITY_FILTER_SERVICE );
        }

        return _singleton;
    }

    @Override
    public ProfanityResult checkString( String str )
    {
        ProfanityResult profResult = new ProfanityResult( );
        String [ ] wordStr = null;
        Pattern p = Pattern.compile( "\\W" );
        Collection<Word> wordList = WordHome.getWordsList( );

        if ( ( str != null ) && StringUtils.isNotEmpty( str ) && StringUtils.isNotBlank( str ) )
        {
            wordStr = p.split( str );
        }

        boolean _isSwearWords = false;
        int number = 0;

        if ( wordStr != null )
        {
            for ( String word : wordStr )
            {
                String strWord = containsReferenceTo( wordList, word );
                if ( strWord != null )
                {
                    profResult.addWord( strWord );
                    _isSwearWords = true;
                    number++;
                }
            }
        }

        profResult.setIsSwearWords( _isSwearWords );
        profResult.setNumberSwearWords( number );

        return profResult;
    }

    @Override
    public ProfanityResult checkStringCounter( String str, String strResourceType )
    {
        Counter counter = CounterHome.findByResourceTypeKey( strResourceType );
        Pattern p = Pattern.compile( "\\W" );

        if ( counter == null )
        {
            Counter newCounter = new Counter( );
            newCounter.setCounter( 0 );
            newCounter.setResourceType( strResourceType );
            counter = CounterHome.create( newCounter );
        }

        ProfanityResult profResult = new ProfanityResult( );
        String [ ] wordStr = null;
        Collection<Word> wordList = WordHome.getWordsList( );

        if ( ( str != null ) && StringUtils.isNotEmpty( str ) && StringUtils.isNotBlank( str ) )
        {
            wordStr = p.split( str );
        }

        boolean _isSwearWords = false;
        int number = 0;

        if ( wordStr != null )
        {
            for ( String word : wordStr )
            {
                String strWord = containsReferenceTo( wordList, word );
                if ( strWord != null )
                {
                    profResult.addWord( strWord );
                    _isSwearWords = true;
                    number++;
                    counter.setCounter( counter.getCounter( ) + 1 );
                    CounterHome.update( counter );
                    profResult.setCounterSwearWords( counter.getCounter( ) );
                }
            }
        }

        profResult.setIsSwearWords( _isSwearWords );
        profResult.setNumberSwearWords( number );

        return profResult;
    }

    @Override
    public Counter getCounterSwearWords( String strResourceType )
    {
        return CounterHome.findByResourceTypeKey( strResourceType );
    }

    /**
     * Returns a word if contains reference
     * 
     * @param collection
     * @param element
     * @return word
     */
    public static String containsReferenceTo( Collection<Word> collection, String element )
    {
        if ( collection == null )
        {
            throw new NullPointerException( "collection cannot be null" );
        }

        for ( Word x : collection )
        {
            if ( removeAccent( x.getValue( ) ).toUpperCase( ).equals( removeAccent( element ).toUpperCase( ) )
                    || removeAccent( element ).toUpperCase( ).contains( removeAccent( x.getValue( ) ).toUpperCase( ) ) )
            {
                return x.getValue( );
            }
        }

        return null;
    }

    /**
     * Delete accents of the word
     * 
     * @param source
     * @return words without accents
     */
    public static String removeAccent( String source )
    {
        return Normalizer.normalize( source, Normalizer.Form.NFD ).replaceAll( "[\u0300-\u036F]", "" );
    }
}
