/**
 * 
 */

function myEditComment(id) {
    var x = document.getElementById("comment"+id);
    var b = document.getElementById("btnc"+id);

    if (x.style.display === 'none') {
    	b.value="cancle"
        x.style.display = 'block';
    } else {
        x.style.display = 'none';
        b.value="edit"
    }
}

function myEditPost(id) {
    var x = document.getElementById("post"+id);
    var b = document.getElementById("btnp"+id);

    if (x.style.display === 'none') {
    	b.value="cancle"
        x.style.display = 'block';
    } else {
        x.style.display = 'none';
        b.value="edit"
    }
}