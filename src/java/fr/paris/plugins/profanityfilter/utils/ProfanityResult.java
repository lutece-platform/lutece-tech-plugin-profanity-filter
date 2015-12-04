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
package fr.paris.plugins.profanityfilter.utils;

import java.util.HashSet;
import java.util.Set;


public class ProfanityResult
{
    private Set<String> _strWords = new HashSet<String>(  );
    private int _nNumberSwearWords;
    private boolean _bIsSwearWords = false;
    private int _nCounterSwearWord;

    public void addWord( String _strWord )
    {
        this._strWords.add( _strWord );
    }

    public Set<String> getSwearWords(  )
    {
        return this._strWords;
    }

    public int getNumberSwearWords(  )
    {
        return _nNumberSwearWords;
    }

    public void setNumberSwearWords( int _nNumberSwearWords )
    {
        this._nNumberSwearWords = _nNumberSwearWords;
    }

    public boolean isSwearWords(  )
    {
        return _bIsSwearWords;
    }

    public void setIsSwearWords( boolean _bIsSwearWords )
    {
        this._bIsSwearWords = _bIsSwearWords;
    }

    public int getCounterSwearWords(  )
    {
        return _nCounterSwearWord;
    }

    public void setCounterSwearWords( int nCounterSwearWord )
    {
        this._nCounterSwearWord = nCounterSwearWord;
    }
}
