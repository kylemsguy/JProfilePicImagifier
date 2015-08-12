# JProfilePicImagifier
Converts an image into TCaS's ASCII format. The image _MUST_ be 32x32.

Supports various image formats:
 - PNG
 - BMP
 - And possibly more!
 
To add the generated image to your profile, go to your [profile page](http://www.twocansandstring.com/profile/draw), and use this bookmarklet to import the image data into the page. All you need to do then is to simply click "OK" and you're done!

```javascript
javascript:var imagedatatag = document.getElementById("draw_current_avatar");var usrimagedata = prompt("Please paste the encoded image data for the TCaS Profile Image", imagedatatag.innerHTML);imagedatatag.innerHTML = usrimagedata;draw_cancel_click();alert("Done! If you like what you see, don't forget to click OK to save the image!");
```

To add the bookmarklet to the page, drag [this link](javascript:var imagedatatag = document.getElementById("draw_current_avatar");var usrimagedata = prompt("Please paste the encoded image data for the TCaS Profile Image", imagedatatag.innerHTML);imagedatatag.innerHTML = usrimagedata;draw_cancel_click();alert("Done! If you like what you see, don't forget to click OK to save the image!");) to your bookmarks bar, or bookmark this page and change the link to the code above.


Screenshot:

![Screenshot of JProfilePicImagifier](http://i.imgur.com/nVphfU2.png)
