/**
 * custom utilities javascript functions
 */

function changeCommDlistDD(ddVal,dd)
{
	//alert(ddVal);
	document.getElementById(dd).innerHTML=ddVal+"<span class=\"caret\"></span>";
	document.getElementById(dd).value =ddVal;
}