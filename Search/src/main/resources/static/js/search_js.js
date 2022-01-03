

var keywordList = [];

$("#keyword").on("keyup paste click", function() {
	var currentVal = $(this).val();
	
	if (currentVal == "") {
		$(".keyword-box-list").html("");
		$(".keyword-box").css('visibility', 'hidden');
		return false;
	} else {
		$.ajax({
			type: 'get',
			url: "/autocomplete",
			dataType: "json",
			data: { "keyword": currentVal },

			success: function(data) {
				keywordList = [];
				for (var i in data) {
					keywordList.push(data[i]);
				}

				$(".keyword-box-list").html("");
				for (var i = 0; i < keywordList.length; i++) {
					$(".keyword-box-list").append("<li>" + keywordList[i] + "</li>");
				}

				if (keywordList.length == 0) {
					$(".keyword-box").css('visibility', 'hidden');
				} else {
					$(".keyword-box").css('visibility', 'visible');
				}
			},

			error: function() {
				alert("fail..");
			}
		})
	}
});


$("#keyword").focus(function() {
	$(".keyword-box").css('visibility', 'visible');
});

$("#keyword").blur(function(e) {
	$(".keyword-box").css('visibility', 'hidden');
});

$(document).on("mousedown", ".keyword-box", function(e) {
	e.preventDefault();
}).on('click', ".keyword-box-list li", function(e) {
	$("#keyword").val($(e.target).text());
	$("#search-form").submit();
});


var lastScrollTop = 0; 
var delta = 5; // 동작의 구현이 시작되는 위치 
var navbarHeight = $('.similar-words').outerHeight();


var didScroll; // 스크롤시에 사용자가 스크롤했다는 것을 알림 
$(window).scroll(function(event){ didScroll = true; }); // hasScrolled()를 실행하고 didScroll 상태를 재설정 
setInterval(function() { 
	if (didScroll) { 
		hasScrolled(); 
		didScroll = false; 
	} 
}, 250); 

function hasScrolled() { // 동작을 구현 
	var st = $(this).scrollTop();
	
	if (Math.abs(lastScrollTop - st) <= delta) return;
	
	if (st > lastScrollTop && st > navbarHeight){
		$('.similar-words').removeClass('nav-down').addClass('nav-up');
	} else { 
		if(st + $(window).height() < $(document).height()) { 
			$('.similar-words').removeClass('nav-up').addClass('nav-down'); 
		} 
	}
	lastScrollTop = st;
}
