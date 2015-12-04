/*
 * Copyright (c) 2002-2015, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *         and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *         and the following disclaimer in the documentation and/or other materials
 *         provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *         contributors may be used to endorse or promote products derived from
 *         this software without specific prior written permission.
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
package fr.paris.lutece.plugins.profanityfilter.business;

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.portal.service.spring.SpringContextService;

import java.util.Collection;


/**
 * This class provides instances management methods (create, find, ...) for Word objects
 */
public final class CounterHome
{
    // Static variable pointed at the DAO instance
    private static ICounterDAO _dao = SpringContextService.getBean( "profanityfilter.counterDAO" );
    private static Plugin _plugin = PluginService.getPlugin( "profanityfilter" );

    /**
     * Private constructor - this class need not be instantiated
     */
    private CounterHome(  )
    {
    }

    /**
     * Create an instance of the word class
     * @param word The instance of the Counter which contains the informations to store
     * @return The  instance of Counter which has been created with its primary key.
     */
    public static Counter create( Counter counter )
    {
        _dao.insert( counter, _plugin );

        return counter;
    }

    /**
     * Update of the Counter which is specified in parameter
     * @param word The instance of the Counter which contains the data to store
     * @return The instance of the  Counter which has been updated
     */
    public static Counter update( Counter counter )
    {
        _dao.store( counter, _plugin );

        return counter;
    }

    /**
     * Remove the Counter whose identifier is specified in parameter
     * @param nKey The Counter Id
     */
    public static void remove( int nKey )
    {
        _dao.delete( nKey, _plugin );
    }

    ///////////////////////////////////////////////////////////////////////////
    // Finders

    /**
     * Returns an instance of a word whose identifier is specified in parameter
     * @param nKey The word primary key
     * @return an instance of Word
     */
    public static Counter findByResourceTypeKey( String strReourceType )
    {
        return _dao.load( strReourceType, _plugin );
    }

    /**
     * Load the data of all the Counter objects and returns them in form of a collection
     * @return the collection which contains the data of all the Counter objects
     */
    public static Collection<Counter> getCounterList(  )
    {
        return _dao.selectCounterList( _plugin );
    }

    /**
     * Load the id of all the Counter objects and returns them in form of a collection
     * @return the collection which contains the id of all the Counter objects
     */
    public static Collection<Integer> getIdWordsList(  )
    {
        return _dao.selectIdCounterList( _plugin );
    }
}
