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
import fr.paris.lutece.util.sql.DAOUtil;

import java.util.ArrayList;
import java.util.Collection;


/**
 * This class provides Data Access methods for Word objects
 */
public final class CounterDAO implements ICounterDAO
{
    // Constants
    private static final String SQL_QUERY_NEW_PK = "SELECT max( id_counter ) FROM profanityfilter_counter";
    private static final String SQL_QUERY_SELECT = "SELECT id_counter, resouece_type, counter FROM profanityfilter_counter WHERE resouece_type = ?";
    private static final String SQL_QUERY_INSERT = "INSERT INTO profanityfilter_counter ( id_counter, resouece_type, counter ) VALUES ( ?, ?, ? ) ";
    private static final String SQL_QUERY_DELETE = "DELETE FROM profanityfilter_counter WHERE id_counter = ? ";
    private static final String SQL_QUERY_UPDATE = "UPDATE profanityfilter_counter SET id_counter = ?, resouece_type = ?, counter = ? WHERE resouece_type = ?";
    private static final String SQL_QUERY_SELECTALL = "SELECT id_counter, resouece_type, counter FROM profanityfilter_counter";
    private static final String SQL_QUERY_SELECTALL_ID = "SELECT id_counter FROM profanityfilter_counter";

    /**
     * Generates a new primary key
     * @param plugin The Plugin
     * @return The new primary key
     */
    public int newPrimaryKey( Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_NEW_PK, plugin );
        daoUtil.executeQuery(  );

        int nKey = 1;

        if ( daoUtil.next(  ) )
        {
            nKey = daoUtil.getInt( 1 ) + 1;
        }

        daoUtil.free(  );

        return nKey;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void insert( Counter counter, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, plugin );

        counter.setId( newPrimaryKey( plugin ) );

        daoUtil.setInt( 1, counter.getId(  ) );
        daoUtil.setString( 2, counter.getResourceType(  ) );
        daoUtil.setInt( 3, counter.getCounter(  ) );

        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Counter load( String strResourceType, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT, plugin );
        daoUtil.setString( 1, strResourceType );
        daoUtil.executeQuery(  );

        Counter counter = null;

        if ( daoUtil.next(  ) )
        {
            counter = new Counter(  );
            counter.setId( daoUtil.getInt( 1 ) );
            counter.setResourceType( daoUtil.getString( 2 ) );
            counter.setCounter( daoUtil.getInt( 3 ) );
        }

        daoUtil.free(  );

        return counter;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void delete( int nKey, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE, plugin );
        daoUtil.setInt( 1, nKey );
        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void store( Counter counter, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, plugin );

        daoUtil.setInt( 1, counter.getId(  ) );
        daoUtil.setString( 2, counter.getResourceType(  ) );
        daoUtil.setInt( 3, counter.getCounter(  ) );

        daoUtil.setString( 4, counter.getResourceType(  ) );

        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Collection<Counter> selectCounterList( Plugin plugin )
    {
        Collection<Counter> counterList = new ArrayList<Counter>(  );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            Counter counter = new Counter(  );
            counter.setId( daoUtil.getInt( 1 ) );
            counter.setResourceType( daoUtil.getString( 2 ) );
            counter.setCounter( daoUtil.getInt( 3 ) );

            counterList.add( counter );
        }

        daoUtil.free(  );

        return counterList;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Collection<Integer> selectIdCounterList( Plugin plugin )
    {
        Collection<Integer> counterList = new ArrayList<Integer>(  );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL_ID, plugin );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            counterList.add( daoUtil.getInt( 1 ) );
        }

        daoUtil.free(  );

        return counterList;
    }
}
