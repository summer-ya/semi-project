
$(function() {
	var from = "";
	var to = "";
	var checkin = flatpickr("#checkin", {
		locale: "ko",
		minDate: "today",
		dateFormat: "Y년 m월 d일",

		onChange: function(dateStr, dateObj) {
			checkout.set("minDate", dateObj);
			checkin.setDate(dateObj);
			from = $("#checkin").val();
		}
	});
	var checkout = flatpickr("#checkout", {
		locale: "ko",
		minDate: "today",
		dateFormat: "Y년 m월 d일",
		onChange: function() {
			to = $("#checkout").val();
			$.ajax({
				type: "POST",
				url: "/reserv",
				data: { "to": to, "from": from },
				dataType: "text",
				success: function(data) {
					console.log("성공");
				}
			});
		}
	});

});		