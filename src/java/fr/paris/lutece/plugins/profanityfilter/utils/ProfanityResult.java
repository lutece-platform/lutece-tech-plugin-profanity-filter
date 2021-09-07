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
package fr.paris.lutece.plugins.profanityfilter.utils;

import java.util.HashSet;
import java.util.Set;

public class ProfanityResult
{
    private Set<String> _strWords = new HashSet<String>( );
    private int _nNumberSwearWords;
    private boolean _bIsSwearWords = false;
    private int _nCounterSwearWord;

    /**
     * Add the word
     * 
     * @param strWord
     */
    public void addWord( String strWord )
    {
        _strWords.add( strWord );
    }

    /**
     * Returns the words
     * 
     * @return words
     */
    public Set<String> getSwearWords( )
    {
        return _strWords;
    }

    /**
     * Returns the number of the swear word
     * 
     * @return numberSwearWord
     */
    public int getNumberSwearWords( )
    {
        return _nNumberSwearWords;
    }

    /**
     * Sets the nNumberSwearWords
     * 
     * @param nNumberSwearWords
     */
    public void setNumberSwearWords( int nNumberSwearWords )
    {
        _nNumberSwearWords = nNumberSwearWords;
    }

    /**
     * Check if the word is a swear Word
     * 
     * @return bool
     */
    public boolean isSwearWords( )
    {
        return _bIsSwearWords;
    }

    /**
     * Sets isSwearWords
     * 
     * @param bIsSwearWords
     */
    public void setIsSwearWords( boolean bIsSwearWords )
    {
        _bIsSwearWords = bIsSwearWords;
    }

    /**
     * Returs CounterSwearWord
     * 
     * @return CounterSwearWord
     */
    public int getCounterSwearWords( )
    {
        return _nCounterSwearWord;
    }

    /**
     * Returns CounterSwearWord
     * 
     * @param nCounterSwearWord
     */
    public void setCounterSwearWords( int nCounterSwearWord )
    {
        _nCounterSwearWord = nCounterSwearWord;
    }
}
