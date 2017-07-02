
$(document).ready(function() {
	document.getElementById("editForm").hide();
	document.getElementById("regForm").show();
	$("#regBtn").click(Registration);
	$("#editBtn").click(Edit);

});
function Registration() {
	document.getElementById("editForm").hide();
	document.getElementById("regForm").show();
}
function Edit() {

	document.getElementById("editForm").show();
	document.getElementById("regForm").hide();
}