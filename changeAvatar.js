var imagedatatag = document.getElementById("draw_current_avatar");
var usrimagedata = prompt("Please paste the encoded image data for the TCaS Profile Image", imagedatatag.innerHTML);
imagedatatag.innerHTML = usrimagedata;
draw_cancel_click();
alert("Done! If you like what you see, don't forget to click OK to save the image!");

