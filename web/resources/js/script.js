/**
 * Created by Denis on 25.12.2016.
 */

function toggleDetails(image) {
    var detailsId = image.id.substring(0, image.id.lastIndexOf(':')) + ':details';
    var details = document.getElementById(detailsId);
    if (details.style.display == 'none') {
        details.style.display = 'block';
        image.src = 'resources/img/collapse.png';
    } else {
        details.style.display = 'none';
        image.src = 'resources/img/expand.png';
    }
}
