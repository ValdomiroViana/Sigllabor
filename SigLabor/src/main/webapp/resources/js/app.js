
$(document).ready(function() {

	$('.js-toggle').bind('click', function() {
		$('.js-sidebar').toggleClass('is-toggled');
		$('.js-content').toggleClass('is-toggled');
		$('.js-toggle').toggleClass('is-toggled');
	
	});
	
});

$(document).ready(function() {

	$('.js-topbar-menu-botton').bind('click', function() {
		$('.js-menu-form').toggleClass('is-toggled');
		$('.js-menu-sistema').toggleClass('is-toggled');
		$('.js-menu-separator').toggleClass('is-toggled');
	
	});
	
});