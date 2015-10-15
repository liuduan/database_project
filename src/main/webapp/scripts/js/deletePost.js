function post(path, params, method, csrf_key, csrf_value)
{
	method = method || "post"; 

	var form = document.createElement("form");
	form.setAttribute("method", method);
	form.setAttribute("action", path);
	
	var csrf = document.createElement("input");
	csrf.setAttribute("type", "hidden");
	csrf.setAttribute("name", csrf_key);
	csrf.setAttribute("value", csrf_value);
	form.appendChild(csrf);
	
	for ( var key in params) {
		if (params.hasOwnProperty(key)) {
			var hiddenField = document.createElement("input");
			hiddenField.setAttribute("type", "hidden");
			hiddenField.setAttribute("name", key);
			hiddenField.setAttribute("value", params[key]);

			form.appendChild(hiddenField);
		}
	}

	document.body.appendChild(form);
	form.submit();
}


function delete_record(_url)
{
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");

	$.ajax(
	{
        url: _url,
        type: 'POST',
        beforeSend: function(xhr)
        {
            xhr.setRequestHeader(header, token);
        }
    });
}

function exportFile(_url)
{
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");

	$.ajax(
	{
        url: _url,
        type: 'POST',
        beforeSend: function(xhr)
        {
            xhr.setRequestHeader(header, token);
        }
    });
}

