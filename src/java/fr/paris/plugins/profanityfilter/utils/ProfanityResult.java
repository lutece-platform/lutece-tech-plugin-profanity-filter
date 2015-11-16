package fr.paris.plugins.profanityfilter.utils;

import java.util.HashSet;
import java.util.Set;

public class ProfanityResult {
	
	
	
	private Set<String> _strWords= new HashSet<String>();
	
	private int _nNumberSwearWords;
	
	private boolean _bIsSwearWords = false;

	

	public void addWord(String _strWord) {
		this._strWords.add(_strWord);
	}

	public Set<String> getSwearWords(String _strWord) {
		return this._strWords;
	}

	
	public int getNumberSwearWords() {
		return _nNumberSwearWords;
	}

	public void setNumberSwearWords(int _nNumberSwearWords) {
		this._nNumberSwearWords = _nNumberSwearWords;
	}

	public boolean isIsSwearWords() {
		return _bIsSwearWords;
	}

	public void setIsSwearWords(boolean _bIsSwearWords) {
		this._bIsSwearWords = _bIsSwearWords;
	}
	

}
