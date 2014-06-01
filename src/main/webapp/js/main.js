$(function() {
  $(window).scroll(actualizeBackground);
  function actualizeBackground() {
        if ($(window).scrollTop() > 300) {
            if (!$("body").data("under300")) {
                $("body").data("under300", true);
                $("body").removeClass("notUnder300");
                $("body").addClass("under300");
            }
        } else {
            if ($("body").data("under300")) {
                $("body").data("under300", false);
                $("body").removeClass("under300");
                $("body").addClass("notUnder300");
            }
        }
    }
    
});
