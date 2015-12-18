
function checkWord(idTextArea)

{

	baseUrl = document.getElementsByTagName('base')[0].href;
	var text = $('#'+idTextArea).val();
	
	$.ajax({

		url : baseUrl + 'jsp/site/plugins/profanityfilter/DoCheckWords.jsp',

		type: 'POST',

		dataType: "json",

		data: {word:text},

		async: false,

	        cache:false,


		success:function(data) {

			if ( data.status == 'OK' )

	    		{	

				if ( data.result )

					{
						for (var i = 0; i < data.result.length; i++) {
							replaceWords(data.result[i],idTextArea);
						}
					}

					else

					{
						//callBackFunctionError();

					}

	    		}
			else if(data.errorCode =='WORD_IS_AUTHORIZED')

	    		{
				//callBackFunctionError();
	  			
	    		}
		

			}

			,

		error: function(jqXHR, textStatus, errorThrown) {

		}

	});
}



function checkWordWithCounter(idTextArea, resourceType)

{

	baseUrl = document.getElementsByTagName('base')[0].href;
	var text = $('#'+idTextArea).val();
	
	$.ajax({

		url : baseUrl + 'jsp/site/plugins/profanityfilter/DoCheckWords.jsp',

		type: 'POST',

		dataType: "json",

		data: {word:text, typeResource: resourceType},

		async: false,

	        cache:false,


		success:function(data) {

			if ( data.status == 'OK' )

	    		{	

				if ( data.result )

					{
						for (var i = 0; i < data.result.length; i++) {
							replaceWords(data.result[i],idTextArea);
						}
					}

					else

					{
						//callBackFunctionError();

					}

	    		}
			else if(data.errorCode =='WORD_IS_AUTHORIZED')

	    		{
				//callBackFunctionError();
	  			
	    		}
		

			}

			,

		error: function(jqXHR, textStatus, errorThrown) {

		}

	});
}


function replaceWords(word, idTextArea){

	function replaceAll(machaine, chaineARemaplacer, chaineDeRemplacement) {
   		return machaine.replace(new RegExp(chaineARemaplacer, 'g'),chaineDeRemplacement);
 	}

	var textArea= $('#'+idTextArea);
	var machaine= textArea.val();
	var newText=replaceAll(machaine, word, "****");
	//newText=newText.replace('#######',word);
	textArea.val(newText);
}
